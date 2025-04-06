package online.cybeer.simplecalendarapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private int status;
    private String message;
}
