import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Flight {
    private String flightNumber;
    private String origin;
    private String destination;
    private int totalSeats;
    private int availableSeats;

    public Flight(String flightNumber, String origin, String destination, int totalSeats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Flight " + flightNumber + " from " + origin + " to " + destination +
               ", Available Seats: " + availableSeats + "/" + totalSeats;
    }
}

class Reservation {
    private String passengerName;
    private String flightNumber;

    public Reservation(String passengerName, String flightNumber) {
        this.passengerName = passengerName;
        this.flightNumber = flightNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
}

public class AirlineReservationSystem {
    private List<Flight> flights;
    private List<Reservation> reservations;
    private Scanner scanner;

    public AirlineReservationSystem() {
        flights = new ArrayList<>();
        reservations = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void listFlights() {
        System.out.println("\nAvailable Flights:");
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }

    public void makeReservation() {
        System.out.print("Enter your name: ");
        String passengerName = scanner.nextLine();
        System.out.print("Enter the flight number: ");
        String flightNumber = scanner.nextLine();

        Flight flight = findFlight(flightNumber);

        if (flight != null && flight.bookSeat()) {
            reservations.add(new Reservation(passengerName, flightNumber));
            System.out.println("Reservation successful!");
        } else {
            System.out.println("Flight not found or no available seats.");
        }
    }

    private Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        AirlineReservationSystem ars = new AirlineReservationSystem();
        ars.addFlight(new Flight("F101", "New York", "Los Angeles", 100));
        ars.addFlight(new Flight("F102", "Chicago", "Miami", 80));

        while (true) {
            System.out.println("\nAirline Reservation System Menu:");
            System.out.println("1. List Flights");
            System.out.println("2. Make Reservation");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = ars.scanner.nextInt();
            ars.scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    ars.listFlights();
                    break;
                case 2:
                    ars.makeReservation();
                    break;
                case 3:
                    System.out.println("Exiting Airline Reservation System.");
                    ars.scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
