CREATE TABLE IF NOT EXISTS aud.book_aud (
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT,

    title VARCHAR(255),
    author VARCHAR(255),
    pages BIGINT,
    cost DOUBLE PRECISION,

    binding VARCHAR(255),
    book_status VARCHAR(255),
    library_id BIGINT,
    received_by_user_id BIGINT,

    PRIMARY KEY (id, rev)
);

CREATE TABLE IF NOT EXISTS aud.revinfo (
    rev BIGSERIAL PRIMARY KEY,
    revtimestamp BIGINT
);

ALTER TABLE aud.book_aud
    ADD CONSTRAINT fk_book_aud_rev FOREIGN KEY (rev) REFERENCES aud.revinfo (rev);
