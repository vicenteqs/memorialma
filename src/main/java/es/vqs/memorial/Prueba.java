package es.vqs.memorial;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Prueba implements Serializable {
    private static final long serialVersionUID = -2216382168252563212L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    @JsonIgnore
    @OneToMany(mappedBy = "prueba")
    private List<Serie> series;


}
