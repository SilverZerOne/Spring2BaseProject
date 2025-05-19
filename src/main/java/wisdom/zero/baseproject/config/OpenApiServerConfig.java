package wisdom.zero.baseproject.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiServerConfig {

    @Bean
    public OpenAPI openAPI(
            @Value("${server.servlet.context-path:}") String contextPath
    ) {
        return new OpenAPI()
                .addServersItem(new Server().url(contextPath).description("Enviroment URL"))
                .info(new Info().title("url").description("url for testing endpoints"))
                .components(new Components()
                        .addSecuritySchemes("cookieSchema",
                                new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.COOKIE).name("wZs")));
    }

}
