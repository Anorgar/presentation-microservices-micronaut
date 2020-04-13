package fr.micronaut.presentation.petapi.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import fr.micronaut.presentation.common.domains.Pet;
import fr.micronaut.presentation.common.domains.Type;
import fr.micronaut.presentation.petapi.clients.PetClient;
import io.micronaut.http.HttpStatus;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import io.restassured.RestAssured;
import java.util.Collections;
import java.util.Optional;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MicronautTest
public class PetControllerTest {

  public static final PetClient PET_CLIENT = mock(PetClient.class);

  @Inject
  private EmbeddedServer server;

  @MockBean(PetClient.class)
  public PetClient getPetClient(){
    return PET_CLIENT;
  }

  @BeforeEach
  public void setUp() {
    RestAssured.port = server.getPort();
  }

  @Test
  public void should_find_by_name(){
    doReturn(Optional.of(buildCat()))
        .when(PET_CLIENT).findByName(eq("chat persan"));

    given()
        .when()
        .get("/pet/chat persan")
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
  public void should_not_find_by_name(){
    doReturn(Optional.empty())
        .when(PET_CLIENT).findByName(eq("caniche"));

    given()
        .when()
        .get("/pet/caniche")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.getCode())
        .body("message", equalTo("Pet caniche not found"));
  }

  private Pet buildCat() {
    return Pet.builder()
        .id(1)
        .name("chat persan")
        .type(buildCatType())
        .stock(17)
        .price(255.99)
        .build();
  }

  private Type buildCatType() {
    return Type.builder().id(1).type("chat").build();
  }
}
