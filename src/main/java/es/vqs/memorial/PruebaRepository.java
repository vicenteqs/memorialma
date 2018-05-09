package es.vqs.memorial;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource(collectionResourceRel = "prueba", path = "prueba")
public interface PruebaRepository extends CrudRepository<Prueba, Integer> {

	Prueba findFirstByIdGreaterThanOrderById(Integer id);
}
