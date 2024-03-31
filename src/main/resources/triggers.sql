CREATE FUNCTION delete_hotel() RETURNS TRIGGER AS
'
BEGIN
    DELETE FROM room WHERE public.room.hotel_id = old.hotel_id;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER on_hotel_deletion
    BEFORE DELETE
    ON hotel
    FOR EACH ROW
EXECUTE FUNCTION delete_hotel();