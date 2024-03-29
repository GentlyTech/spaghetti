CREATE TABLE IF NOT EXISTS public.booking
(
    room_number    integer NOT NULL,
    customer_id    integer NOT NULL,
    hotel_id       integer NOT NULL,
    booking_status text,
    check_in_date  timestamp,
    check_out_date timestamp,
    damage_fee     double precision,
    CONSTRAINT booking_pk PRIMARY KEY (room_number, customer_id, hotel_id),
    CONSTRAINT customer_id_fk FOREIGN KEY (customer_id)
        REFERENCES public.customer (customer_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT room_fk FOREIGN KEY (room_number, hotel_id)
        REFERENCES public.room (room_number, hotel_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
