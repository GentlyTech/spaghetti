CREATE TABLE IF NOT EXISTS public.hotel
(
    hotel_id   bigserial NOT NULL,
    rating     integer   NOT NULL,
    hotel_name text,
    owner      text,
    CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id),
    CONSTRAINT fkh6wsbhrtqg2kerj3cxor0h954 FOREIGN KEY (owner)
        REFERENCES public.hotel_chain (chain_name) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.hotel_contacts
(
    hotel_id   bigint   NOT NULL,
    contact_id serial NOT NULL,
    CONSTRAINT hotel_id_fk FOREIGN KEY (hotel_id) REFERENCES public.hotel (hotel_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
    CONSTRAINT contact_id_fk FOREIGN KEY (contact_id) REFERENCES public.contacts (contact_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.hotel_addresses
(
    hotel_id   bigint   NOT NULL,
    address_id serial NOT NULL,
    CONSTRAINT hotel_id_fk FOREIGN KEY (hotel_id) REFERENCES public.hotel (hotel_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
    CONSTRAINT address_id_fk FOREIGN KEY (address_id) REFERENCES public.addresses (address_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.hotel_amenities
(
    hotel_id    bigint NOT NULL,
    description text,
    name        text   NOT NULL,
    CONSTRAINT fk6nh3bf7h7byp48bw1dmw7k89i FOREIGN KEY (hotel_id)
        REFERENCES public.hotel (hotel_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
