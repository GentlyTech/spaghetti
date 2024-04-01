-- gets all of the rooms in a city
CREATE VIEW rooms_in_area as
SELECT addresses.city, count(distinct (room.hotel_id, room.room_number)) as room_count
FROM addresses,
     hotel_addresses,
     room
WHERE hotel_addresses.hotel_id = room.hotel_id
  AND addresses.address_id = hotel_addresses.address_id
group by addresses.city;


-- count of rooms in all specific hotels
CREATE VIEW rooms_in_hotel as
SELECT hotel_id, count(distinct room_number) as room_count
FROM room
group by hotel_id;

-- Room querying

CREATE VIEW hotel_room_map AS
SELECT hotelInst.hotel_name, hotelInst.rating, hotelInst.owner, roomInst.*
FROM hotel hotelInst
         LEFT JOIN room roomInst ON hotelInst.hotel_id = roomInst.hotel_id;

CREATE VIEW hotel_address_map AS
SELECT hotelAddressInst.hotel_id, addressInst.*
FROM hotel_addresses hotelAddressInst
         LEFT JOIN addresses addressInst ON hotelAddressInst.address_id = addressInst.address_id;

CREATE VIEW giga_map AS
SELECT hotelRoomMapInst.hotel_id,
       hotel_name,
       rating,
       owner,
       room_number,
       price,
       view_type,
       capacity,
       extendable,
       address_id,
       street,
       city,
       province,
       postal_code,
       country
FROM hotel_room_map hotelRoomMapInst
         LEFT JOIN hotel_address_map hotelAddressMapInst ON hotelRoomMapInst.hotel_id = hotelAddressMapInst.hotel_id;