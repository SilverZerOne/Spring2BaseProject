package wisdom.zero.baseproject.service.example;

import wisdom.zero.baseproject.repository.jpa.example.ExampleEntity;
import javax.management.InstanceNotFoundException;

public interface IExampleService {

    void delete(ExampleEntity exampleEntity);

    ExampleEntity findByIdAndDeletedAtIsNull(String exampleId) throws InstanceNotFoundException;

    ExampleEntity save(ExampleEntity exampleEntity);
}
