CREATE TABLE IF NOT EXISTS public.amenities
(
    name        text NOT NULL,
    description text,
    CONSTRAINT amenity_pk PRIMARY KEY (name)
)
