package petstore;

import io.restassured.response.ValidatableResponse;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class Store {


    String uri = "https://petstore.swagger.io/v2/store/order";

    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    @Test(priority = 1)
    public void incluirLoja() throws IOException {
        String jsonBody = lerJson("db/loja1.json");

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)
        .when()
                .post(uri)
        .then()
                .log().all()
                .statusCode(200)
              // .body("id",is(1))
               //.body("status", is("placed"))
        ;
    }


    @Test(priority = 2)
    public void incluirInventario() throws IOException {
        String jsonBody = lerJson("db/inventario1.json");

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
    @Test(priority = 3)
    public void consultarLoja(){
       //String uri = "https://petstore.swagger.io/v2/store/order";
        String id = "1";
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/" + id)
        .then()
                .log().all()
                .statusCode(200)

               // .body("quantity", is(5))
                ;
    }

    @Test(priority = 4)
    public void consultarInventario(){
       String uri = "https://petstore.swagger.io/v2/store/inventory";

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri)
        .then()
                .log().all()
                .statusCode(200)
                ;
    }

     @Test(priority = 5)
    public void excluirLoja(){
        String id = "1";
        given()
                .contentType("application/json")
                .log().all()
        .when()
                .delete(uri + "/" + id)
        .then()
                .log().all()
                .statusCode(200)
                .body("code", is(200))
                .body("type",is("unknown"))
                .body("message", is(id))
        ;
    }
}
