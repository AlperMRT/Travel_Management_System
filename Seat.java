public abstract class Seat {

    String label;
    int price;
    boolean booked;

    public abstract String getSummary();  // TODO: Must implement this method in child classes

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {

        this.price = price;
    }

}
