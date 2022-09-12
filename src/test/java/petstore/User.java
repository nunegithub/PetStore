package petstore;


import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class User {


    String uri = "https://petstore.swagger.io/v2/user";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));

    }
    @Test(priority = 1)
    public void incluirUser() throws IOException {
        String jsonBody = lerJson("db/user1.json");

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
                .body("type",is("unknown"))
                .body("message",is("1"))
        ;
    }
    @Test(priority = 2)
    public void consultarUser(){
        String userName = "j";
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + userName)
        .then()
                .log().all()
                .statusCode(200)
        ;

    }
    @Test(priority = 3)
    public void alterarUser() throws IOException {
        String jsonBody = lerJson("db/user2.json");

        String userName = "j";
        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .put(uri + "/" + userName)
        .then()
                .log().all()
                .statusCode(200)
                .body("type",is("unknown"))
                .body("message", is("2"))
        ;
    }

    @Test(priority = 4)
    public void excluirUser(){
        String userName = "b";
        given()
                .contentType("application/json")
                .log().all()
                .when()
                .delete(uri + "/" + userName)
                .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type",is("unknown"))
                //.body("message", is(Id))
        ;
    }

}
