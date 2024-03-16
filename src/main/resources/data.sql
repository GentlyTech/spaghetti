INSERT INTO hotel_chain (chain_name) VALUES ('Hilton');
INSERT INTO hotel_chain (chain_name) VALUES ('Hyatt');
INSERT INTO hotel_chain (chain_name) VALUES ('Marriott');
INSERT INTO hotel_chain (chain_name) VALUES ('Travelodge');
INSERT INTO hotel_chain (chain_name) VALUES ('Walt Disney');

WITH retVal AS (INSERT INTO hotel (hotel_name, rating) VALUES ('Ottawa Marriott Hotel', 4) RETURNING hotel_id)
INSERT INTO hotel_chain_hotels (chain_name, hotels_hotel_id) SELECT 'Marriott', hotel_id from retVal ON CONFLICT DO NOTHING;
                                                                 
WITH retVal AS (INSERT INTO hotel (hotel_name, rating) VALUES ('Sheraton Vancouver Wall Centre', 4) RETURNING hotel_id)
INSERT INTO hotel_chain_hotels (chain_name, hotels_hotel_id) SELECT 'Marriott', hotel_id from retVal ON CONFLICT DO NOTHING;

INSERT INTO hotel_chain_contacts (chain_name, name, email, phone_number) VALUES ('Marriott', 'Customer Service (US and canada)', '', '18006277468');
INSERT INTO hotel_chain_contacts (chain_name, name, email, phone_number) VALUES ('Marriott', 'Customer Service (Australia)', '', '1800450010');
INSERT INTO hotel_chain_contacts (chain_name, name, email, phone_number) VALUES ('Hilton', 'Customer Service', '', '18009974058');
