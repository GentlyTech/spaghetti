CREATE TABLE IF NOT EXISTS public.roles
(
    name        text NOT NULL,
    description text,
    CONSTRAINT roles_pk PRIMARY KEY (name)
);
