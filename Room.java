public class Room {
    private int roomNumber;
    private String category;
    private boolean isAvailable;
    private double price;

    public Room(int roomNumber, String category, boolean isAvailable, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return "Room " + roomNumber + " (" + category + ") - Rs." + price + " - " +
               (isAvailable ? "Available" : "Booked");
    }
}
