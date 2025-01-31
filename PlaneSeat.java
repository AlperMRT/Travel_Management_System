public class PlaneSeat extends Seat{
    /** PlaneSeat class should inherit from Seat abstract class
     *  This should override getSummary(), toString() methods
     *  Also it should store baggage limit as an integer property.
     */
    int baggageLimit;
    public PlaneSeat(String label, int price, boolean booked, int baggageLimit){
        this.label = label;
        this.price = price;
        this.booked = booked;
        this.baggageLimit = baggageLimit;
    }


    @Override
    public String toString() {
        return ""; // TODO: this should return a string representation of object
    }

    public int getBaggageLimit() {
        return 0; // TODO: this should return baggage limit
    }

    @Override
    public String getSummary() {

        return "PlaneSeat - Label: " + getLabel() + " - Price: " + price + " TL - Baggage Limit: " + baggageLimit + " kg - Booked: " + booked;
        //return ""; // TODO: implement this method so that it returns a summary
    }
}
