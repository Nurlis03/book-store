ALTER TABLE public.book
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP,
    ADD COLUMN IF NOT EXISTS created_by BIGINT,
    ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP,
    ADD COLUMN IF NOT EXISTS updated_by BIGINT;

ALTER TABLE public.library
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP,
    ADD COLUMN IF NOT EXISTS created_by BIGINT,
    ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP,
    ADD COLUMN IF NOT EXISTS updated_by BIGINT;


ALTER TABLE public._user
    ADD COLUMN IF NOT EXISTS created_at TIMESTAMP,
    ADD COLUMN IF NOT EXISTS created_by BIGINT,
    ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP,
    ADD COLUMN IF NOT EXISTS updated_by BIGINT,
    ADD COLUMN IF NOT EXISTS deleted_at TIMESTAMP,
    ADD COLUMN IF NOT EXISTS deleted_by BIGINT;

CREATE TABLE IF NOT EXISTS public.book_history (
    id SERIAL PRIMARY KEY,
    action_type VARCHAR(255) NOT NULL,
    performed_by_user_id BIGINT NOT NULL,
    action_date TIMESTAMP NOT NULL,
    comment TEXT,
    book_id BIGINT NOT NULL,
    FOREIGN KEY (performed_by_user_id) REFERENCES _user(id),
    FOREIGN KEY (book_id) REFERENCES book(id)
);