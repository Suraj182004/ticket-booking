package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.service.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class App {

    public static void main(String[] args) {
        System.out.println("Running Train Booking System");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        UserBookingService userBookingService;
        User loggedInUser = null;  // ⬅️ this keeps track of the logged-in user
        Train trainSelectedForBooking = null;

        try {
            userBookingService = new UserBookingService();
        } catch (IOException ex) {
            System.out.println("There is something wrong");
            return;
        }

        while (option != 7) {
            System.out.println("Choose option");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Enter the username to signup");
                    String nameToSignUp = scanner.next();
                    System.out.println("Enter the password to signup");
                    String passwordToSignUp = scanner.next();
                    User userToSignup = new User(nameToSignUp, passwordToSignUp,
                            UserServiceUtil.hashPassword(passwordToSignUp), new ArrayList<>(), UUID.randomUUID().toString());
                    if (userBookingService.signUp(userToSignup)) {
                        System.out.println("Signup successful!");
                    } else {
                        System.out.println("Signup failed.");
                    }
                    break;

                case 2:
                    System.out.println("Enter the username to Login");
                    String nameToLogin = scanner.next();
                    System.out.println("Enter the password to login");
                    String passwordToLogin = scanner.next();
                    User tempUser = new User(nameToLogin, passwordToLogin,
                            UserServiceUtil.hashPassword(passwordToLogin), new ArrayList<>(), UUID.randomUUID().toString());

                    try {
                        UserBookingService tempService = new UserBookingService(tempUser);
                        if (tempService.loginUser()) {
                            System.out.println("Login successful!");
                            loggedInUser = tempUser;
                            userBookingService = tempService;
                        } else {
                            System.out.println("Login failed! Invalid username or password.");
                        }
                    } catch (IOException ex) {
                        System.out.println("Login failed due to an error.");
                    }
                    break;

                case 3:
                    if (loggedInUser == null) {
                        System.out.println("Please login first.");
                        break;
                    }
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBookings();
                    break;

                case 4:
                    if (loggedInUser == null) {
                        System.out.println("Please login first.");
                        break;
                    }
                    System.out.println("Type your source station");
                    String source = scanner.next();
                    System.out.println("Type your destination station");
                    String dest = scanner.next();
                    List<Train> trains = userBookingService.getTrains(source, dest);
                    if (trains.isEmpty()) {
                        System.out.println("No trains found.");
                        break;
                    }
                    int index = 1;
                    for (Train t : trains) {
                        System.out.println(index + " Train id : " + t.getTrainId());
                        for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                            System.out.println("station " + entry.getKey() + " time: " + entry.getValue());
                        }
                        index++;
                    }
                    System.out.println("Select a train by typing 1,2,3...");
                    int selectedTrainIndex = scanner.nextInt() - 1;
                    if (selectedTrainIndex >= 0 && selectedTrainIndex < trains.size()) {
                        trainSelectedForBooking = trains.get(selectedTrainIndex);
                        System.out.println("Train selected: " + trainSelectedForBooking.getTrainId());
                    } else {
                        System.out.println("Invalid selection.");
                    }
                    break;

                case 5:
                    if (loggedInUser == null) {
                        System.out.println("Please login first.");
                        break;
                    }
                    if (trainSelectedForBooking == null) {
                        System.out.println("Please search and select a train first.");
                        break;
                    }
                    System.out.println("Select a seat out of these seats");
                    List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);
                    for (List<Integer> row : seats) {
                        for (Integer val : row) {
                            System.out.print(val + " ");
                        }
                        System.out.println();
                    }
                    System.out.println("Select the seat by typing the row and column");
                    System.out.println("Enter the row");
                    int row = scanner.nextInt();
                    System.out.println("Enter the column");
                    int col = scanner.nextInt();
                    System.out.println("Booking your seat....");
                    Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col);
                    if (booked.equals(Boolean.TRUE)) {
                        System.out.println("Booked! Enjoy your journey");
                    } else {
                        System.out.println("Can't book this seat");
                    }
                    break;

                case 6:
                    if (loggedInUser == null) {
                        System.out.println("Please login first.");
                        break;
                    }
                    System.out.println("Enter ticket ID to cancel:");
                    String ticketId = scanner.next();
                    userBookingService.cancelBooking(ticketId);
                    break;

                case 7:
                    System.out.println("Exiting the app.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

}