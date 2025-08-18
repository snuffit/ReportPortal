package dto.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Widget {

    @Builder.Default
    String type = "Launch statistics chart";
    @Builder.Default
    String filter = "filter";
    String name;
    @Builder.Default
    String description = "Common description";
}
