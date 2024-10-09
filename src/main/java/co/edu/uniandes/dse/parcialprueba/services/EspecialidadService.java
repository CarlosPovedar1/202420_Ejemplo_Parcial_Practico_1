package co.edu.uniandes.dse.parcialprueba.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EspecialidadService {
    @Autowired
    private EspecialidadRepository especialidadRepository;
    @Transactional
    public EspecialidadEntity crearEspecialidad(EspecialidadEntity especialidadEntity){
        log.info("Crear Especialidad");
        if(especialidadEntity.getDescripcion().length() <10 ){
            throw new IllegalArgumentException("La descripcion de la especialidad no es valida");
        }
        return especialidadRepository.save(especialidadEntity);
    }
}
