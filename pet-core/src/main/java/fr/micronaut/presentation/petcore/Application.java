package fr.micronaut.presentation.petcore;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "Pet core",
        description = "Expose the pet data base"
    ),
    servers = {
        @Server(url = "http://localhost:8081", description = "development")
    }
)
public class Application {

  public static void main(String[] args) {
    Micronaut.run(Application.class);
  }
}
