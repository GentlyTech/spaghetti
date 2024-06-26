CREATE TABLE IF NOT EXISTS public.room
(
    hotel_id    integer          NOT NULL,
    room_number integer          NOT NULL,
    price       double precision NOT NULL,
    view_type   text,
    capacity    integer          NOT NULL,
    extendable  boolean          NOT NULL,
    CONSTRAINT room_pk PRIMARY KEY (room_number, hotel_id),
    CONSTRAINT hotel_id_fk FOREIGN KEY (hotel_id)
        REFERENCES public.hotel (hotel_id)
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.room_amenities
(
    room_number integer     NOT NULL,
    hotel_id    integer     NOT NULL,
    name        text UNIQUE NOT NULL,
    description text,
    CONSTRAINT room_fk FOREIGN KEY (room_number, hotel_id)
        REFERENCES public.room (room_number, hotel_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.room_problems
(
    hotel_id    integer     NOT NULL,
    room_number integer     NOT NULL,
    caption     text UNIQUE NOT NULL,
    description text,
    CONSTRAINT room_fk FOREIGN KEY (room_number, hotel_id)
        REFERENCES public.room (room_number, hotel_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
