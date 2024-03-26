CREATE TABLE IF NOT EXISTS public.hotel
(
    hotel_id   serial  NOT NULL,
    rating     integer NOT NULL,
    hotel_name text,
    owner      text,
    CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id),
    CONSTRAINT hotel_chain_fk FOREIGN KEY (owner)
        REFERENCES public.hotel_chain (chain_name)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.hotel_contacts
(
    hotel_id   integer NOT NULL,
    contact_id integer NOT NULL,
    CONSTRAINT hotel_id_fk FOREIGN KEY (hotel_id) REFERENCES public.hotel (hotel_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
    CONSTRAINT contact_id_fk FOREIGN KEY (contact_id) REFERENCES public.contacts (contact_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.hotel_addresses
(
    hotel_id   int     NOT NULL,
    address_id integer NOT NULL,
    CONSTRAINT hotel_id_fk FOREIGN KEY (hotel_id) REFERENCES public.hotel (hotel_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
    CONSTRAINT address_id_fk FOREIGN KEY (address_id) REFERENCES public.addresses (address_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.hotel_amenities
(
    hotel_id    integer NOT NULL,
    description text,
    name        text    NOT NULL,
    CONSTRAINT hotel_id_fk FOREIGN KEY (hotel_id)
        REFERENCES public.hotel (hotel_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);
