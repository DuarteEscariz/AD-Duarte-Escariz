import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class App {
    File archivo;
    public File getArchivo() {
        return archivo;
    }
    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
    public ArrayList<Object> mostrarDatos(boolean escribir)throws IOException{
        ArrayList <Object> salida=new ArrayList<Object>();
        try(FileInputStream fis=new FileInputStream(archivo);
        ObjectInputStream in=new ObjectInputStream(fis)){
            try {
                
                while(true){
                    Object obj=in.readObject();
                    salida.add(obj);
                    if(obj.getClass()==Persona.class&&escribir){
                        System.out.println(((Persona)obj).toString());
                    }
                    if(obj.getClass()==Depart.class&&escribir){
                        System.out.println(((Depart)obj).toString());
                    }
                }
            } catch (EOFException e) {
                //TODO: handle exception
            }
            return salida;
        }
    }
    public void añadirDatos(Object obj)throws IOException{
        ArrayList<Object> datos=mostrarDatos(false);
        datos.add(obj);
        try(FileOutputStream fos= new FileOutputStream(archivo);
        ObjectOutputStream out= new ObjectOutputStream(fos)){
            for (Object object : datos) {
                out.writeObject(object);
            }
        }
    }
}

