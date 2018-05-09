package es.vqs.memorial;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource(collectionResourceRel = "serie", path = "serie")
public interface SerieRepository extends CrudRepository<Serie, Integer> {

	Serie findOneByPruebaAndNumero(Prueba p, Integer numero);
}
