ALTER TABLE book
    ADD COLUMN deleted_at TIMESTAMP,
ADD COLUMN deleted_by BIGINT;

ALTER TABLE library
    ADD COLUMN deleted_at TIMESTAMP,
ADD COLUMN deleted_by BIGINT;

ALTER TABLE book
    ADD CONSTRAINT fk_deleted_by_book
        FOREIGN KEY (deleted_by)
            REFERENCES _user(id);

ALTER TABLE library
    ADD CONSTRAINT fk_deleted_by_library
        FOREIGN KEY (deleted_by)
            REFERENCES _user(id);
