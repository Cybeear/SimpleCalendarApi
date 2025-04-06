CREATE TABLE events (
    id UUID PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    location TEXT
);

CREATE TABLE idempotency_key (
    id INTEGER PRIMARY KEY,
    key VARCHAR(255) UNIQUE,
    entityId UUID NOT NULL 
);