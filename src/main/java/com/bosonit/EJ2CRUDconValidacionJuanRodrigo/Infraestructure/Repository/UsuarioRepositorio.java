package com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Infraestructure.Repository;

import com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Domain.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<UsuarioEntity, Integer> {
    List<UsuarioEntity> findByUsuario(String nombre);


    UsuarioEntity[] findByName(String name);
}



