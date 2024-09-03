ALTER TABLE public.book DROP CONSTRAINT IF EXISTS fk_library;

ALTER TABLE public.book
    ADD CONSTRAINT fk_library
        FOREIGN KEY (library_id)
            REFERENCES public.library(id)
            ON DELETE SET NULL;