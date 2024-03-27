CREATE TABLE IF NOT EXISTS public.user
(
    user_id       serial NOT NULL,
    username      text   NOT NULL,
    password_hash text   NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (user_id)
);
