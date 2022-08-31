package com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Infraestructure.DTO.Output;

import com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Domain.UsuarioEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


public record UsuarioOutputDTO(
     Integer id_persona,
     String usuario,
     String name,
     String surname,
     String company_email,
     String personal_email,
     String city,
     Boolean active,
    String created_date,
     String imagen_url,
     Date termination_date){

    public UsuarioOutputDTO (UsuarioEntity usuarioEntity) {
      this(
        usuarioEntity.getId_persona(),
        usuarioEntity.getUsuario(),
        usuarioEntity.getName(),
        usuarioEntity.getSurname(),
        usuarioEntity.getCompany_email(),
        usuarioEntity.getPersonal_email(),
        usuarioEntity.getCity(),
        usuarioEntity.getActive(),
        usuarioEntity.getCreated_date(),
        usuarioEntity.getImagen_url(),
        usuarioEntity.getTermination_date());


    }

}
