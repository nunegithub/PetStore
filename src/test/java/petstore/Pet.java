package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class Pet {

    String uri = "https://petstore.swagger.io/v2/pet";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));

    }
    @Test(priority = 1)
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        //Dado Quando Entao

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Snoopy"))
                .body("status", is("available"))
                .body("id", is(777))
                .body("tags.id", contains(2021))
                .body("tags.name",contains("sta"))
                .body("category.name", is("dog"))
        ;
    }
   @Test(priority = 2)
    public void consultarPet(){
        String petId = "777";
        given()
                .contentType("application/json")
                .log().all()
        .when()
                 .get(uri + "/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Snoopy"))
                .body("tags.id", contains(2021))
                .body("category.name", is("dog"))
                .body("category.id", is(1))

        ;

    }
}

