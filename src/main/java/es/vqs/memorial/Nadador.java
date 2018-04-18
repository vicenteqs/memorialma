package es.vqs.memorial;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Nadador implements Serializable {
	private static final long serialVersionUID = -2216382168252563212L;

	@Id
	@GeneratedValue
	private Long id;
	private Integer serie;
	private Integer calle;
	private String nombre;
	private String club;

}
