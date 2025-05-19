package wisdom.zero.baseproject.client.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@FeignClient(name = "ExampleClient", url = "http://default.url", configuration = ExampleClientConfig.class)
public interface ExampleClient {

    @PostMapping(name = "createSupervisionCenterAppointment", value = "/example1/{param}", produces = MediaType.APPLICATION_JSON_VALUE)
    void example1(
            @RequestHeader(value="DParametrizedHeaderName") String dynamicParametrizedHeader,
            @PathVariable("param") String test
    );

    @GetMapping(name = "updateSupervisionCenterAppointment", value = "/example2", produces = MediaType.APPLICATION_JSON_VALUE)
    void example2(
            URI overridesDefaultURL
    );

}
