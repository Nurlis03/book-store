ALTER TABLE public.book
    ADD COLUMN IF NOT EXISTS title VARCHAR(255),
    ADD COLUMN IF NOT EXISTS book_status VARCHAR(255),
    ADD COLUMN IF NOT EXISTS library_id BIGINT,
    ADD COLUMN IF NOT EXISTS user_id BIGINT;

DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_library') THEN
            ALTER TABLE public.book
                ADD CONSTRAINT fk_library
                    FOREIGN KEY (library_id)
                        REFERENCES public.library(id);
        END IF;

        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_user') THEN
            ALTER TABLE public.book
                ADD CONSTRAINT fk_user
                    FOREIGN KEY (user_id)
                        REFERENCES public._user(id);
        END IF;
    END $$;
