CREATE TABLE IF NOT EXISTS public.employee
(
    id serial NOT NULL,
    creation_date text,
    name text,
    sin text,
    CONSTRAINT employee_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.employee_addresses
(
    employee_id bigint,
    city text NOT NULL,
    country text NOT NULL,
    postal_code text NOT NULL,
    province text NOT NULL,
    street text NOT NULL,
    CONSTRAINT employee_addresses_pkey PRIMARY KEY (city, country, postal_code, province, street),
    CONSTRAINT employee_addresses_employee_id_key UNIQUE (employee_id),
    CONSTRAINT fkk9tk9o1fymv1wq2fuycf5i040 FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.employee_roles
(
    id bigint NOT NULL,
    description text,
    name text,
    CONSTRAINT fk1xlpopin9cfno37qtl3ag68gs FOREIGN KEY (id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);