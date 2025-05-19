package wisdom.zero.baseproject.repository.jpa.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ExampleRepository extends JpaRepository<ExampleEntity, String> {
    Optional<ExampleEntity> findByIdAndDeletedAtIsNull(String exampleId);
}
