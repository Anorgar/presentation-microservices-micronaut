package fr.micronaut.presentation.petcore.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import fr.micronaut.presentation.common.domains.Pet;
import fr.micronaut.presentation.common.domains.Type;
import io.micronaut.http.HttpStatus;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MicronautTest
public class PetControllerTest {

  @Inject
  private EmbeddedServer server;

  @BeforeEach
  public void setUp() {
    RestAssured.port = server.getPort();
  }

  @Test
  public void should_find_by_name(){
    given()
        .when()
        .get("/pets/chat persan")
        .then()
        .statusCode(HttpStatus.OK.getCode())
        .body("id", equalTo(1),
            "type.id", equalTo(1),
            "type.type", equalTo("chat"),
            "stock", equalTo(17),
            "price", equalTo(255.99F),
            "name", equalTo("chat persan"));
  }

  @Test
  public void should_not_found_pet(){
    given()
        .when()
        .get("/pets/caniche")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.getCode())
        .body("message", equalTo("Pet caniche not found"),
            "status", equalTo(HttpStatus.NOT_FOUND.getCode()));
  }

  @Test
  public void should_save_and_find_all(){
    given()
        .when()
        .get("/pets")
        .then()
        .statusCode(HttpStatus.OK.getCode())
        .body("id.size()", equalTo(1),
            "id[0]", equalTo(1),
            "type[0].id", equalTo(1),
            "type[0].type", equalTo("chat"),
            "stock[0]", equalTo(17),
            "price[0]", equalTo(255.99F),
            "name[0]", equalTo("chat persan"));

    Pet maineCoon = Pet.builder()
        .type(Type.builder().id(1).type("chat").build())
        .stock(12)
        .price(499.99D)
        .name("Maine Coon")
        .build();

    given()
        .when()
        .contentType(ContentType.JSON)
        .body(maineCoon)
        .post("/pets")
        .then()
        .statusCode(HttpStatus.OK.getCode())
        .body("id", equalTo(2),
            "type.id", equalTo(1),
            "type.type", equalTo("chat"),
            "stock", equalTo(12),
            "price", equalTo(499.99F),
            "name", equalTo("Maine Coon"));

    given()
        .when()
        .get("/pets")
        .then()
        .statusCode(HttpStatus.OK.getCode())
        .body("id.size()", equalTo(2),
            "id[0]", equalTo(1),
            "type[0].id", equalTo(1),
            "type[0].type", equalTo("chat"),
            "stock[0]", equalTo(17),
            "price[0]", equalTo(255.99F),
            "name[0]", equalTo("chat persan"),
            "id[1]", equalTo(2),
            "type[1].id", equalTo(1),
            "type[1].type", equalTo("chat"),
            "stock[1]", equalTo(12),
            "price[1]", equalTo(499.99F),
            "name[1]", equalTo("Maine Coon"));
  }

}
