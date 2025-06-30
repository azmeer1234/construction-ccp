import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class BadReservationApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Guest House Booking System");

        System.out.print("Guest house name: ");
        String gh = sc.nextLine();

        if (gh.isEmpty()) {
            System.out.println("Guest house required.");
            gh = sc.nextLine();
        }

        System.out.print("Room type: ");
        String rt = sc.nextLine();

        LocalDate s = null;
        while (s == null) {
            System.out.print("Start Date (YYYY-MM-DD): ");
            try {
                s = LocalDate.parse(sc.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Bad date. Try again.");
            }
        }

        LocalDate e = null;
        while (e == null) {
            System.out.print("End Date (YYYY-MM-DD): ");
            try {
                e = LocalDate.parse(sc.nextLine());
            } catch (DateTimeParseException ex) {
                System.out.println("Bad end date.");
            }
        }

        if (e.isBefore(s)) {
            System.out.println("End date cannot be before start.");
            return;
        }

        System.out.print("Name: ");
        String n = sc.nextLine();
        if (n.length() < 1) {
            System.out.println("Name needed.");
            n = sc.nextLine();
        }

        System.out.print("Address: ");
        String a = sc.nextLine();
        if (a.equals("")) {
            System.out.println("Address is required.");
            a = sc.nextLine();
        }

        System.out.print("Credit Card (16 digits): ");
        String cc = sc.nextLine();
        if (!(cc.replaceAll("\\s+", "").matches("[0-9]{16}"))) {
            System.out.println("Invalid card.");
            cc = sc.nextLine();
        }

        String result = makeReservation(gh, s, e, rt, n, a, cc);
        System.out.println("Result: " + result);
    }

    // Business logic mixed directly, no encapsulation
    public static String makeReservation(String guesthouse, LocalDate s, LocalDate e,
                                         String type, String name, String addr, String card) {
        return "Reservation done for " + name + " at " + guesthouse + " for " + type +
                " room from " + s + " to " + e + ".";
    }
}
