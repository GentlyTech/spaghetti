CREATE INDEX hotel_index on hotel USING hash(hotel_id);
CREATE INDEX room_index ON room (hotel_id, room_number);
CREATE INDEX booking_index ON booking (room_number, customer_id, hotel_id);