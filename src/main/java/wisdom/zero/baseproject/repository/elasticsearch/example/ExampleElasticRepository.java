package wisdom.zero.baseproject.repository.elasticsearch.example;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import wisdom.zero.baseproject.repository.elasticsearch.example.dto.ExampleElasticEntity;

@Repository
public interface ExampleElasticRepository extends ElasticsearchRepository<ExampleElasticEntity, Long> {

}
