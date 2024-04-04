CREATE TABLE IF NOT EXISTS public.booking
(
    room_number    integer NOT NULL,
    customer_id    integer NOT NULL,
    hotel_id       integer NOT NULL,
    check_in_date  date    NOT NULL,
    check_out_date date    NOT NULL,
    booking_status text,
    damage_fee     double precision,
    CONSTRAINT booking_pk PRIMARY KEY (room_number, hotel_id, check_in_date, check_out_date),
    CONSTRAINT customer_id_fk FOREIGN KEY (customer_id)
        REFERENCES public.customer (customer_id)
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT room_fk FOREIGN KEY (room_number, hotel_id)
        REFERENCES public.room (room_number, hotel_id)
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.archived_booking
(
    room_number    integer NOT NULL,
    customer_id    integer NOT NULL,
    hotel_id       integer NOT NULL,
    check_in_date  date    NOT NULL,
    check_out_date date    NOT NULL,
    booking_status text,
    damage_fee     double precision,
    CONSTRAINT archived_booking_pk PRIMARY KEY (room_number, customer_id, hotel_id, check_in_date, check_out_date)
);