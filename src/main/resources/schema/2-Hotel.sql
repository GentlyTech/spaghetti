CREATE TABLE IF NOT EXISTS public.hotel
(
    hotel_id bigserial NOT NULL,
    rating integer NOT NULL,
    hotel_name character varying(255) COLLATE pg_catalog."default",
    owner character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id),
    CONSTRAINT fkh6wsbhrtqg2kerj3cxor0h954 FOREIGN KEY (owner)
        REFERENCES public.hotel_chain (chain_name) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.hotel_addresses
(
    hotel_id bigint,
    city character varying(255) COLLATE pg_catalog."default" NOT NULL,
    country character varying(255) COLLATE pg_catalog."default" NOT NULL,
    postal_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    province character varying(255) COLLATE pg_catalog."default" NOT NULL,
    street character varying(255) COLLATE pg_catalog."default" NOT NULL,
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
    description character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT fk6nh3bf7h7byp48bw1dmw7k89i FOREIGN KEY (hotel_id)
        REFERENCES public.hotel (hotel_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.hotel_contacts
(
    hotel_id bigint,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone_number character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT hotel_contacts_pkey PRIMARY KEY (email, name, phone_number),
    CONSTRAINT fkig4lh2vmm0eiqfu5llc1ycofu FOREIGN KEY (hotel_id)
        REFERENCES public.hotel (hotel_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
