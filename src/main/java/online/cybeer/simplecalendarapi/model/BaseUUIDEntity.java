package online.cybeer.simplecalendarapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.sql.Types;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */

@Getter
@Setter
@MappedSuperclass
public class BaseUUIDEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(Types.VARCHAR)
    protected UUID id;
}
