package online.cybeer.simplecalendarapi.dto.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/05
 */
@Data
public class EventRequest {
    
    @UUID
    @NotBlank
    private String id;

    @Size(max = 255)
    private String title;

    @Size(max = 255)
    private String description;
    
    @NotBlank
    private String startTime;
    @NotBlank
    private String endTime;

    @Size(max = 255)
    private String location;
    
    @UUID
    private String userId;
}
