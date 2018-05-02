package es.vqs.memorial.a;

public class Rectangulo extends Forma {
    public Rectangulo() {
        this.tipo = "Rectangulo";
    }

    @Override
    public void pintar() {
        System.out.println("Dentro del método pintar de Rectangulo");
    }
}
