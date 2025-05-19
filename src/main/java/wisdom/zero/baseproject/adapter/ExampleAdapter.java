package wisdom.zero.baseproject.adapter;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.TypeMappingOptions;
import org.springframework.stereotype.Component;
import wisdom.zero.baseproject.controller.example.dto.request.ExampleRequestTO;
import wisdom.zero.baseproject.controller.example.dto.response.ExampleResponseTO;
import wisdom.zero.baseproject.repository.jpa.example.ExampleEntity;
import wisdom.zero.baseproject.repository.jpa.example.ExampleRepository;

@Component
public class ExampleAdapter {

    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    private static final Mapper merger = DozerBeanMapperBuilder.create().withMappingBuilder(new BeanMappingBuilder() {protected void configure() {
        mapping(ExampleRequestTO.class, ExampleEntity.class, TypeMappingOptions.mapNull(false));}}).build();

    public ExampleEntity mapExampleRequestToExampleEntity(ExampleRequestTO exampleRequestTO) {
        return mapper.map(exampleRequestTO, ExampleEntity.class);
    }

    public ExampleResponseTO mapExampleEntityToExampleResponse(ExampleEntity exampleEntity) {
        return mapper.map(exampleEntity, ExampleResponseTO.class);
    }

    public ExampleEntity mergeExampleRequestWithExampleEntity(ExampleRequestTO exampleRequestTO, ExampleEntity exampleEntity) {
        merger.map(exampleRequestTO, exampleEntity);
        return exampleEntity;
    }
}
