ALTER TABLE book
    ADD COLUMN title VARCHAR(255),
    ADD COLUMN book_status VARCHAR(255),
    ADD COLUMN library_id BIGINT,
    ADD COLUMN user_id BIGINT;

ALTER TABLE book
    ADD CONSTRAINT fk_library
        FOREIGN KEY (library_id)
            REFERENCES library(id),
    ADD CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES _user(id);
