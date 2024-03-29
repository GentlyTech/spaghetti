CREATE TABLE IF NOT EXISTS public.customer
(
    customer_id   serial    NOT NULL,
    creation_date timestamp NOT NULL,
    id_type       text      NOT NULL,
    name          text      NOT NULL,
    CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);

CREATE TABLE IF NOT EXISTS public.customer_addresses
(
    customer_id integer NOT NULL,
    address_id  integer NOT NULL,
    CONSTRAINT customer_id_fk FOREIGN KEY (customer_id) REFERENCES public.customer (customer_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
    CONSTRAINT address_id_fk FOREIGN KEY (address_id) REFERENCES public.addresses (address_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);
