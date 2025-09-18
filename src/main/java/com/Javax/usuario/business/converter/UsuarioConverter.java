package com.Javax.usuario.business.converter;

import com.Javax.usuario.business.dto.EnderecoDTO;
import com.Javax.usuario.business.dto.TelefoneDTO;
import com.Javax.usuario.business.dto.UsuarioDTO;
import com.Javax.usuario.infrastructure.entity.Endereco;
import com.Javax.usuario.infrastructure.entity.Telefone;
import com.Javax.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .senha(usuarioDTO.getSenha())
                .email(usuarioDTO.getEmail())
                .telefones(paraListaTelefone(usuarioDTO.getTelefone()))
                .enderecos(paraListaEndereco(usuarioDTO.getEndereco()))

                .build();
    }


    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEndereco).toList();
        /*
        List<Endereco> enderecos = new ArrayList<>();
        for(EnderecoDTO enderecoDTO : enderecoDTOS){
           enderecos.add(paraEndereco(enderecoDTO));
           }
           return enderecos;
         */
    }


    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
        List<Telefone> telefones = new ArrayList<>();
        for (TelefoneDTO telefoneDTO : telefoneDTOS){
            telefones.add(paraTelefone(telefoneDTO));
        }
        return telefones;
    }


    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }


    //inverter

    public UsuarioDTO paraUsuarioDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .senha(usuario.getSenha())
                .email(usuario.getEmail())
                .telefone(paraListaTelefoneDTO(usuario.getTelefones()))
                .endereco(paraListaEnderecoDTO(usuario.getEnderecos()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecos){
        return enderecos.stream().map(this::paraEnderecoDTO).toList();
    /*
    List<EnderecoDTO> enderecoDTOS = new ArrayList<>();
    for(Endereco endereco : enderecos){
        enderecoDTOS.add(paraEnderecoDTO(endereco));
    }
    return enderecoDTOS;
     */
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .cidade(endereco.getCidade())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefones){
        List<TelefoneDTO> telefoneDTOS = new ArrayList<>();
        for (Telefone telefone : telefones){
            telefoneDTOS.add(paraTelefoneDTO(telefone));
        }
        return telefoneDTOS;
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        return TelefoneDTO.builder()
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }



}
