CREATE TABLE IF NOT EXISTS public.hotel_chain
(
    chain_name text NOT NULL,
    CONSTRAINT hotel_chain_pkey PRIMARY KEY (chain_name)
);

CREATE TABLE IF NOT EXISTS public.hotel_chain_contacts
(
    chain_name text   NOT NULL,
    contact_id integer NOT NULL,
    CONSTRAINT chain_name_fk FOREIGN KEY (chain_name) REFERENCES public.hotel_chain (chain_name)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
    CONSTRAINT contact_id_fk FOREIGN KEY (contact_id) REFERENCES public.contacts (contact_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.hotel_chain_addresses
(
    chain_name text   NOT NULL,
    address_id integer NOT NULL,
    CONSTRAINT chain_name_fk FOREIGN KEY (chain_name) REFERENCES public.hotel_chain (chain_name)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
    CONSTRAINT address_id_fk FOREIGN KEY (address_id) REFERENCES public.addresses (address_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);