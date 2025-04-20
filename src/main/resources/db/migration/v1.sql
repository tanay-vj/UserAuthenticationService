CREATE TABLE user
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    created_at      datetime NULL,
    last_updated_at datetime NULL,
    state           SMALLINT NULL,
    email_id        VARCHAR(255) NULL,
    password        VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);