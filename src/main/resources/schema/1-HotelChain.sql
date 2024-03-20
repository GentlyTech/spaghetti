CREATE TABLE IF NOT EXISTS public.hotel_chain
(
    chain_name text NOT NULL,
    CONSTRAINT hotel_chain_pkey PRIMARY KEY (chain_name)
);

CREATE TABLE IF NOT EXISTS public.hotel_chain_addresses
(
    chain_name text,
    city text NOT NULL,
    country text NOT NULL,
    postal_code text NOT NULL,
    province text NOT NULL,
    street text NOT NULL,
    CONSTRAINT hotel_chain_addresses_pkey PRIMARY KEY (city, country, postal_code, province, street),
    CONSTRAINT fkf4jlty6bcqu25gp8afyp4uuhb FOREIGN KEY (chain_name)
        REFERENCES public.hotel_chain (chain_name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.hotel_chain_contacts
(
    chain_name text,
    email text NOT NULL,
    name text NOT NULL,
    phone_number text NOT NULL,
    CONSTRAINT hotel_chain_contacts_pkey PRIMARY KEY (email, name, phone_number),
    CONSTRAINT fk67vfv8vasjlhdb8h8vqas2bdd FOREIGN KEY (chain_name)
        REFERENCES public.hotel_chain (chain_name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
