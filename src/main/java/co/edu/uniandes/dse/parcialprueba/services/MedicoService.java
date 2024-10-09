package co.edu.uniandes.dse.parcialprueba.services;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialprueba.repositories.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class MedicoService {
    //crear medico

    @Autowired
    private MedicoRepository medicoRepository;
    @Transactional
    public MedicoEntity crearMedico(MedicoEntity medicoEntity){ 
        log.info("Crear Medico");
        LocalDate fechaActual = LocalDate.now();
        if(medicoEntity.getRegistro() == null || medicoEntity.getRegistro().substring(0, 1) == "RW"){
            throw new IllegalArgumentException("El registro  del medico no es valido");
        }
        int edad = Period.between(medicoEntity.getFechaNacimiento(), fechaActual).getYears();
        if(edad<25){
            throw new IllegalArgumentException("El medico no cumple con la edad minima");
        }
        return medicoRepository.save(medicoEntity);

        
        
    }
}
