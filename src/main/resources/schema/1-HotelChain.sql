CREATE TABLE IF NOT EXISTS public.hotel_chain
(
    chain_name text NOT NULL,
    CONSTRAINT hotel_chain_pkey PRIMARY KEY (chain_name)
);

CREATE TABLE IF NOT EXISTS public.hotel_chain_addresses
(
    chain_name character varying(255) COLLATE pg_catalog."default",
    city character varying(255) COLLATE pg_catalog."default" NOT NULL,
    country character varying(255) COLLATE pg_catalog."default" NOT NULL,
    postal_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    province character varying(255) COLLATE pg_catalog."default" NOT NULL,
    street character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT hotel_chain_addresses_pkey PRIMARY KEY (city, country, postal_code, province, street),
    CONSTRAINT fkf4jlty6bcqu25gp8afyp4uuhb FOREIGN KEY (chain_name)
        REFERENCES public.hotel_chain (chain_name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.hotel_chain_contacts
(
    chain_name character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone_number character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT hotel_chain_contacts_pkey PRIMARY KEY (email, name, phone_number),
    CONSTRAINT fk67vfv8vasjlhdb8h8vqas2bdd FOREIGN KEY (chain_name)
        REFERENCES public.hotel_chain (chain_name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
