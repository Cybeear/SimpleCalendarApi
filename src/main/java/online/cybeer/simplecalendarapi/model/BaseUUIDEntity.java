package online.cybeer.simplecalendarapi.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/05
 */

@Getter
@Setter
@MappedSuperclass
public class BaseUUIDEntity {

    @Id
    protected UUID id;
}
