public class BusinessBusSeat extends BusSeat{
    public BusinessBusSeat(String label, int price, boolean booked, Character gender) {
        super(label, price, booked, gender);
    }

    /** BusinessBusSeat class should inherit from BusSeat class
        It represents single and large seats which exist in 2+1 buses.
        It should override getPrice(), getSummary() and toString() methods.
        toString() method returns a string with * asterisk prefix before its label (e.g. *2A)
     */

    @Override
    public String toString() {
        return ""; // TODO: this should return a string representation of object
        // TODO a business plane seat should have * asterisk prefix before its label (e.g. *2A)
    }

    @Override
    public String getSummary() {
        if (gender==null){
            return "BusinessBusSeat - Label: " + getLabel() + " - Price: " + getPrice() + " TL - Booked: " + booked;
        }else {
            return "BusinessBusSeat - Label: " + getLabel() + " - Price: " + getPrice() + " TL - Booked: " + booked + " (" + gender + ")";
        }
        //return ""; // TODO: implement this method so that it returns a summary
    }
}
