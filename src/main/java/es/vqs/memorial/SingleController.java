package es.vqs.memorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SingleController {

    @Autowired
    private NadadorRepository nadadorRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private SerieRepository serieRepository;
    @Autowired
    private PruebaRepository pruebaRepository;
    @Autowired
    private WebSocketController webSocketController;

    @GetMapping("/serieActual")
    private List<Nadador> getSerieActual() {
        Estado estadoActual = this.estadoRepository.findFirstByOrderByIdDesc();
        if (estadoActual != null) {
            return estadoActual.getUltimaSerie().getNadadores();
        } else {
            return new ArrayList<>();
        }
    }

    @GetMapping("/pruebaActual")
    private Prueba getPruebaActual() {
        Estado estadoActual = this.estadoRepository.findFirstByOrderByIdDesc();
        if (estadoActual != null) {
            return estadoActual.getUltimaSerie().getPrueba();
        } else {
            return new Prueba();
        }
    }

    @GetMapping("/numSerieActual")
    private Serie getNumSerieActual() {
        Estado estadoActual = this.estadoRepository.findFirstByOrderByIdDesc();
        if (estadoActual != null) {
            return estadoActual.getUltimaSerie();
        } else {
            return new Serie();
        }
    }


    @GetMapping("/cambiarEstado")
    private void setSiguienteSerie() {
        Estado estadoActual = this.estadoRepository.findFirstByOrderByIdDesc();
        if (estadoActual != null) {
            if (estadoActual.getEstado().equals(1)) {
                Serie s = estadoActual.getUltimaSerie();
                Serie siguienteSerie = this.serieRepository.findOneByPruebaAndNumero(s.getPrueba(), s.getNumero() + 1);
                if (siguienteSerie == null) {
                    Prueba p = this.pruebaRepository.findFirstByIdGreaterThanOrderById(s.getPrueba().getId());
                    if (p != null) {
                        siguienteSerie = this.serieRepository.findOneByPruebaAndNumero(p, 1);
                    }
                }
                estadoActual.setUltimaSerie(siguienteSerie);
                estadoActual.setEstado(0);
            } else if (estadoActual.getEstado().equals(-1)) {
                estadoActual.setEstado(0);
            } else if (estadoActual.getEstado().equals(0)) {
                estadoActual.setEstado(1);
            }
        } else {
            estadoActual = new Estado();
            Prueba p = this.pruebaRepository.findFirstByIdGreaterThanOrderById(0);
            estadoActual.setUltimaSerie(p.getSeries().get(0));
            estadoActual.setEstado(-1);
        }
        this.estadoRepository.save(estadoActual);
        this.webSocketController.onReceivedMessage("yeha");
    }

    @GetMapping("/resetear")
    private void resetear() {
        Estado estadoActual = new Estado();
        estadoActual.setEstado(-1);
        estadoActual.setUltimaSerie(this.pruebaRepository.findFirstByIdGreaterThanOrderById(0).getSeries().get(0));
        this.estadoRepository.save(estadoActual);
        this.webSocketController.onReceivedMessage("yeha");
    }

    @GetMapping("/irA")
    private void irA(@RequestParam("prueba") Integer prueba, @RequestParam("serie") Integer serie) {
        Estado estadoActual = new Estado();
        Prueba p = this.pruebaRepository.findById(prueba).orElse(null);
        estadoActual.setUltimaSerie(this.serieRepository.findOneByPruebaAndNumero(p, serie));
        estadoActual.setEstado(-1);
        this.estadoRepository.save(estadoActual);
        this.webSocketController.onReceivedMessage("yeha");
    }
}
