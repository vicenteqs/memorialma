package es.vqs.memorial;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource(collectionResourceRel = "nadador", path = "nadador")
public interface NadadorRepository extends CrudRepository<Nadador, Long> {
	List<Nadador> findBySerieOrderByCalle(@Param("serie") Integer serie);
}
