import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.json.Json;
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
    public static void main(String[] args) throws Exception {
        JsonObject jonSnow= (JsonObject) leeJSON("http://anapioficeandfire.com/api/characters/583");
        JsonObject dannyTarg= (JsonObject) leeJSON("https://anapioficeandfire.com/api/characters/271");
        nombreGenero(jonSnow);
        nombreGenero(dannyTarg);
    }
}

