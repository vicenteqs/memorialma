package es.vqs.memorial;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Estado implements Serializable {
    private static final long serialVersionUID = -2216382168252563212L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_ultima_serie")
    private Serie ultimaSerie;
    private Integer estado;


}
