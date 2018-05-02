package es.vqs.memorial.a;

public class Cuadrado extends Forma {

    public Cuadrado() {
        this.tipo = "Cuadrado";
    }

    @Override
    public void pintar() {
        System.out.println("Dentro del método pintar de Cuadrado");
    }
}
