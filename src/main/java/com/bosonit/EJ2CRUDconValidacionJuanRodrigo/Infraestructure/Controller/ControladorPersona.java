package com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Infraestructure.Controller;

import com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Domain.UsuarioEntity;
import com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Domain.UsuarioService;
import com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Infraestructure.DTO.Input.UsuarioInputDTO;
import com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Infraestructure.DTO.Output.UsuarioOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

    @RestController
    public class ControladorPersona {

        @Autowired
        UsuarioService usuarioService;

        @PostMapping("/crearusuario")
        public UsuarioOutputDTO crearUsuario(@RequestBody UsuarioInputDTO usuarioInputDTO) throws Exception {
           return usuarioService.postPerson(usuarioInputDTO);


        }

        @GetMapping("/id/{id}")
        public UsuarioOutputDTO getUsuarioByID(@PathVariable(value = "id") Integer id) {
            return usuarioService.getPersonByID(id);
        }


        @GetMapping("/usuario/{usuario}")
        public List<UsuarioOutputDTO> getUsuarioByUser(@PathVariable(value = "name") String name) {
            return usuarioService.getPersonsByName(name);
        }

        @GetMapping("/usuario/all/{pageNumber}")
        public List<UsuarioOutputDTO> getAllUsuarios(@PathVariable int pageNumber) {
            return usuarioService.getAllPersons(pageNumber);
        }

        @PutMapping("/usuario/update/{id}")
        public UsuarioOutputDTO updateUsuarioByID(@PathVariable(value = "id") Integer id, @RequestBody UsuarioInputDTO usuarioInputDTO) {
            return usuarioService.updatePerson(id,usuarioInputDTO);
        }

        @DeleteMapping("/usuario/{id}")
        public UsuarioOutputDTO deleteByID(@PathVariable(value = "id") Integer id) {
          return  usuarioService.deletePerson(id);
        }

        @GetMapping("/usuario/criteria")

        public List<UsuarioOutputDTO> getPersonsWithCriteriaQuery(@RequestParam Optional<String> name , Optional<String> user , Optional<String> creation_date , String dateCondition , Optional<String> sorting){
        return usuarioService.getPersonsWithCriteriaQuery(
                 name,
                 user,
                 creation_date,
                dateCondition,
                 sorting
        );
        }
    }





