package wisdom.zero.baseproject.controller.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import wisdom.zero.baseproject.controller.example.dto.request.ExampleRequestTO;
import wisdom.zero.baseproject.controller.example.dto.response.ExampleResponseTO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface IExampleController {

    @PostMapping(value = "/exampleRequest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    ExampleResponseTO exampleRequest(
            HttpServletRequest request,
            @Valid @RequestBody ExampleRequestTO exampleRequestTO
    );
}
