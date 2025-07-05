import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookingManager manager = new BookingManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n\uD83C\uDFE8 HOTEL RESERVATION SYSTEM");
            System.out.println("1. Show Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View All Bookings");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    manager.showAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter room number: ");
                    int rn = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter contact: ");
                    String contact = sc.nextLine();
                    manager.bookRoom(rn, name, contact);
                    break;
                case 3:
                    System.out.print("Enter room number to cancel: ");
                    int cancelRoom = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter your name: ");
                    String cancelName = sc.nextLine();
                    manager.cancelReservation(cancelRoom, cancelName);
                    break;
                case 4:
                    manager.viewAllBookings();
                    break;
                case 0:
                    System.out.println("Thanks for using the system.");
                    break;
                default:
                    System.out.println("Invalid input.");
            }
        } while (choice != 0);

        sc.close();
    }
}
