package wisdom.zero.baseproject.controller.example;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import wisdom.zero.baseproject.controller.example.dto.request.ExampleRequestTO;
import wisdom.zero.baseproject.controller.example.dto.response.ExampleResponseTO;
import wisdom.zero.baseproject.usecase.example.IExampleUseCase;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "Example")
@Controller
public class ExampleController implements IExampleController {

    @Autowired
    private IExampleUseCase exampleUseCase;

    @Override
    public ExampleResponseTO exampleRequest(HttpServletRequest request, ExampleRequestTO exampleRequestTO) {
        return exampleUseCase.executeExampleRequest(request, exampleRequestTO);
    }

}
