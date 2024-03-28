CREATE TABLE IF NOT EXISTS public.addresses
(
    address_id serial NOT NULL,
    alias text,
    street text,
    city text,
    province text,
    postal_code text,
    country text,
    CONSTRAINT addresses_pk PRIMARY KEY (address_id)
);
