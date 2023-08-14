package api.rest.Devsu.ejerciciopractico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.rest.Devsu.ejerciciopractico.model.ClienteModel;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

	Optional<ClienteModel> findByNumDoc(String docIdentif);

}
