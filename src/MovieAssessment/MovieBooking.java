package MovieAssessment;

import java.util.*;
import java.io.*;

class MovieBooking {
    private static final String FRONT_DESK_USERNAME = "admin";
    private static String FRONT_DESK_PASSWORD = "password";

    private static final Map<String, Integer> seatPrices = new HashMap<>();
    private static final Map<String, List<String>> seatingArrangement = new HashMap<>();
    private static final Map<String, List<String>> bookingStatus = new HashMap<>();

    static {
        seatPrices.put("B1", 120);
        seatPrices.put("B2", 120);
        seatPrices.put("B3", 120);
	seatPrices.put("B4", 120);
	seatPrices.put("B5", 120);
	seatPrices.put("B6", 120);
	seatPrices.put("C1", 180);
	seatPrices.put("C2", 180);
	seatPrices.put("C3", 180);
	seatPrices.put("C4", 180);
	seatPrices.put("C5", 180);
	seatPrices.put("C6", 180);
	seatPrices.put("D1", 250);
	seatPrices.put("D2", 250);
        // Add more seats and prices as needed.

        // Initialize seating arrangement for a specific date and show time.
        List<String> availableSeats = new ArrayList<>(seatPrices.keySet());

        
        
        
        
		/*
		 * try { File myObj = new File("availableTime.txt"); Scanner myReader = new
		 * Scanner(myObj); while (myReader.hasNextLine()) { String data =
		 * myReader.nextLine(); seatingArrangement.put(data,availableSeats);
		 * //System.out.println(data); } myReader.close(); } catch
		 * (FileNotFoundException e) { System.out.println("An error occurred.");
		 * e.printStackTrace(); }
		 */
        seatingArrangement.put("2023-12-25_09:00", availableSeats);
        seatingArrangement.put("2023-12-25_12:00", availableSeats);
        seatingArrangement.put("2023-12-25_15:00", availableSeats);
        seatingArrangement.put("2023-12-25_17:00", availableSeats);
        seatingArrangement.put("2023-12-25_18:00", availableSeats);
        seatingArrangement.put("2023-12-25_20:00", availableSeats);
        seatingArrangement.put("2023-12-25_21:00", availableSeats);
        
    }
        

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Welcome to Movie Ticket Booking System!");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (!login(username, password)) {
            System.out.println("Invalid credentials. Exiting...");
            return;
        }

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Update Password");
            System.out.println("2. View Seating Arrangement");
            System.out.println("3. Book Ticket");
            System.out.println("4. View Booking Status");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    updatePassword();
                    break;
                case 2:
                    viewSeatingArrangement();
                    break;
                case 3:
                    bookTicket(scanner);
                    break;
                case 4:
                    viewBookingStatus(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean login(String username, String password) {
        return FRONT_DESK_USERNAME.equals(username) && FRONT_DESK_PASSWORD.equals(password);
    }

    private static void updatePassword() {
Scanner scanner = new Scanner(System.in);
        // Implementation to update the password
 	System.out.print("Enter current password: ");
    	String currentPassword = scanner.nextLine();

    if (!FRONT_DESK_PASSWORD.equals(currentPassword)) {
        System.out.println("Incorrect current password. Password update failed.");
        return;
    }

    System.out.print("Enter new password: ");
    String newPassword = scanner.nextLine();

    // You may want to add additional checks or validations for the new password.
    FRONT_DESK_PASSWORD = newPassword;

    System.out.println("Password updated successfully.");

    }

    private static void viewSeatingArrangement() {
        
	Scanner scanner = new Scanner(System.in);
// Implementation to view seating arrangement
System.out.print("Enter date and show time (e.g., 2023-01-01_18:00): ");
    String dateTime = scanner.nextLine();

    if (!seatingArrangement.containsKey(dateTime)) {
        System.out.println("Invalid date or show time. Seating arrangement not available.");
        return;
    }

    List<String> availableSeats = seatingArrangement.get(dateTime);

    System.out.println("Seating Arrangement for " + dateTime + ":");
    for (String seat : availableSeats) {
        System.out.print(seat + " ");
    }
    System.out.println();

    }

    private static void bookTicket(Scanner scanner) {
        // Implementation to book a ticket
	System.out.print("Enter date and show time (e.g., 2023-01-01_18:00): ");
    String dateTime = scanner.nextLine();

    if (!seatingArrangement.containsKey(dateTime)) {
        System.out.println("Invalid date or show time. Seating arrangement not available.");
        return;
    }

    List<String> availableSeats = seatingArrangement.get(dateTime);

    System.out.print("Available seats for " + dateTime + ": ");
    for (String seat : availableSeats) {
        System.out.print(seat + " ");
    }
    System.out.println();

    System.out.print("Enter preferred seat(s) separated by commas (e.g., B1,B2): ");
    String seatSelection = scanner.nextLine();

    String[] selectedSeats = seatSelection.split(",");
    List<String> selectedSeatList = Arrays.asList(selectedSeats);

    // Check if selected seats are available
    if (!availableSeats.containsAll(selectedSeatList)) {
        System.out.println("Invalid seat selection. One or more seats are not available.");
        return;
    }

    // Calculate total amount
    int totalAmount = selectedSeatList.stream()
            .mapToInt(seat -> seatPrices.get(seat))
            .sum();

    System.out.println("Total amount: Rs" + totalAmount);

    System.out.print("Do you want to confirm the booking? (yes/no): ");
    String confirmation = scanner.nextLine();

    if ("yes".equalsIgnoreCase(confirmation)) {
        // Update seating arrangement and booking status
        availableSeats.removeAll(selectedSeatList);
        bookingStatus.put(dateTime, new ArrayList<>(selectedSeatList));

        System.out.println("Booking confirmed. Enjoy the movie!");
    } else {
        System.out.println("Booking canceled.");
    }

    }

    private static void viewBookingStatus(Scanner scanner) {
        System.out.print("Enter date and show time (e.g., 2023-01-01_18:00): ");
    String dateTime = scanner.nextLine();

    if (!bookingStatus.containsKey(dateTime)) {
        System.out.println("No bookings found for the specified date and show time.");
        return;
    }

    List<String> bookedSeats = bookingStatus.get(dateTime);

    System.out.println("Booking Status for " + dateTime + ":");
    for (String seat : bookedSeats) {
        System.out.println("Seat " + seat + " - Booked");
    }	
    }
}
