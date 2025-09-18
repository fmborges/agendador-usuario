package com.Javax.usuario.business;

import com.Javax.usuario.business.converter.UsuarioConverter;
import com.Javax.usuario.business.dto.UsuarioDTO;
import com.Javax.usuario.infrastructure.entity.Usuario;
import com.Javax.usuario.infrastructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;


    //Metodo para salvar dados

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){

        //Converter usuarioDTO para usuario entity
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        //salvar
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

    }
}
