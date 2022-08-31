package com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Domain;

import com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Infraestructure.DTO.Input.UsuarioInputDTO;
import com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Infraestructure.DTO.Output.UsuarioOutputDTO;
import com.bosonit.EJ2CRUDconValidacionJuanRodrigo.Infraestructure.Repository.UsuarioRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UsuarioInterface{

    static final int pageSize = 10;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    EntityManager entityManager;

    @Override

    public List<UsuarioOutputDTO>getAllPersons (int pageNumber){
        List<UsuarioOutputDTO>usuarioOutputDTOList = new ArrayList<>();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsuarioEntity>query = cb.createQuery(UsuarioEntity.class);
        Root<UsuarioEntity> usuarioEntityRoot = query.from(UsuarioEntity.class);
        CriteriaQuery<UsuarioEntity> select = query.select(usuarioEntityRoot);
        TypedQuery<UsuarioEntity> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult((pageNumber*10)-10);
        typedQuery.setMaxResults(pageSize);
        List<UsuarioEntity> paginatedPersons = typedQuery.getResultList();

        for (UsuarioEntity usuarioEntity : paginatedPersons){
           UsuarioOutputDTO auxOutputDTO = new UsuarioOutputDTO(usuarioEntity);
            usuarioOutputDTOList.add(auxOutputDTO);
        }
        return usuarioOutputDTOList;


    }



    @Override
    public boolean existsPerson(int id) {
        return usuarioRepositorio.existsById(id);
    }

    @Override
    public UsuarioOutputDTO getPersonByID(int id) {
        UsuarioEntity usuarioEntity = usuarioRepositorio.findById(id).orElse(null);
        UsuarioOutputDTO usuarioOutputDTO = new UsuarioOutputDTO(usuarioEntity);
        return usuarioOutputDTO;
    }

    @Override
    public List<UsuarioOutputDTO> getPersonsByName(String name) {
        List<UsuarioOutputDTO> personaOutputDTOList = new ArrayList<>();

        for (UsuarioEntity personEntity : usuarioRepositorio.findByName(name)){
            UsuarioOutputDTO auxOutputDTO = new UsuarioOutputDTO(personEntity);
            personaOutputDTOList.add(auxOutputDTO);
        }

        return personaOutputDTOList;
    }

    @Override
    public List<UsuarioOutputDTO> getPersonsWithCriteriaQuery(
            Optional<String> name,
            Optional<String> user,
            Optional<String> creation_date,
            String dateCondition,
            Optional<String> sorting) {

        List<UsuarioOutputDTO> personaOutputDTOList = new ArrayList<>();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsuarioEntity> query = cb.createQuery(UsuarioEntity.class);
        Root<UsuarioEntity> personaEntityRoot = query.from(UsuarioEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        if (name.isPresent())
            predicates.add(cb.like(personaEntityRoot.get("name"), name.get()));
        if (user.isPresent())
            predicates.add(cb.like(personaEntityRoot.get("usuario"), user.get()));

        if (creation_date.isPresent()){
            switch (dateCondition){
                case "pre":
                    predicates.add(cb.lessThan(personaEntityRoot.get("created_date"), creation_date.get()));
                    break;

                case "pos":
                    predicates.add(cb.greaterThan(personaEntityRoot.get("created_date"), creation_date.get()));
                    break;

                default:
                    predicates.add(cb.equal(personaEntityRoot.get("created_date"), creation_date.get()));
            }
        }

        if(sorting.isPresent()){
            switch (sorting.get()){
                case "name":
                    query.select(personaEntityRoot).where(cb.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(cb.asc(personaEntityRoot.get("name")));
                    break;

                case "user":
                    query.select(personaEntityRoot).where(cb.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(cb.asc(personaEntityRoot.get("usuario")));
                    break;
            }
        } else query.select(personaEntityRoot).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));



        entityManager.createQuery(query).getResultList().forEach( personEntity -> {
            personaOutputDTOList.add(new UsuarioOutputDTO((UsuarioEntity) personEntity));
        });
        return personaOutputDTOList;
    }

    @Override
    public UsuarioOutputDTO postPerson(UsuarioInputDTO personInputDTO) {
        UsuarioEntity usuarioEntity = new UsuarioEntity(personInputDTO);
        usuarioRepositorio.save(usuarioEntity);
        UsuarioOutputDTO personaOutputDTO = new UsuarioOutputDTO(usuarioEntity);
        return personaOutputDTO;
    }

    @Override
    public UsuarioOutputDTO updatePerson(int id, UsuarioInputDTO usuarioInputDTO) {
        /*
            We could then simply get the entity from the database,
            make the change, and use the save() method as before.
         */
      UsuarioEntity personInDB = usuarioRepositorio.findById(id).orElse(null);
        personInDB.updateEntity(usuarioInputDTO);
        usuarioRepositorio.save(personInDB);

        UsuarioOutputDTO personaOutputDTO = new UsuarioOutputDTO(personInDB);
        return personaOutputDTO;
    }

    @Override
    public UsuarioOutputDTO deletePerson(int id) {
        UsuarioOutputDTO personaOutputDTO = getPersonByID(id);
      usuarioRepositorio.deleteById(id);
        return personaOutputDTO;
    }
}










