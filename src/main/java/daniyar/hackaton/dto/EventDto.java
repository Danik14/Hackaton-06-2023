package daniyar.hackaton.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    @Length(min = 3, max = 50)
    @NotNull
    private String name;

    @Length(min = 3, max = 1000)
    @NotNull
    private String description;

    @Max(1000_000)
    @NotNull
    private Long price;

    @Max(1000_000)
    @NotNull
    private Long fee;

    @Min(1)
    @NotNull

    private int min_people;

    @Max(10000)
    @NotNull
    private int max_people;

    @NotNull
    private List<String> cases;

    @NotNull
    private boolean isPrivate;
}
