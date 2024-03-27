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

-- Hotel Chain Contacts --

WITH retVal AS (
INSERT
INTO contacts (name, email, phone_number)
VALUES ('Customer Service (US and canada)', '', '18006277468') RETURNING contact_id)
INSERT
INTO hotel_chain_contacts (chain_name, contact_id)
SELECT 'Marriott', contact_id
FROM retVal;

WITH retVal AS (
INSERT
INTO contacts (name, email, phone_number)
VALUES ('Customer Service (Australia)', '', '1800450010') RETURNING contact_id)
INSERT
INTO hotel_chain_contacts (chain_name, contact_id)
SELECT 'Marriott', contact_id
FROM retVal;

WITH retVal AS (
INSERT
INTO contacts (name, email, phone_number)
VALUES ('Customer Service', '', '18009974058') RETURNING contact_id)
INSERT
INTO hotel_chain_contacts (chain_name, contact_id)
SELECT 'Hilton', contact_id
FROM retVal;

-- Hotel Chain Addresses --

WITH retVal AS (
INSERT
INTO addresses (street, city, province, postal_code, country)
VALUES ('7707 Woodmont Ave', 'Bethesda', 'MD', '20814', 'United States') RETURNING address_id)
INSERT
INTO hotel_chain_addresses (chain_name, address_id)
SELECT 'Marriott', address_id
FROM retVal;

-- Hotels, Hotel Contacts, and Hotel Addresses --

WITH hotelRetVal AS (
INSERT
INTO hotel (hotel_name, owner, rating)
VALUES ('Ottawa Marriott Hotel', 'Marriott', 4) RETURNING hotel_id, owner), addressRetVal AS (
INSERT
INTO addresses (street, city, province, postal_code, country)
VALUES ('100 Kent St', 'Ottawa', 'ON', 'K1P5R7', 'Canada') RETURNING address_id), contactRetVal AS (
SELECT contactInst.contact_id
FROM contacts contactInst
    LEFT JOIN hotel_chain_contacts hotelChainInst
ON contactInst.contact_id = hotelChainInst.contact_id
WHERE hotelChainInst.chain_name = (SELECT owner FROM hotelRetVal)), contactLambda AS (
INSERT
INTO hotel_contacts (hotel_id, contact_id)
SELECT hotel_id, contact_id
FROM hotelRetVal, contactRetVal
    )
INSERT
INTO hotel_addresses (hotel_id, address_id)
SELECT hotel_id, address_id
FROM hotelRetVal,
     addressRetVal;

INSERT
INTO hotel (hotel_name, owner, rating)
VALUES ('Sheraton Vancouver Wall Centre', 'Marriott', 4);

INSERT
INTO hotel (hotel_name, owner, rating)
VALUES ('Hilton Garden Inn Ottawa Downtown', 'Hilton', 4);
