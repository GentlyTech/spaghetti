CREATE TABLE IF NOT EXISTS public.archived_booking
(
    room_number    integer NOT NULL,
    customer_id    integer NOT NULL,
    hotel_id       integer NOT NULL,
    booking_status text,
    check_in_date  date,
    check_out_date date,
    damage_fee     double precision,
    CONSTRAINT archived_booking_pk PRIMARY KEY (room_number, customer_id, hotel_id)
);