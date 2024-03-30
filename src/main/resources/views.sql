-- gets all of the rooms in a city
CREATE VIEW rooms_in_area as
SELECT addresses.city, count(distinct (room.hotel_id, room.room_number)) as room_count
FROM addresses, hotel_addresses, room
WHERE hotel_addresses.hotel_id = room.hotel_id
  AND addresses.address_id = hotel_addresses.address_id
group by addresses.city;


-- count of rooms in all specific hotels
CREATE VIEW rooms_in_hotel as
SELECT hotel_id, count(distinct room_number) as room_count
FROM room
group by hotel_id;