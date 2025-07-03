import java.util.Scanner;

class Movie {
    String name;
    int totalSeats;
    int bookedSeats;
    int ticketPrice;

    Movie(String name, int totalSeats, int ticketPrice) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
        this.ticketPrice = ticketPrice;
    }

    boolean bookSeats(int seats) {
        if (seats <= (totalSeats - bookedSeats)) {
            bookedSeats += seats;
            return true;
        } else {
            return false;
        }
    }

    int availableSeats() {
        return totalSeats - bookedSeats;
    }
}

public class MovieTicketBookingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Movie[] movies = {
                new Movie("The Matrix", 50, 150),
                new Movie("Inception", 40, 180),
                new Movie("Interstellar", 60, 200)
        };

        int totalTicketsBooked = 0;
        int totalRevenue = 0;

        while (true) {
            System.out.println("\nAvailable Movies:");
            for (int i = 0; i < movies.length; i++) {
                System.out.printf("%d. %s | ₹%d per ticket | Available Seats: %d\n",
                        i + 1, movies[i].name, movies[i].ticketPrice, movies[i].availableSeats());
            }
            System.out.println("0. Exit & View Summary");

            System.out.print("Select a movie by number: ");
            int choice = getValidInt(sc);
            if (choice == -1) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            if (choice == 0) {
                System.out.println("\n=== Booking Summary ===");
                System.out.println("Total Tickets Booked: " + totalTicketsBooked);
                System.out.println("Total Amount: ₹" + totalRevenue);
                System.out.println("Thank you for using the Movie Ticket Booking System!");
                break;
            }

            if (choice < 1 || choice > movies.length) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            Movie selectedMovie = movies[choice - 1];

            System.out.print("Enter number of tickets to book: ");
            int tickets = getValidInt(sc);
            if (tickets == -1 || tickets <= 0) {
                System.out.println("Number of tickets must be a positive integer.");
                continue;
            }

            if (selectedMovie.bookSeats(tickets)) {
                int cost = tickets * selectedMovie.ticketPrice;
                totalTicketsBooked += tickets;
                totalRevenue += cost;

                System.out.println("\n✅ Booking Confirmed!");
                System.out.println("Movie: " + selectedMovie.name);
                System.out.println("Tickets Booked: " + tickets);
                System.out.println("Total Price: ₹" + cost);
            } else {
                System.out.println("❌ Sorry, not enough seats available.");
            }
        }

        sc.close();
    }

    private static int getValidInt(Scanner sc) {
        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            sc.next();
            return -1;
        }
    }
}
