CREATE INDEX hotel_index on hotel (hotel_id, rating);
CREATE INDEX room_index ON room (hotel_id, room_number, price, capacity);
CREATE INDEX booking_index ON booking (room_number, customer_id, hotel_id);
CREATE INDEX addresses_index ON addresses (city, country, province);