CREATE TABLE IF NOT EXISTS public.hotel
(
    hotel_id bigserial NOT NULL,
    rating integer NOT NULL,
    hotel_name text,
    owner text,
    CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id),
    CONSTRAINT fkh6wsbhrtqg2kerj3cxor0h954 FOREIGN KEY (owner)
        REFERENCES public.hotel_chain (chain_name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.hotel_addresses
(
    hotel_id bigint,
    city text NOT NULL,
    country text NOT NULL,
    postal_code text NOT NULL,
    province text NOT NULL,
    street text NOT NULL,
    CONSTRAINT hotel_addresses_pkey PRIMARY KEY (city, country, postal_code, province, street),
    CONSTRAINT hotel_addresses_hotel_id_key UNIQUE (hotel_id),
    CONSTRAINT fk58vw5xw9xaj08vvy71c41vw4j FOREIGN KEY (hotel_id)
        REFERENCES public.hotel (hotel_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.hotel_amenities
(
    hotel_id bigint NOT NULL,
    description text,
    name text NOT NULL,
    CONSTRAINT fk6nh3bf7h7byp48bw1dmw7k89i FOREIGN KEY (hotel_id)
        REFERENCES public.hotel (hotel_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.hotel_contacts
(
    hotel_id bigint,
    email text NOT NULL,
    name text NOT NULL,
    phone_number text NOT NULL,
    CONSTRAINT hotel_contacts_pkey PRIMARY KEY (email, name, phone_number),
    CONSTRAINT fkig4lh2vmm0eiqfu5llc1ycofu FOREIGN KEY (hotel_id)
        REFERENCES public.hotel (hotel_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
