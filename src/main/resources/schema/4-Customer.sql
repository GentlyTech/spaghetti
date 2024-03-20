CREATE TABLE IF NOT EXISTS public.customer
(
    id serial NOT NULL,
    creation_date text,
    id_type text,
    name text,
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.customer_addresses
(
    customer_id bigint,
    city text NOT NULL,
    country text NOT NULL,
    postal_code text NOT NULL,
    province text NOT NULL,
    street text NOT NULL,
    CONSTRAINT customer_addresses_pkey PRIMARY KEY (city, country, postal_code, province, street),
    CONSTRAINT customer_addresses_customer_id_key UNIQUE (customer_id),
    CONSTRAINT fkt5007akl8ydn1dskefawemfw0 FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
