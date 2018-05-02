package es.vqs.memorial.a;

public class Circulo extends Forma {

    public Circulo() {
        this.tipo = "Circulo";
    }

    @Override
    public void pintar() {
        System.out.println("Dentro del método pintar de circulo");
    }

}
