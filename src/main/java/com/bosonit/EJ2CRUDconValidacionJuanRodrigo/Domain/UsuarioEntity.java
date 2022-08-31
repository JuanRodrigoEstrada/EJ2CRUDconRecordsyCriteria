package com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Domain;

import com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Infraestructure.DTO.Input.UsuarioInputDTO;
import com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Infraestructure.DTO.Output.UsuarioOutputDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name = "Personas")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Persona")
    private Integer id_persona;

    @Column(name = "Usuario", nullable = false)
    private String usuario;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Nombre", nullable = false)
    private String name;

    @Column(name = "Surname", nullable = false)
    private String surname;
    @Column(name = "Company_email", nullable = false)
    private String company_email;

    @Column(name = "Personal_email", nullable = false)
    private String personal_email;

    @Column(name = "Ciudad", nullable = false)
    private String city;

    @Column(name = "Activo", nullable = false)
    private Boolean active;

    @Column(name = "Created_Date", nullable = false)
    private String created_date;

    @Column(name = "Imagen_URL")
    private String imagen_url;

    @Column(name = "Date")
    private Date termination_date;



    public UsuarioEntity(UsuarioInputDTO usuarioInputDTO) {
        if (usuarioInputDTO == null) return;
        id_persona = usuarioInputDTO.id_persona();
        usuario = usuarioInputDTO.usuario();
        password = usuarioInputDTO.password();
        name = usuarioInputDTO.name();
        surname = usuarioInputDTO.surname();
        company_email = usuarioInputDTO.company_email();
        personal_email = usuarioInputDTO.personal_email();
        city = usuarioInputDTO.city();
        active = usuarioInputDTO.active();
        created_date = usuarioInputDTO.created_date();
        imagen_url = usuarioInputDTO.imagen_url();
        termination_date = usuarioInputDTO.termination_date();
    }

    public UsuarioEntity(List<UsuarioOutputDTO> usuarioOutputDTOList) {
    }

    public void updateEntity(UsuarioInputDTO usuarioInputDTO) {
    }
}
