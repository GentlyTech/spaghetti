-- function do archive booking
CREATE OR REPLACE FUNCTION archive_booking()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
'
    BEGIN
        INSERT INTO archived_booking
        SELECT *
        FROM booking
        WHERE booking.hotel_id = old.hotel_id AND booking.customer_id = old.customer_id AND booking.room_number = old.room_number AND booking.check_in_date = old.check_in_date AND booking.check_out_date = old.check_out_date;
        RETURN old;
    end;
';

-- trigger for booking deletion
CREATE TRIGGER delete_booking_trigger
    BEFORE DELETE
    ON booking
    FOR EACH ROW
EXECUTE FUNCTION archive_booking();
