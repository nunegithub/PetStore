package petstore;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Pet {

    String uri = "https://petstore.swagger.io/v2/pet";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));

    }
    @Test
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        //Dado Quando Entao

        RestAssured.
        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
        ;



    }
}

