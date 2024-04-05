# Script

## Intro

Hi, I'm Andy, and I'll be taking you through a quick tour of our backend.

## Part 2 - Schema

We'll begin by exploring the schema. As you can see, we split the schema up to keep it more organized.

- Show some of the weak entities (Contact, Address)
- Show some of the main entities (HotelChain, Hotel)
- "We will show the relationship tables under the 'Integrity Constraints' section"

### Changes to Schema

The main difference between our implementation and the proposed schema in Deliverable 1 is that we added IDs to contacts and addresses. This allows us to reuse their corresponding data classes while giving us a way to associate them with the different entities that use them.

## Part 3 - Integrity Constraints

- Contacts and addresses must have an owner
  - It doesn't make sense to keep random contacts or addresses that are not being used for anything (wastes space)
  - Thus they'll be automatically deleted if they don't have an owner
- Dependency of a hotel on a hotel chain
  - Assumption is that hotels can't exist without being under a chain
  - All hotels will be deleted if their owning chain is deleted
- Dependency of rooms on a hotel
  - Rooms can't exist without a hotel
  - All rooms in a given hotel will be deleted if the hotel is deleted
- Bookings are deleted if the room, hotel, or customer is deleted
  - Since a booking is not complete without all three references

## Part 4 - Sample Data

- Mention the types of sample data we have (show some JSON)
- Mention how we have generators for the rooms, bookings, and amenities since they depend on other things existing in order to get the IDs needed to form the primary keys
  - Thus we can't hardcode them in the JSON file
- Show the init method

## Part 5 - Queries

- Introduce DAOs and how the REST controllers call into them
  - "Don't worry we're not using ORM features"
  - "Since we've already written the queries ahead of time, we'll just use the endpoints to perform queries, rather than manually running them in the console"

- Use swagger
  - Query some information from hotels and maybe do a room search
  - Post a new booking?
    - Show that the booking exists in pgadmin
  - Update the booking's checkout date or something
    - Show that the booking was updated in pgadmin
  - Delete the booking
    - Show that the booking was deleted in pgadmin

- Explain how the UI uses these exact same endpoints to send and retrieve information

## Part 6 - Triggers

- We have triggers for archiving bookings when a booking is deleted (either directly or because a customer deleted their account or a room or hotel was deleted).
  - Additionally, we have triggers for automatically deleting bookings and adding them to the archive when their status has been set to 'cancelled' or 'completed'.
  - Same goes with inserting a booking that is already set to 'cancelled' or 'completed' (which shouldn't happen but covering all bases).
- Demo the trigger in pgadmin (we will demo the trigger on customer deletion).
  - SELECT * FROM booking;
    - Pick one of the customers to delete
  - DELETE FROM customer WHERE customer_id = ?;
  - SELECT * FROM booking;
    - All bookings concerning the customer should be gone.
  - SELECT * FROM archived_booking;
    - All bookings that were deleted should appear here.

## Part 7 - Indices

We created the following indices:

- hotel_index
  - improves the querying speed of hotels
- room_index
  - improves the querying speed of rooms
- booking_index
  - improves the querying speed of bookings

We chose to create indices for these entities because they were some of the more heavily accessed ones, and also contain the most information out of all entities.

## Part 8 - Views

- We implemented the required views for showing the number of available rooms in a given area and the capacity of each hotel.
  - Perhaps show that the views work
- We also implemented extra views to combine hotel information (including the hotel address) with each room
  - This made writing the query for searching rooms with the required parameters considerably easier to implement (since the joins have essentially been precomputed ahead of time, so we don't need to join the tables when selecting rows)
