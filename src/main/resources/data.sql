INSERT INTO hotel_chain (chain_name) VALUES ('Hilton');
INSERT INTO hotel_chain (chain_name) VALUES ('Hyatt');
INSERT INTO hotel_chain (chain_name) VALUES ('Marriott');
INSERT INTO hotel_chain (chain_name) VALUES ('Travelodge');
INSERT INTO hotel_chain (chain_name) VALUES ('Walt Disney');

WITH retVal AS (INSERT INTO hotel (hotel_name, rating) VALUES ('Ottawa Marriott Hotel', 4) RETURNING hotel_id)
INSERT INTO hotel_chain_hotels (chain_name, hotels_hotel_id) SELECT 'Marriott', hotel_id from retVal;
                                                                 
WITH retVal AS (INSERT INTO hotel (hotel_name, rating) VALUES ('Sheraton Vancouver Wall Centre', 4) RETURNING hotel_id)
INSERT INTO hotel_chain_hotels (chain_name, hotels_hotel_id) SELECT 'Marriott', hotel_id from retVal;
