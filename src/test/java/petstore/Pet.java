package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;

public class Pet {
    String uri = "https://petstore.swagger.io/v2/pet"; //endereço da entidade Pet

    //método para realizar a leitura do arquivo JSON
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson))); // variável caminhoJson retornada em forma de String para mostrar o resultado da leitura do arquivo Json
    }

    //Incluir - Create - POST
    @Test //identifica o método ou função como um teste para o framework TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        //sintaxe Gherkin
        // Dado - Quando - Então
        // Given - When - Then

        given() //dado
                .contentType("application/json") //comum em APIs REST - antigas eram "txt/xml"
                .log().all()
                .body(jsonBody)

        .when() //quando
                .post(uri)
        .then() //então
                .log().all()
                .statusCode(200)
        ;
    }
}
