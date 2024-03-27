CREATE TABLE IF NOT EXISTS public.employee
(
    employee_id   serial    NOT NULL,
    creation_date timestamp NOT NULL,
    name          text      NOT NULL,
    sin           text      NOT NULL,
    CONSTRAINT employee_pk PRIMARY KEY (employee_id)
);

CREATE TABLE IF NOT EXISTS public.employee_addresses
(
    employee_id integer NOT NULL,
    address_id  integer NOT NULL,
    CONSTRAINT employee_id_fk FOREIGN KEY (employee_id) REFERENCES public.employee (employee_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION,
    CONSTRAINT address_id_fk FOREIGN KEY (address_id) REFERENCES public.addresses (address_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.employee_roles
(
    employee_id integer NOT NULL,
    role_name   text    NOT NULL,
    CONSTRAINT employee_id_fk FOREIGN KEY (employee_id)
        REFERENCES public.employee (employee_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
        CONSTRAINT role_name_fk FOREIGN KEY (role_name)
        REFERENCES public.roles (name)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
