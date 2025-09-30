package com.Javax.usuario.business;

import com.Javax.usuario.business.converter.UsuarioConverter;
import com.Javax.usuario.business.dto.UsuarioDTO;
import com.Javax.usuario.infrastructure.entity.Usuario;
import com.Javax.usuario.infrastructure.exceptions.ConflictException;
import com.Javax.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.Javax.usuario.infrastructure.repository.UsuarioRepository;
import com.Javax.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    //Método para salvar dados

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public void emailExiste(String email){
        try{
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já Cadastrado!");
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já Cadastrado ", e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado" + email));
    }
    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto){
        //buscou email do usuário atraves do token(para tirar a obrigatoriedade de passar o email)
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        //criptografia de senha
        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);

        //Busca os dados do usuario no banco de dados
        Usuario usuarioEmtity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email não localizado"));
        //mesclou os dadsos que recebeu na requisição  DTO com os do banco de dados
        Usuario usuario = usuarioConverter.updateUsuario(dto,usuarioEmtity);

        //salvou os dados do usuário convertido e retornou e converteu para UsuarioDTO
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}
