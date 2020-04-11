package fr.micronaut.presentation.petapi;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "Pet shop",
        description = "Expose a small pet shop in micronaut microservices"
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "development")
    }
)
public class Application {

  public static void main(String[] args) {
    Micronaut.run(Application.class);
  }
}
