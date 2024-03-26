CREATE TABLE IF NOT EXISTS public.addresses
(
    address_id serial NOT NULL,
    city text,
    country text,
    postal_code text,
    province text,
    street text,
    CONSTRAINT addresses_pkey PRIMARY KEY (address_id)
);
