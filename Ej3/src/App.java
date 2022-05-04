import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class App {
    static File archivo;
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
    public void añadirAlumno(String nombre, int fecha, int codigo) throws IOException{
        try(FileOutputStream fout= new FileOutputStream(archivo,false   );
        DataOutputStream out = new DataOutputStream(fout)){
            out.writeUTF(nombre);
            out.writeInt(fecha);
            out.writeInt(codigo);
        }
        catch(IOException e){
            System.out.println("Error");
        }
    }
    public void mostrarAlumnos()throws IOException{
        int loop=1;
        try(FileInputStream fis= new FileInputStream(archivo);
        DataInputStream in= new DataInputStream(fis)){
            try {
                while (true) {
                    System.out.println(loop+".Nombre:"+in.readUTF()+" Fecha:"+in.readInt()+" Código:"+in.readInt());
                    loop++;
                }
            } catch (EOFException e) {}
        }
    }
    public void borrarAlumno(int borrar){
        try()
    }
}
