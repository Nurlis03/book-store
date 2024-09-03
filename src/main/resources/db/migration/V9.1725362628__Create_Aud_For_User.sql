CREATE TABLE IF NOT EXISTS aud._user_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,

    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255),

    PRIMARY KEY (id, rev)
);

ALTER TABLE aud._user_aud
    ADD CONSTRAINT fk_user_aud_rev FOREIGN KEY (rev) REFERENCES aud.revinfo (rev);