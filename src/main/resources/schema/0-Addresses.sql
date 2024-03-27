CREATE TABLE IF NOT EXISTS public.addresses
(
    address_id serial NOT NULL,
    street text,
    city text,
    province text,
    postal_code text,
    country text,
    CONSTRAINT addresses_pk PRIMARY KEY (address_id)
);
