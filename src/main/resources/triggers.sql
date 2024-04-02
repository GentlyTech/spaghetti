CREATE OR REPLACE FUNCTION archive_booking_on_insert_or_update()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
'
    BEGIN
        DELETE
        FROM booking
        WHERE booking.hotel_id = new.hotel_id
          AND booking.customer_id = new.customer_id
          AND booking.room_number = new.room_number
          AND booking.check_in_date = new.check_in_date
          AND booking.check_out_date = new.check_out_date;
        RETURN new;
    end;
';

CREATE OR REPLACE FUNCTION archive_booking_on_delete()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
'
    BEGIN
        INSERT INTO archived_booking
        SELECT *
        FROM booking
        WHERE booking.hotel_id = old.hotel_id
          AND booking.customer_id = old.customer_id
          AND booking.room_number = old.room_number
          AND booking.check_in_date = old.check_in_date
          AND booking.check_out_date = old.check_out_date;
        RETURN old;
    end;
';

CREATE TRIGGER insert_booking_trigger
    AFTER INSERT
    ON booking
    FOR EACH ROW
    WHEN ( new.booking_status = 'cancelled' OR new.booking_status = 'completed' )
EXECUTE FUNCTION archive_booking_on_insert_or_update();

CREATE TRIGGER update_booking_trigger
    AFTER UPDATE
    ON booking
    FOR EACH ROW
    WHEN ( new.booking_status = 'cancelled' OR new.booking_status = 'completed' )
EXECUTE FUNCTION archive_booking_on_insert_or_update();

CREATE TRIGGER delete_booking_trigger
    BEFORE DELETE
    ON booking
    FOR EACH ROW
EXECUTE FUNCTION archive_booking_on_delete();
