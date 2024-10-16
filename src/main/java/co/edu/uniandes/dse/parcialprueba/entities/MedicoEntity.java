package co.edu.uniandes.dse.parcialprueba.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class MedicoEntity extends BaseEntity{
    
   LocalDate fechaNacimiento;
    private String nombre;
    private String registro;

    @PodamExclude
	@ManyToMany
	private List<EspecialidadEntity> especialidades = new ArrayList<>();
    
}
