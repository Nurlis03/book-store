CREATE TABLE IF NOT EXISTS aud.library_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,

    title VARCHAR(255),
    address VARCHAR(255),

    PRIMARY KEY (id, rev)
);

ALTER TABLE aud.library_aud
    ADD CONSTRAINT fk_library_aud_rev FOREIGN KEY (rev) REFERENCES aud.revinfo (rev);