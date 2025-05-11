# Train Booking System

A command-line based train booking system that allows users to sign up, log in, search for trains, book seats, and cancel bookings.

## Features

- **Sign up**: Users can create a new account by providing a username and password
- **Login**: Users can log in with their existing credentials
- **Fetch Bookings**: Users can view their previous bookings
- **Search Trains**: Users can search for available trains based on source and destination
- **Book Seats**: Users can select and book available seats on a train
- **Cancel Bookings**: Users can cancel their existing bookings by providing the ticket ID

## Technologies Used

- **Java**: Main programming language for implementing the functionality
- **Jackson**: Library for JSON processing
- **UUID**: For generating unique IDs
- **Scanner**: For user input via the command line

## Project Structure

```
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── ticket
│   │   │   │       ├── booking
│   │   │   │       │   ├── App.java              # Main entry point
│   │   │   │       │   ├── entities
│   │   │   │       │   │   ├── Ticket.java        # Ticket entity
│   │   │   │       │   │   ├── Train.java         # Train entity
│   │   │   │       │   │   └── User.java          # User entity
│   │   │   │       │   ├── service
│   │   │   │       │   │   ├── UserBookingService.java  # Business logic
│   │   │   │       │   │   └── TrainService.java       # Train service logic
│   │   │   │       │   └── util
│   │   │   │       │       └── UserServiceUtil.java   # Utility methods for user service
│   │   └── resources
│   │       └── localDb
│   │           └── users.json           # User data stored here
└── pom.xml                              # Maven project file
```

## Requirements

- Java 8 or higher
- Maven 3.6 or higher
- Git

## Installation

1. **Clone the repository** to your local machine:
   ```bash
   git clone https://github.com/yourusername/train-booking-system.git
   cd train-booking-system
   ```

2. **Build the project** using Maven:
   ```bash
   mvn clean install
   ```

## How to Run

### Using an IDE

1. Open the project in IntelliJ IDEA or any other Java IDE
2. Navigate to `src/main/java/ticket/booking/App.java`
3. Right-click and select "Run App.main()"

### Using Command Line

1. Build the project with Maven:
   ```bash
   mvn clean package
   ```

2. Run the application:
   ```bash
   java -cp target/train-booking-system.jar ticket.booking.App
   ```

3. Follow the on-screen instructions to interact with the system

## Usage Guide

When you run the application, you'll see a menu with the following options:

1. **Sign Up**: Create a new account
2. **Login**: Access your existing account
3. **Exit**: Quit the application

After logging in successfully, you'll have access to the following options:

1. **Search Trains**: Find trains based on source and destination
2. **Book Seats**: Book seats on available trains
3. **View Bookings**: See all your current bookings
4. **Cancel Booking**: Cancel an existing booking
5. **Logout**: Return to the main menu

## Data Storage

The application stores user data and bookings in JSON files located in the `resources/localDb` directory:

- `users.json`: Stores user account information
- Train and ticket data is handled in-memory for simplicity

## How to Contribute

1. Fork this repository to your GitHub account
2. Clone the forked repository to your local machine:
   ```bash
   git clone https://github.com/yourusername/train-booking-system.git
   ```
3. Create a new branch for your changes:
   ```bash
   git checkout -b feature/your-feature-name
   ```
4. Make your changes and commit them with descriptive messages:
   ```bash
   git commit -m "Add feature: description of changes"
   ```
5. Push your changes to your forked repository:
   ```bash
   git push origin feature/your-feature-name
   ```
6. Create a pull request to the original repository

## Future Enhancements

- Implement persistent storage for trains and tickets
- Add user profile management
- Implement admin panel for train management
- Add payment integration
- Create a graphical user interface

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contact

For any questions or suggestions, please open an issue on the GitHub repository.
