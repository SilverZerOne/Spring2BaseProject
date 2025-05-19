package wisdom.zero.baseproject.usecase.example;

import wisdom.zero.baseproject.controller.example.dto.request.ExampleRequestTO;
import wisdom.zero.baseproject.controller.example.dto.response.ExampleResponseTO;
import javax.servlet.http.HttpServletRequest;

public interface IExampleUseCase {

    ExampleResponseTO executeExampleRequest(HttpServletRequest request, ExampleRequestTO exampleRequestTO);
}
