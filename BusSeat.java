public class BusSeat extends Seat {
    Character gender = null;
    public BusSeat(String label, int price, boolean booked, Character gender) {
        this.label = label;
        this.price = price;
        this.booked = booked;

    }

    /** BusSeat class should inherit from Seat abstract class
        It should override getSummary() and toString() methods.
     */

    @Override
    public String toString() {
        return ""; // TODO: this should return a string representation of object
    }

    @Override
    public String getSummary() {
        if (gender==null){
            return "BusSeat - Label: " + getLabel() + " - Price: " + getPrice() + " TL - Booked: " + booked;
        }else {
            return "BusSeat - Label: " + getLabel() + " - Price: " + getPrice() + " TL - Booked: " + booked + " (" + gender + ")";
        }
        //return ""; // TODO: implement this method so that it returns a summary
    }
}

