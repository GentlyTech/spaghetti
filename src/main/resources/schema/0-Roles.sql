CREATE TABLE IF NOT EXISTS public.roles
(
    description text,
    name        text NOT NULL,
    CONSTRAINT roles_pk PRIMARY KEY (name)
);
