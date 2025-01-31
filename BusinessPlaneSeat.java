public class BusinessPlaneSeat extends PlaneSeat{
    int baggageLimit = super.baggageLimit*2;
    public BusinessPlaneSeat(String label, int price, boolean booked, int baggageLimit) {
        super(label, price, booked, baggageLimit);
    }

    /**
     BusinessPlaneSeat class should inherit from PlaneSeat class
     also it should override getBaggageLimit(), getPrice(), getSummary()
     */
    @Override
    public String toString() {
        return ""; // TODO: this should return a string representation of object
        // TODO a business plane seat should have * asterisk prefix before its label (e.g. *2A)
    }

    @Override
    public String getSummary() {
        return "BusinessPlaneSeat - Label: " + getLabel() + " - Price: " + price + " TL - Baggage Limit: " + baggageLimit + " kg - Booked: " + booked;
        //return ""; // TODO: implement this method so that it returns a summary
    }
}
