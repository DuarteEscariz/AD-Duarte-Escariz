import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        File prueba=new File("C:/Users/Duarte/Desktop/AD/Pruebas/a.txt");
        divCadenas(prueba,2);
        
    }
    public static void divChar(File f, int n) throws IOException{
        try(FileReader fichin=new FileReader(f)){
            char buffer[]=new char[n];
            int i;
            int loop=0;
            while((i=fichin.read(buffer))!=-1){
                loop++;
                File nueva=new File(f.getParentFile().getAbsolutePath()+"/"+"copia"+loop+".txt" );
                try(FileWriter fichOut=new FileWriter(nueva)){
                    fichOut.write(buffer,0,i);
                }
            }

        }
    }
    public static void divCadenas(File f, int n) throws FileNotFoundException{
        try(Scanner sc=new Scanner(f)){
            int loop=0;
            int i=0;
            while(sc.hasNext()){
                try(PrintWriter pw= new PrintWriter(new File(f.getParentFile().getAbsolutePath()+"/"+"copia"+(loop+1)+".txt" ))){
                    for (int index = 0; index < n && sc.hasNext() ; index++) {
                       
                            pw.println(sc.nextLine());
                        
                    }
                }
                loop++;
            }
        }
    }
    public static void unir(File[] as) throws FileNotFoundException{
        try(PrintWriter pw=new PrintWriter(new File(as[0].getParentFile().getAbsolutePath()+"/union"))){
                for (File file : as) {
                    try(Scanner sc=new Scanner(file)){
                        while(sc.hasNext()){
                            pw.println(sc.nextLine());
                        }
                    }
                }
        }
    }
}

