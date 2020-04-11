package fr.micronaut.presentation.petcore.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import fr.micronaut.presentation.petcore.domains.dtos.PetDTO;
import fr.micronaut.presentation.petcore.domains.dtos.TypeDTO;
import io.micronaut.http.HttpStatus;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MicronautTest
public class TypeControllerTest {

  @Inject
  private EmbeddedServer server;

  @BeforeEach
  public void setUp() {
    RestAssured.port = server.getPort();
  }

  @Test
  public void should_save_and_find_all(){
    given()
        .when()
        .get("/pet/type")
        .then()
        .statusCode(HttpStatus.OK.getCode())
        .body("id.size()", equalTo(1),
            "id[0]", equalTo(1),
            "type[0]", equalTo("chat"));

    TypeDTO.builder().type("chien").build();
    PetDTO maineCoon = PetDTO.builder()
        .type(TypeDTO.builder().id(1).type("chat").build())
        .number(12)
        .price(499.99D)
        .name("Maine Coon")
        .build();

    given()
        .when()
        .contentType(ContentType.JSON)
        .body(TypeDTO.builder().type("chien").build())
        .post("/pet/type")
        .then()
        .statusCode(HttpStatus.OK.getCode())
        .body("id", equalTo(2),
            "type", equalTo("chien"));

    given()
        .when()
        .get("/pet/type")
        .then()
        .statusCode(HttpStatus.OK.getCode())
        .body("id.size()", equalTo(2),
            "id[0]", equalTo(1),
            "type[0]", equalTo("chat"),
            "id[1]", equalTo(2),
            "type[1]", equalTo("chien"));
  }

}
