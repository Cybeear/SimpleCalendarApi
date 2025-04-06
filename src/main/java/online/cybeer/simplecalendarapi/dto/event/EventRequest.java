package online.cybeer.simplecalendarapi.dto.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import online.cybeer.simplecalendarapi.validation.EventDatesValidator;
import online.cybeer.simplecalendarapi.validation.XSSValidator;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
@Data
@EventDatesValidator
public class EventRequest {

    @NotBlank
    @XSSValidator
    private String title;

    @XSSValidator
    private String description;

    @NotNull
    private Long startTimestamp;

    @NotNull
    private Long endTimestamp;

    @XSSValidator
    private String location;
}
