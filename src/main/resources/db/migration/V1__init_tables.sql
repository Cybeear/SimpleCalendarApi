CREATE TABLE events
(
    id          CHAR(36) PRIMARY KEY,
    title       TEXT      NOT NULL,
    description TEXT,
    start_time  TIMESTAMP NOT NULL,
    end_time    TIMESTAMP NOT NULL,
    location    TEXT
);

CREATE TABLE idempotency_key
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    idempotency_token VARCHAR(255) UNIQUE NOT NULL,
    entity_id         CHAR(36)            NOT NULL
);