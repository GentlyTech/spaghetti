CREATE TABLE IF NOT EXISTS public.contacts
(
    contact_id serial NOT NULL,
    email text,
    name text NOT NULL,
    phone_number text,
    CONSTRAINT contacts_pkey PRIMARY KEY (contact_id)
);
