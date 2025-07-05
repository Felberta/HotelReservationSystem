import java.util.*;
import java.io.*;

public class BookingManager {
    private List<Room> rooms = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    public BookingManager() {
        loadRooms();
        loadReservationsFromFile();
    }

    private void loadRooms() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/rooms.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int number = Integer.parseInt(parts[0]);
                String type = parts[1];
                boolean available = Boolean.parseBoolean(parts[2]);
                double price = Double.parseDouble(parts[3]);
                rooms.add(new Room(number, type, available, price));
            }
        } catch (IOException e) {
            System.out.println("Error reading rooms: " + e.getMessage());
        }
    }

    private void saveReservationsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data/reservations.txt"))) {
            for (Reservation r : reservations) {
                writer.println(r.getRoom().getRoomNumber() + "," +
                               r.getCustomer().getName() + "," +
                               r.getCustomer().getContact());
            }
        } catch (IOException e) {
            System.out.println("Error saving reservations: " + e.getMessage());
        }
    }

    private void loadReservationsFromFile() {
        File file = new File("data/reservations.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int roomNumber = Integer.parseInt(parts[0]);
                String name = parts[1];
                String contact = parts[2];

                for (Room r : rooms) {
                    if (r.getRoomNumber() == roomNumber) {
                        r.setAvailable(false);
                        Reservation res = new Reservation(r, new Customer(name, contact));
                        reservations.add(res);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading reservations: " + e.getMessage());
        }
    }

    public void showAvailableRooms() {
        for (Room r : rooms) {
            if (r.isAvailable())
                System.out.println(r);
        }
    }

    public void bookRoom(int roomNumber, String name, String contact) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber && r.isAvailable()) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter card number (dummy): ");
                String card = scan.nextLine();
                System.out.println("Processing payment...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\u2705 Payment successful for Rs. " + r.getPrice());

                Customer c = new Customer(name, contact);
                Reservation res = new Reservation(r, c);
                reservations.add(res);
                r.setAvailable(false);
                saveReservationsToFile();
                System.out.println("Booking Successful: " + res);
                return;
                
            }
        }
        System.out.println("Room not available or doesn't exist.");
    }

    public void cancelReservation(int roomNumber, String name) {
        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation r = iterator.next();
            if (r.getRoom().getRoomNumber() == roomNumber && r.getCustomer().getName().equalsIgnoreCase(name)) {
                r.getRoom().setAvailable(true);
                iterator.remove();
                saveReservationsToFile();
                System.out.println("Reservation Cancelled.");
                return;
            }
        }
        System.out.println("No matching reservation found.");
    }

    public void viewAllBookings() {
        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }
}
