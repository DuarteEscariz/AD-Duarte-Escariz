import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.net.ssl.HttpsURLConnection;

public class App {
    public static JsonValue leeJSON(String ruta){
        JsonReader reader=null;
        JsonValue jsonV=null;
        try {
            if (ruta.toLowerCase().startsWith("http://")){
                URL url = new URL(ruta);
                URLConnection hc = url.openConnection();
                hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:52.0) Gecko/20100101 Firefox/52.0");
                InputStream is = url.openStream();
                reader = Json.createReader(is);
            } else if (ruta.toLowerCase().startsWith("https://")){
                URL url = new URL(ruta);
                HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:52.0) Gecko/20100101 Firefox/52.0");
                InputStream is = conn.getInputStream();
                reader = Json.createReader(is);
            } else {
            reader = Json.createReader(new FileReader(ruta));
            }
            jsonV = reader.read();
        } catch (Exception e) {
            System.out.println("Error procesando documento Json"+e.getLocalizedMessage());
        }
        if (reader!=null) reader.close();
        return jsonV;
    }
    public static void nombreGenero(JsonObject personaje){
        System.out.println(personaje.getString("name")+" "+personaje.getString("gender"));
    }
    public static void descripccionCasa(JsonObject casa){
        System.out.println(casa.getString("name")+", "+casa.getString("region")+", "+casa.getString("coatOfArms"));
    }
    public static void datosLibro(JsonObject libro){
        System.out.println(libro.getString("name"));
        System.out.println(libro.getString("isbn"));
        JsonArray autores=libro.getJsonArray("authors");
        for (JsonValue jsonValue : autores) {
            System.out.println(jsonValue.toString());
        }
        System.out.println(libro.getInt("numberOfPages"));
        System.out.println(libro.getString("mediaType"));
        System.out.println(libro.getString("released"));
        System.out.println();
        
    }
    public static void comparaCasas(JsonObject casa1, JsonObject casa2){
        int max=casa1.getJsonArray("swornMembers").size();
        if(max>casa2.getJsonArray("swornMembers").size()){
            System.out.println("La casa más grande es: "+casa1.getString("name")+" con "+max+" miembros");
        }else{
            if(max==casa2.getJsonArray("swornMembers").size()){
                System.out.println("Las dos casas son iguales");
            }else{
                max=casa2.getJsonArray("swornMembers").size();
                System.out.println("La casa más grande es: "+casa2.getString("name")+" con "+max+" miembros");
            }
        }
    }
    public static void main(String[] args) throws Exception {

        //Ej14
        JsonObject jonSnow= (JsonObject) leeJSON("https://anapioficeandfire.com/api/characters/583");
        JsonObject dannyTarg= (JsonObject) leeJSON("https://anapioficeandfire.com/api/characters/271");
        nombreGenero(jonSnow);
        System.out.println();
        nombreGenero(dannyTarg);
        System.out.println();

        //Ej15
        JsonArray titulos=jonSnow.getJsonArray("titles");
        for (JsonValue jsonValue : titulos) {
            System.out.println(jsonValue.toString());
        }
        System.out.println();
        titulos=dannyTarg.getJsonArray("titles");
        for (JsonValue jsonValue : titulos) {
            System.out.println(jsonValue.toString());
        }

        //Ej16
        JsonArray apodos=jonSnow.getJsonArray("aliases");
        System.out.println("Jon Snow:");
        for (JsonValue jsonValue : apodos) {
            System.out.println(jsonValue.toString());
        }
        apodos=dannyTarg.getJsonArray("aliases");
        System.out.println("Daenerys Targarien");
        for (JsonValue jsonValue : apodos) {
            System.out.println(jsonValue.toString());
        }

        //EJ17
        JsonObject jonSpouse=(JsonObject) leeJSON(jonSnow.getString("spouse"));
        if(jonSpouse !=null){
            System.out.println(jonSpouse.getString("name"));
        }
        JsonObject daniSpouse=(JsonObject) leeJSON(dannyTarg.getString("spouse"));
        if(daniSpouse !=null){
            System.out.println(daniSpouse.getString("name"));
        }   

        //Ej18
        JsonObject casaStark=(JsonObject) leeJSON("https://anapioficeandfire.com/api/houses/362");
        JsonObject casaTarg=(JsonObject) leeJSON("https://anapioficeandfire.com/api/houses/378");
        descripccionCasa(casaStark);
        descripccionCasa(casaTarg);
        //Ej19
        JsonArray titles=casaStark.getJsonArray("titles");
        System.out.println("Casa Stark: ");
        for (JsonValue jsonValue : titles) {
            System.out.println(jsonValue.toString());
        }
        System.out.println();
        titles=casaTarg.getJsonArray("titles");
        System.out.println("Casa Targaryen:");
        for (JsonValue jsonValue : titles) {
            System.out.println(jsonValue.toString());
        }
        System.out.println();

        //Ej20
        JsonArray aliados=jonSnow.getJsonArray("allegiances");
        for (JsonValue jsonValue : aliados) {
            JsonObject casa=(JsonObject) leeJSON(jsonValue.toString());
            System.out.println(casa.getString("name"));
        }
        System.out.println();
        aliados=dannyTarg.getJsonArray("allegiances");
        for (JsonValue jsonValue : aliados) {
            JsonObject casa=(JsonObject) leeJSON(jsonValue.toString());
            System.out.println(casa.getString("name"));
        }
        System.out.println();

        //Ej21
        //Se puede reducir más el codigo
        JsonArray libros=jonSnow.getJsonArray("books");
        if(libros.size()>0){
            for (JsonValue jsonValue : libros) {
                JsonObject libro=(JsonObject) leeJSON(jsonValue.toString());
                datosLibro(libro);
            }
        }
        libros=jonSnow.getJsonArray("povBooks");
        if(libros.size()>0){
            for (JsonValue jsonValue : libros) {
                JsonObject libro=(JsonObject) leeJSON(jsonValue.toString());
                datosLibro(libro);
            }
        }
        libros=dannyTarg.getJsonArray("books");
        if(libros.size()>0){
            for (JsonValue jsonValue : libros) {
                JsonObject libro=(JsonObject) leeJSON(jsonValue.toString());
                datosLibro(libro);
            }
        }
        libros=dannyTarg.getJsonArray("povBooks");
        if(libros.size()>0){
            for (JsonValue jsonValue : libros) {
                JsonObject libro=(JsonObject) leeJSON(jsonValue.toString());
                datosLibro(libro);
            }
        }
        System.out.println();

        //Ej22
        JsonArray armas=casaStark.getJsonArray("ancestralWeapons");
        System.out.println("Casa Stark");
        for (JsonValue jsonValue : armas) {
            System.out.println(jsonValue.toString());
        }
        System.out.println();
        System.out.println("Casa Targaryen");
        armas=casaTarg.getJsonArray("ancestralWeapons");
        for (JsonValue jsonValue : armas) {
            System.out.println(jsonValue.toString());
        }
        System.out.println();

        //Ej23
        comparaCasas(casaStark, casaTarg);
    }
}

