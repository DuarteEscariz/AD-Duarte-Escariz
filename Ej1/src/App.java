import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        
    }
    public void divChar(File f, int n) throws IOException{
        try(FileReader fichin=new FileReader(f)){
            char buffer[]=new char[n];
            int i;
            int loop=0;
            while((i=fichin.read(buffer))!=-1){
                loop++;
                File nueva=new File(f.getAbsolutePath()+loop);
                try(FileWriter fichOut=new FileWriter(nueva)){
                    String cad=new String(buffer,0,i);
                    for(int a=0;a<cad.length();a++){
                        fichOut.write(cad.charAt(a));
                        //Preguntar fichOut.write(System.getProperty("line.separator"));
                    }
                }
            }

        }
    }
    public void divCadenas(File f, int n) throws FileNotFoundException{
        try(Scanner sc=new Scanner(f)){
            int loop=0;
            int i=0;
            while(sc.hasNext()){

            }
        }
    }
}
