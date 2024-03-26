INSERT INTO hotel_chain (chain_name) VALUES ('Hilton');
INSERT INTO hotel_chain (chain_name) VALUES ('Hyatt');
INSERT INTO hotel_chain (chain_name) VALUES ('Marriott');
INSERT INTO hotel_chain (chain_name) VALUES ('Travelodge');
INSERT INTO hotel_chain (chain_name) VALUES ('Walt Disney');

INSERT INTO hotel (hotel_name, owner, rating) VALUES ('Ottawa Marriott Hotel', 'Marriott', 4);
INSERT INTO hotel (hotel_name, owner, rating) VALUES ('Sheraton Vancouver Wall Centre', 'Marriott', 4);
INSERT INTO hotel (hotel_name, owner, rating) VALUES ('Hilton Garden Inn Ottawa Downtown', 'Hilton', 4);

INSERT INTO hotel_chain_contacts (chain_name, name, email, phone_number) VALUES ('Marriott', 'Customer Service (US and canada)', '', '18006277468');
INSERT INTO hotel_chain_contacts (chain_name, name, email, phone_number) VALUES ('Marriott', 'Customer Service (Australia)', '', '1800450010');
INSERT INTO hotel_chain_contacts (chain_name, name, email, phone_number) VALUES ('Hilton', 'Customer Service', '', '18009974058');
