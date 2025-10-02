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
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos()))

                .build();
    }


    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS){
        List<Endereco> enderecos = new ArrayList<>();
        if (enderecoDTOS != null) { // só itera se não for null
            for (EnderecoDTO enderecoDTO : enderecoDTOS) {
                enderecos.add(paraEndereco(enderecoDTO));
            }
        }
        return enderecos;

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
                .enderecos(paraListaEnderecoDTO(usuario.getEnderecos()))
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
                .id(endereco.getId())
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
                .id(telefone.getId())
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario entity){
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome())
                .id(entity.getId())
                .senha(usuarioDTO.getSenha() !=null ? usuarioDTO.getSenha() : entity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : entity.getEmail())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }

    public Endereco updateEndereco(EnderecoDTO dto, Endereco entity){
        return Endereco.builder()
                .id(entity.getId())
                .rua(dto.getRua() != null ? dto.getRua() : entity.getRua())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .complemento(dto.getComplemento() != null ? dto.getComplemento() : entity.getComplemento())
                .cidade(dto.getCidade() != null ? dto.getCidade() : entity.getCidade())
                .cep(dto.getCep() != null ? dto.getCep() : entity.getCep())
                .estado(dto.getEstado() != null ? dto.getEstado() : entity.getEstado())
                .build();


    }

    public Telefone updateTelefone(TelefoneDTO dto, Telefone entity){
        return Telefone.builder()
                .id(entity.getId())
                .ddd(dto.getDdd() != null ? dto.getDdd() : entity.getDdd())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .build();
    }

    public Endereco paraEnderecoEntity(EnderecoDTO dto, Long idUsuario){
        return Endereco.builder()
                .rua(dto.getRua())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .cep(dto.getCep())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .usuario_id(idUsuario)

                .build();
    }

    public Telefone paraTelefoneEntity(TelefoneDTO dto, Long idUsuario){
        return Telefone.builder()
                .ddd(dto.getDdd())
                .numero(dto.getNumero())
                .usuario_id(idUsuario)

                .build();
    }



}
