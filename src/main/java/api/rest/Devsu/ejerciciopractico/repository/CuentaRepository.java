package api.rest.Devsu.ejerciciopractico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.rest.Devsu.ejerciciopractico.model.CuentaModel;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaModel, Long> {
	
	List<CuentaModel> findByClienteModel_NumDoc(String numDoc);
	List<CuentaModel> findByNumCuenta(String numCuenta);
}
