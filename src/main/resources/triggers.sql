-- function do archive booking using room number
CREATE OR REPLACE FUNCTION archive_booking_by_room_num()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
'
BEGIN
    INSERT INTO archived_booking
    SELECT *
    FROM booking
    WHERE booking.room_number = OLD.room_number;
    RETURN old;
end;
';

-- function do archive booking using customer id
CREATE OR REPLACE FUNCTION archive_booking_by_customer_id()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
'
BEGIN
    INSERT INTO archived_booking
    SELECT *
    FROM booking
    WHERE booking.customer_id = OLD.customer_id;
    RETURN old;
end;
';

-- trigger for on delete room
CREATE TRIGGER delete_room_trigger
    BEFORE DELETE
    ON room
    FOR EACH ROW
EXECUTE FUNCTION archive_booking_by_room_num();

-- trigger for on delete customer
CREATE TRIGGER delete_customer_trigger
    BEFORE DELETE
    ON customer
    FOR EACH ROW
EXECUTE FUNCTION archive_booking_by_customer_id();