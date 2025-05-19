package wisdom.zero.baseproject.util.dto.generic;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorResponseTO {
    private HttpStatus status;
    private Object error;
}
