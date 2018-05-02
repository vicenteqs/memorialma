package es.vqs.memorial.a;

public abstract class Forma implements Cloneable {

    private String id;
    protected String tipo;

    public abstract void pintar();

    public String getTipo(){
        return tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }
}
