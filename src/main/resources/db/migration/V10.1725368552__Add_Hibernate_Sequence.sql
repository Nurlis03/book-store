alter table public._user
    alter column id type bigint using id::bigint;

alter table public.book
    alter column id type bigint using id::bigint;

alter table public.library
    alter column id type bigint using id::bigint;

alter table public.book_history
    alter column id type bigint using id::bigint;

