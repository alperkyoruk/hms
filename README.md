# Hotel Management System

A comprehensive Hotel Management System built with Java and Spring Boot. This application manages various aspects of hotel operations including room reservations, guest management, ordering from Room Service
ticketing for housekeeping and maintenance etc.

## Technologies Used

- Java
- Spring Boot
- Maven
- JPA/Hibernate
- PostgreSQL (or any other relational database)
- IntelliJ IDEA

## Setup Instructions

1. **Clone the repository**:
    ```sh
    git clone https://github.com/alperkyoruk/hotel-management-system.git
    cd hotel-management-system
    ```

2. **Configure the database**:
    - Update the `application.properties` file in `src/main/resources` with your database configuration.

3. **Build the project**:
    ```sh
    mvn clean install
    ```

4. **Run the application**:
    ```sh
    mvn spring-boot:run
    ```

## Usage

Once the application is running, you can access the API at `http://localhost:8080`.

## Endpoints

Here are some of the main API endpoints:

- **Room Service Orders**:
  - `GET /api/roomServiceOrders/getRoomServiceOrderById`: Get a room service order by ID.
  - `POST /api/roomServiceOrders/addRoomServiceOrder`: Create a new room service order.

- **Guests**:
  - `GET /api/guests/getGuestById`: Get a guest by ID.
  - `POST /api/guests/addGuest`: Create a new guest.

- **Tickets**:
  - `GET /api/tickets/getById`: Get a ticket by ID.
  - `POST /api/tickets/addTicket`: Create a new ticket.
  - `POST /api/tickets/updateTicket`: Update a ticket.
  - `POST /api/tickets/deleteTicket`: Delete a ticket.

- **Users**:
  - `GET /api/users/getUserById`: Get a user by ID.
  - `POST /api/users/addUser`: Create a new user.

- **Facilities**:
  - `GET /api/facilities/getFacilityById`: Get a facility by ID.
  - `POST /api/facilities/addFacility`: Create a new facility.

- **Rooms**:
  - `GET /api/rooms/getRoomById`: Get a room by ID.
  - `POST /api/rooms/addRoom`: Create a new room.

- **Staff**:
  - `GET /api/staff/getById`: Get a staff member by ID.
  - `POST /api/staff/addStaff`: Create a new staff member.

- **Menu Items**:
  - `GET /api/menuItems/getMenuItemById`: Get a menu item by ID.
  - `POST /api/menuItems/addMenuItem`: Create a new menu item.

- **Authentication**:
  - `POST /api/auth/generateToken`: Generate a JWT token.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Open a pull request.

## License

This project is licensed under the MIT License.
