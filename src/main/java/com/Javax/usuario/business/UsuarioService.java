package com.Javax.usuario.business;

import com.Javax.usuario.business.converter.UsuarioConverter;
import com.Javax.usuario.business.dto.UsuarioDTO;
import com.Javax.usuario.infrastructure.entity.Usuario;
import com.Javax.usuario.infrastructure.exceptions.ConflictException;
import com.Javax.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

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
}
