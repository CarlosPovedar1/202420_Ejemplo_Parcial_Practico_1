package co.edu.uniandes.dse.parcialprueba.services;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialprueba.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoEspecialidadService {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EspecialidadRepository especialidadRepository;
    
    
    @Transactional
    
    public EspecialidadEntity addEspecialidad(Long idMedico, Long idEspecialidad)throws EntityNotFoundException{
        log.info("agregar Especialidad");
        Optional<MedicoEntity> medico = medicoRepository.findById(idMedico);
        Optional<EspecialidadEntity> especialidad = especialidadRepository.findById(idEspecialidad);
        if(medico.isEmpty() || especialidad.isEmpty()){
            throw new EntityNotFoundException("No se encontro el medico o la especialidad");
        }
        medico.get().getEspecialidades().add(especialidad.get());
        log.info("Termino el proceso de agregar la especialidad al medico");
        return especialidad.get(); 
    }
    
}
