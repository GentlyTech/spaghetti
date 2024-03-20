CREATE TABLE IF NOT EXISTS public.customer
(
    id serial NOT NULL,
    creation_date character varying(255) COLLATE pg_catalog."default",
    id_type character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.customer_addresses
(
    customer_id bigint,
    city character varying(255) COLLATE pg_catalog."default" NOT NULL,
    country character varying(255) COLLATE pg_catalog."default" NOT NULL,
    postal_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    province character varying(255) COLLATE pg_catalog."default" NOT NULL,
    street character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT customer_addresses_pkey PRIMARY KEY (city, country, postal_code, province, street),
    CONSTRAINT customer_addresses_customer_id_key UNIQUE (customer_id),
    CONSTRAINT fkt5007akl8ydn1dskefawemfw0 FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
