package com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Domain;

import com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Infraestructure.DTO.Input.UsuarioInputDTO;
import com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Infraestructure.DTO.Output.UsuarioOutputDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UsuarioInterface {

    List<UsuarioOutputDTO>getAllPersons(int pageNumber);

    List<UsuarioOutputDTO> getPersonsWithCriteriaQuery(
            Optional<String> name,
            Optional<String> user,
            Optional<String> creation_date,
            String dateCondition,
            Optional<String> sorting);

    UsuarioOutputDTO postPerson (UsuarioInputDTO usuarioInputDTO);
    UsuarioOutputDTO deletePerson(int id);
    UsuarioOutputDTO updatePerson(int id, UsuarioInputDTO usuarioInputDTO );

    boolean existsPerson(int id);

    UsuarioOutputDTO getPersonByID(int id);

    List<UsuarioOutputDTO> getPersonsByName(String name);
}
