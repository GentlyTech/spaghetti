CREATE TABLE IF NOT EXISTS public.contacts
(
    contact_id serial NOT NULL,
    name text NOT NULL,
    email text,
    phone_number text,
    CONSTRAINT contacts_pk PRIMARY KEY (contact_id)
);
