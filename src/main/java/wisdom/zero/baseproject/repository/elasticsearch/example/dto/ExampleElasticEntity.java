package wisdom.zero.baseproject.repository.elasticsearch.example.dto;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "example")
public class ExampleElasticEntity {

    @Id
    private String id;

    private String name;
}
