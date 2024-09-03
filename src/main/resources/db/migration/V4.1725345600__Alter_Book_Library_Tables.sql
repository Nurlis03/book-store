ALTER TABLE public.book
    ADD COLUMN IF NOT EXISTS deleted_at TIMESTAMP,
    ADD COLUMN IF NOT EXISTS deleted_by BIGINT;

ALTER TABLE public.library
    ADD COLUMN IF NOT EXISTS deleted_at TIMESTAMP,
    ADD COLUMN IF NOT EXISTS deleted_by BIGINT;

DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_deleted_by_book') THEN
            ALTER TABLE public.book
                ADD CONSTRAINT fk_deleted_by_book
                    FOREIGN KEY (deleted_by)
                        REFERENCES public._user(id);
        END IF;
    END $$;

DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_deleted_by_library') THEN
            ALTER TABLE public.library
                ADD CONSTRAINT fk_deleted_by_library
                    FOREIGN KEY (deleted_by)
                        REFERENCES public._user(id);
        END IF;
    END $$;
