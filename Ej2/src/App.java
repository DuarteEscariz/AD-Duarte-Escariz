import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.plaf.FileChooserUI;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
    public void copiarSinBuff(File fileIn, File fileOut) throws IOException{
            try(FileInputStream in = new FileInputStream(fileIn);
            FileOutputStream out= new FileOutputStream(fileOut)){
                 int c;
                 int i=0;
                 long tamaño=Files.size(fileIn.toPath());
                 int percent=-1;
                 while((c=in.read()) !=-1){
                     i++;
                    out.write(c);
                    if(i/(int)tamaño*100!=percent){
                        percent=i/(int)tamaño*100;
                        System.out.println(percent+"%");
                    }
                 }
            }
        }
        public void copiarBuff(File fileIn,File fileOut, int buff)throws IOException{
            try(FileInputStream in =new FileInputStream(fileIn);
            FileOutputStream out = new FileOutputStream(fileOut)){
                int i;
                byte[] buffer= new byte[buff];
                long tamaño=Files.size(fileIn.toPath());
                 int percent=-1;
                while( (i = in.read(buffer)) !=-1){
                    out.write(buffer, 0, i);
                }
            }
        }
}
