CREATE TABLE IF NOT EXISTS public.employee
(
    id bigint NOT NULL DEFAULT nextval('employee_id_seq'::regclass),
    creation_date character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    sin character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT employee_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.employee_addresses
(
    employee_id bigint,
    city character varying(255) COLLATE pg_catalog."default" NOT NULL,
    country character varying(255) COLLATE pg_catalog."default" NOT NULL,
    postal_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    province character varying(255) COLLATE pg_catalog."default" NOT NULL,
    street character varying(255) COLLATE pg_catalog."default" NOT NULL,
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
    description character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT fk1xlpopin9cfno37qtl3ag68gs FOREIGN KEY (id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
