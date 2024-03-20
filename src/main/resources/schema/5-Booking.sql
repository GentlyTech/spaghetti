CREATE TABLE IF NOT EXISTS public.booking
(
    booking_status smallint,
    room_number integer NOT NULL,
    customer_id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    check_in_date text,
    check_out_date text,
    CONSTRAINT booking_pkey PRIMARY KEY (room_number, customer_id, hotel_id),
    CONSTRAINT fklnnelfsha11xmo2ndjq66fvro FOREIGN KEY (customer_id)
        REFERENCES public.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fktn6unndb7hsrn8d7luvrimgaj FOREIGN KEY (room_number, hotel_id)
        REFERENCES public.room (room_number, hotel_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT booking_booking_status_check CHECK (booking_status >= 0 AND booking_status <= 3)
);
