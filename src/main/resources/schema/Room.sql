CREATE TABLE IF NOT EXISTS public.room
(
    damage_fee double precision NOT NULL,
    extendable boolean NOT NULL,
    price double precision NOT NULL,
    room_number integer NOT NULL,
    view_type smallint,
    hotel_id bigint NOT NULL,
    occupancy_type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT room_pkey PRIMARY KEY (room_number, hotel_id),
    CONSTRAINT fkdosq3ww4h9m2osim6o0lugng8 FOREIGN KEY (hotel_id)
        REFERENCES public.hotel (hotel_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT room_view_type_check CHECK (view_type >= 0 AND view_type <= 1)
);

CREATE TABLE IF NOT EXISTS public.room_amenities
(
    room_number integer NOT NULL,
    hotel_id bigint NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT fkih3tpcijq92irb86qvylvqcco FOREIGN KEY (room_number, hotel_id)
        REFERENCES public.room (room_number, hotel_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.room_problems
(
    room_number integer NOT NULL,
    hotel_id bigint NOT NULL,
    caption character varying(255) COLLATE pg_catalog."default",
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT fknxwt342qucw8xmf1roqwysij6 FOREIGN KEY (room_number, hotel_id)
        REFERENCES public.room (room_number, hotel_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
