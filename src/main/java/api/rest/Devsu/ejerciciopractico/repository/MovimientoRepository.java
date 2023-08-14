package api.rest.Devsu.ejerciciopractico.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.rest.Devsu.ejerciciopractico.model.ClienteModel;
import api.rest.Devsu.ejerciciopractico.model.MovimientoModel;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoModel, Long> {
	
	List<MovimientoModel> findByCuentaModel_ClienteModel_NumDoc(String numDoc);
	List<MovimientoModel> findByFechaBetweenAndCuentaModel_ClienteModel(LocalDate fechaInicio, LocalDate fechaFin, ClienteModel cliente);
	List<MovimientoModel> findByFechaBetweenAndCuentaModel_ClienteModel_NumDoc(LocalDateTime fechaInicio, LocalDateTime fechaFin, String numDoc);
	
}
