import java.io.Serializable;
import java.util.ArrayList;

public class Depart implements Serializable {
    String nombre;
    ArrayList<Persona> personas;
    int codigo;
    public Depart(String nombre, ArrayList<Persona> personas, int codigo) {
        this.nombre = nombre;
        this.personas = personas;
        this.codigo = codigo;
    }

    public Depart() {
        personas=new ArrayList<Persona>();
        nombre="";
        codigo=0;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }
    
    
}
