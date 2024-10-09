package co.edu.uniandes.dse.parcialprueba.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialprueba.entities.MedicoEntity;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MedicoService.class)
class MedicoServiceTest {
    @Autowired
    private MedicoService medicoService;

    private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

	private List<MedicoEntity> medicoList = new ArrayList<>();
    /**
	 * Configuración inicial de la prueba.
	 */
	@BeforeEach
	void setUp() {
		clearData();
		insertData();
	}
    private void clearData() {
		entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
		entityManager.getEntityManager().createQuery("delete from EspecialidadEntity").executeUpdate();
	}
    private void insertData() {
		for (int i = 0; i < 3; i++) {
			MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
			entityManager.persist(medicoEntity);
			medicoList.add(medicoEntity);
		}
	}
    @Test
    void testCrearMedico() {
        MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
        
        newEntity.setRegistro(
            "RW" + newEntity.getRegistro().substring(2)
        );
        MedicoEntity result = medicoService.crearMedico(newEntity);
        assertNotNull(result);
        
        MedicoEntity entity = entityManager.find(MedicoEntity.class, result.getId());
        
        assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getNombre(), entity.getNombre());
        assertEquals(newEntity.getRegistro(), entity.getRegistro());

    
    }
    }
