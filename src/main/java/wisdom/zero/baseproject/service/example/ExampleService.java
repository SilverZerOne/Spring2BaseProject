package wisdom.zero.baseproject.service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisdom.zero.baseproject.repository.jpa.example.ExampleEntity;
import wisdom.zero.baseproject.repository.jpa.example.ExampleRepository;

import javax.management.InstanceNotFoundException;

@Service
public class ExampleService implements IExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    @Override
    public void delete(ExampleEntity exampleEntity) {
        exampleRepository.delete(exampleEntity);
    }

    @Override
    public ExampleEntity findByIdAndDeletedAtIsNull(String exampleId) throws InstanceNotFoundException {

        var exampleEntity = exampleRepository.findByIdAndDeletedAtIsNull(exampleId);

        if (exampleEntity.isEmpty()) {
            throw new InstanceNotFoundException("Example " + exampleId + " does not exist");
        }

        return exampleEntity.get();

    }

    @Override
    public ExampleEntity save(ExampleEntity exampleEntity) {
        return exampleRepository.save(exampleEntity);
    }
}
