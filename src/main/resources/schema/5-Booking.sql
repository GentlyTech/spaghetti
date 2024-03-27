CREATE TABLE IF NOT EXISTS public.booking
(
    booking_status smallint,
    room_number integer NOT NULL,
    customer_id integer NOT NULL,
    hotel_id integer NOT NULL,
    check_in_date timestamp,
    check_out_date timestamp,
    CONSTRAINT booking_pk PRIMARY KEY (room_number, customer_id, hotel_id),
    CONSTRAINT customer_id_fk FOREIGN KEY (customer_id)
        REFERENCES public.customer (customer_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT room_fk FOREIGN KEY (room_number, hotel_id)
        REFERENCES public.room (room_number, hotel_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT booking_status_check CHECK (booking_status >= 0 AND booking_status <= 3)
);
