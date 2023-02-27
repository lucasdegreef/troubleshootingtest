package tests;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class eurfinsTest {
    @Test
    public void checkEmail() throws UnirestException{
        HttpResponse<String> data = Unirest.get("https://reqres.in/api/users/5")
                .header("accept","application/json").asString();
        assertTrue(data.getBody().contains("charles.morris@reqres.in"));
        // doet een get request op de url haalt deze json string eruit en zet om naar string
        // checkt of mail zelfde is als in the json string.
    }
    @Test
    public void patchLastName() throws UnirestException {
        String verzendJson = "{\"name\": \"Lucas\", \"job\": \"Student\"}";

        HttpResponse<JsonNode> Json = Unirest.patch("https://reqres.in/api/users/2")
                .header("Content-Type", "application/json")
                .body(verzendJson)
                .asJson();


        assertEquals(200, Json.getStatus());

        String ontvangenJson = Json.getBody().getObject().toString();
        System.out.println(ontvangenJson);
        assertTrue(ontvangenJson.contains("Lucas"));
        assertTrue(ontvangenJson.contains("Student"));
    }
}
