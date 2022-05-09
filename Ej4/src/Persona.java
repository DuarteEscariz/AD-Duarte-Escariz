import java.io.Serializable;

public class Persona implements Serializable {
    String nombre;
    int edad;
    int cod;
    public Persona(String nombre, int edad, int cod) {
        this.nombre = nombre;
        this.edad = edad;
        this.cod = cod;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public int getCod() {
        return cod;
    }
    public void setCod(int cod) {
        this.cod = cod;
    }

}
