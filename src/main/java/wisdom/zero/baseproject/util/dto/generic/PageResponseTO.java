package wisdom.zero.baseproject.util.dto.generic;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class PageResponseTO<T> {
        @Builder.Default
        private List<T> items = new ArrayList<>();
        Long total;
}
