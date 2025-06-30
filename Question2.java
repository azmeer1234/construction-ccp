import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ReservationApp {

    public static void main(String[] args) {
        ReservationApp app = new ReservationApp();
        app.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Guest House Reservation ===");

        String guestHouseName = promptInput(scanner, "Enter guest house name");
        String roomType = promptInput(scanner, "Enter room type");

        LocalDate startDate = promptDate(scanner, "Enter start date (YYYY-MM-DD)");
        LocalDate endDate = promptDate(scanner, "Enter end date (YYYY-MM-DD)");
        if (!validateDates(startDate, endDate)) return;

        String name = promptInput(scanner, "Enter your full name");
        String address = promptInput(scanner, "Enter your address");
        String creditCardNo = promptInput(scanner, "Enter your 16-digit credit card number");

        if (!Validator.isValidCreditCard(creditCardNo)) {
            System.out.println("nvalid credit card number. Must be exactly 16 digits.");
            return;
        }

        String confirmation = GuestHouseChainFacade.makeReservation(
                guestHouseName, startDate, endDate, roomType,
                name, address, creditCardNo
        );

        System.out.println("\n" + confirmation);
    }

    private String promptInput(Scanner scanner, String prompt) {
        System.out.print(prompt + ": ");
        String input = scanner.nextLine().trim();

        while (input.isEmpty()) {
            System.out.print("Please enter a valid input. " + prompt + ": ");
            input = scanner.nextLine().trim();
        }
        return input;
    }

    private LocalDate promptDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Use YYYY-MM-DD.");
            }
        }
    }

    private boolean validateDates(LocalDate start, LocalDate end) {
        if (start == null || end == null || end.isBefore(start)) {
            System.out.println("Invalid date range. End date must be after start date.");
            return false;
        }
        return true;
    }

    // === Validator Class ===
    static class Validator {
        public static boolean isValidCreditCard(String number) {
            return number != null && number.replaceAll("\\s+", "").matches("\\d{16}");
        }
    }

    // === Facade Stub Class ===
    static class GuestHouseChainFacade {
        public static String makeReservation(String guestHouseName, LocalDate startDate, LocalDate endDate,
                                             String roomType, String name, String address, String creditCardNo) {
            return "Reservation confirmed for " + name + " at " + guestHouseName +
                    " (" + roomType + ") from " + startDate + " to " + endDate + ".";
        }
    }
}
