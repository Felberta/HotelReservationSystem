public class Reservation {
    private Room room;
    private Customer customer;

    public Reservation(Room room, Customer customer) {
        this.room = room;
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String toString() {
        return "Reservation - Room: " + room.getRoomNumber() + ", Name: " + customer.getName() + ", Contact: " + customer.getContact();
    }
}