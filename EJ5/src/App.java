import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

public class App {
    public static Document crearArbol(String ruta){
        Document doc=null;
        try {
            DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            DocumentBuilder builder=factory.newDocumentBuilder();
            doc=builder.parse(ruta);

        } catch (Exception e) {
            //TODO: handle exception
        }
        return doc;
    }
    public static void main(String[] args) throws Exception {
        Document arbol=crearArbol("C:/Users/FX505/Desktop/Recuperaciones/AD/liga.xml");
        Node raiz,temporada;
        NodeList eventos, clasificacion;
        raiz=arbol.getFirstChild();
        //Ej5
        System.out.println(arbol.getElementsByTagName("temporada").item(0).getFirstChild().getTextContent());
        
        //Ej6 
        eventos=arbol.getElementsByTagName("evento");
        System.out.println(eventos.getLength()+"");

        //Ej7
        NodeList fechas, visitantes,locales;
        Element evento;
        fechas=arbol.getElementsByTagName("fecha");
        visitantes=arbol.getElementsByTagName("equipolocal");
        locales=arbol.getElementsByTagName("equipovisitante");
        for (int i = 0; i < eventos.getLength(); i++) {
           System.out.println("Fecha: "+fechas.item(i).getFirstChild().getNodeValue()+" "+
           visitantes.item(i).getFirstChild().getNodeValue()+" vs "+
           locales.item(i).getFirstChild().getNodeValue());
        }
        //Ej8
        int max=0;
        String equipoGoleador="";
        NodeList golesVisitnantes, golesLocales, aux;
        Element padre;
        golesLocales=arbol.getElementsByTagName("resultadolocal");
        golesVisitnantes=arbol.getElementsByTagName("resultadovisitante");;
        for (int i = 0; i < eventos.getLength(); i++) {
            if(Integer.parseInt(golesLocales.item(i).getFirstChild().getNodeValue())>max){
                max=Integer.parseInt(golesLocales.item(i).getFirstChild().getNodeValue());
                padre=(Element) golesLocales.item(i).getParentNode();
                aux=padre.getElementsByTagName("equipolocal");
                if(aux.getLength()>0){
                    equipoGoleador=aux.item(0).getFirstChild().getNodeValue();
                }
                
            }
            if(Integer.parseInt(golesVisitnantes.item(i).getFirstChild().getNodeValue())>max){
                max=Integer.parseInt(golesVisitnantes.item(i).getFirstChild().getNodeValue());
                padre=(Element) golesVisitnantes.item(i).getParentNode();
                aux=padre.getElementsByTagName("equipolocal");
                if(aux.getLength()>0){
                    equipoGoleador=aux.item(0).getFirstChild().getNodeValue();
                }
                
            }
        }
        System.out.println("Equipo goleador:"+equipoGoleador+" Goles:"+max);

        //Ej9
        NodeList tvs;
        ArrayList<String> tvsArr=new ArrayList<String>();
        tvs=arbol.getElementsByTagName("tv");
        for (int i = 0; i <tvs.getLength(); i++) {
            if(!tvsArr.contains(tvs.item(i).getFirstChild().getNodeValue())){
                tvsArr.add(tvs.item(i).getFirstChild().getNodeValue());
            }
        }
        for (String string : tvsArr) {
            System.out.println(string);
        }
        //Ej10
        Element colista=null;
        String nombreColista;
        int colNum=0;
        NodeList ranking;
        ranking=arbol.getElementsByTagName("rank");
        for (int i = 0; i < ranking.getLength(); i++) {
            if(Integer.parseInt(ranking.item(i).getFirstChild().getNodeValue())>colNum){
                colNum=Integer.parseInt(ranking.item(i).getFirstChild().getNodeValue());
                colista=(Element) ranking.item(i).getParentNode();
            }
        }
        nombreColista=colista.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
        for (int i = 0; i < eventos.getLength(); i++) {
            if(visitantes.item(i).getFirstChild().getNodeValue().equals(nombreColista)){
                System.out.println("Fecha: "+fechas.item(i).getFirstChild().getNodeValue()+" "+
                visitantes.item(i).getFirstChild().getNodeValue()+" vs "+
                locales.item(i).getFirstChild().getNodeValue());
            }
            if(locales.item(i).getFirstChild().getNodeValue().equals(nombreColista)){
                System.out.println("Fecha: "+fechas.item(i).getFirstChild().getNodeValue()+" "+
                visitantes.item(i).getFirstChild().getNodeValue()+" vs "+
                locales.item(i).getFirstChild().getNodeValue());
            }
        }

        //Ej11
        NodeList empates;
        int maxEmp=0;
        String equipoEmpates="";
        empates=arbol.getElementsByTagName("drawn");
        for (int i = 0; i < empates.getLength(); i++) {
            if(Integer.parseInt(empates.item(i).getFirstChild().getNodeValue())>maxEmp){
                maxEmp=Integer.parseInt(empates.item(i).getFirstChild().getNodeValue());
                padre=(Element) empates.item(i).getParentNode();
                aux=padre.getElementsByTagName("name");
                if(aux.getLength()>0){
                    equipoEmpates=aux.item(0).getFirstChild().getNodeValue();
                }
            }
        }
        System.out.println("El equipo con más empates es: "+equipoEmpates+" "+maxEmp);

        //Ej12
        String equipoVis,equipoLoc;
        NodeList equipos=arbol.getElementsByTagName("name");
        equipoVis=visitantes.item(2).getFirstChild().getNodeValue();
        equipoLoc=locales.item(2).getFirstChild().getNodeValue();
        for (int i = 0; i < equipos.getLength(); i++) {
            if(equipos.item(i).getFirstChild().getNodeValue().equals(equipoLoc)){
                padre=(Element) equipos.item(i).getParentNode();
                System.out.println(equipoLoc+"rank:"+padre.getElementsByTagName("rank").item(0).getFirstChild().getNodeValue());
            }
            if(equipos.item(i).getFirstChild().getNodeValue().equals(equipoVis)){
                padre=(Element) equipos.item(i).getParentNode();
                System.out.println(equipoVis+" rank:"+padre.getElementsByTagName("rank").item(0).getFirstChild().getNodeValue());
            }
        }

    }
}
