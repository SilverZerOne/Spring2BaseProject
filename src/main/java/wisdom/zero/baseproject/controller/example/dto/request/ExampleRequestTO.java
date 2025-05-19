package wisdom.zero.baseproject.controller.example.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExampleRequestTO {

        @NotNull(message = "name cannot be null")
        String name;

}