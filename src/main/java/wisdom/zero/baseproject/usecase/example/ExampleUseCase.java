package wisdom.zero.baseproject.usecase.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wisdom.zero.baseproject.adapter.ExampleAdapter;
import wisdom.zero.baseproject.controller.example.dto.request.ExampleRequestTO;
import wisdom.zero.baseproject.controller.example.dto.response.ExampleResponseTO;
import wisdom.zero.baseproject.service.example.IExampleService;
import javax.servlet.http.HttpServletRequest;

@Component
public class ExampleUseCase implements IExampleUseCase {

    @Autowired
    private IExampleService exampleService;

    @Autowired
    private ExampleAdapter exampleAdapter;

    @Override
    public ExampleResponseTO executeExampleRequest(HttpServletRequest request, ExampleRequestTO exampleRequestTO) {
        var exampleEntity = exampleAdapter.mapExampleRequestToExampleEntity(exampleRequestTO);
        exampleEntity = exampleService.save(exampleEntity);
        return exampleAdapter.mapExampleEntityToExampleResponse(exampleEntity);
    }

}
