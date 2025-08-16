package dto.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Dashboard {

    String name;
    @Builder.Default
    String description = "Common description";
}
