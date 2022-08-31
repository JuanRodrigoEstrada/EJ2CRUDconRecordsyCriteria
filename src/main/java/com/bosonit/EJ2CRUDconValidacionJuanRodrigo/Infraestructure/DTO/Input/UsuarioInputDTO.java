package com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Infraestructure.DTO.Input;




import java.util.Date;


    public record UsuarioInputDTO (
        Integer id_persona,
        String usuario,
        String password,
        String name,
        String surname,
        String company_email,
        String personal_email,
        String city,
        Boolean active,
       String created_date,
        String imagen_url,
        Date termination_date){}




