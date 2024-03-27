-- Hotel Chains --

INSERT INTO hotel_chain (chain_name)
VALUES ('Hilton');

INSERT INTO hotel_chain (chain_name)
VALUES ('Hyatt');

INSERT INTO hotel_chain (chain_name)
VALUES ('Marriott');

INSERT INTO hotel_chain (chain_name)
VALUES ('Travelodge');

INSERT INTO hotel_chain (chain_name)
VALUES ('Walt Disney');

-- Hotels --

INSERT INTO hotel (hotel_name, owner, rating)
VALUES ('Ottawa Marriott Hotel', 'Marriott', 4);

INSERT INTO hotel (hotel_name, owner, rating)
VALUES ('Sheraton Vancouver Wall Centre', 'Marriott', 4);

INSERT INTO hotel (hotel_name, owner, rating)
VALUES ('Hilton Garden Inn Ottawa Downtown', 'Hilton', 4);

-- Hotel Chain Contacts --

WITH retVal as (
INSERT
INTO contacts (name, email, phone_number)
VALUES ('Customer Service (US and canada)', '', '18006277468') RETURNING contact_id)
INSERT
INTO hotel_chain_contacts (chain_name, contact_id)
SELECT 'Marriott', contact_id
FROM retVal;

WITH retVal as (
INSERT
INTO contacts (name, email, phone_number)
VALUES ('Customer Service (Australia)', '', '1800450010') RETURNING contact_id)
INSERT
INTO hotel_chain_contacts (chain_name, contact_id)
SELECT 'Marriott', contact_id
FROM retVal;

WITH retVal as (
INSERT
INTO contacts (name, email, phone_number)
VALUES ('Customer Service', '', '18009974058') RETURNING contact_id)
INSERT
INTO hotel_chain_contacts (chain_name, contact_id)
SELECT 'Hilton', contact_id
FROM retVal;
