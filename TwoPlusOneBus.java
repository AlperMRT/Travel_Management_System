import java.util.ArrayList;

public class TwoPlusOneBus extends Bus{
    /**
     * @param model
     * @param identifier
     * @param capacity
     * @param seatList
     */
    public TwoPlusOneBus(String model, String identifier, int capacity, ArrayList seatList, int businessSeatCount) {
        super(model, identifier, capacity, seatList);
        this.businessSeatCount = businessSeatCount;
    }
    /** TwoPlusOneBus class should inherit from Bus class and
     *  override printSeatsTable(), bookSeat(String seatLabel, char gender), toString() functions
     */
    @Override
    public void printSeatsTable() {
        int rowIndex = 1;
        Character genderChar = ' ';
        for (Seat seat:seatList){
            if (seat.booked==true&&seat instanceof BusSeat){
                genderChar = ((BusSeat) seat).gender;
            }
            if (seat instanceof BusinessBusSeat) {
                System.out.print("*" + seat.label + "\t" + "[" + genderChar + "]");
            }else {
                System.out.print(seat.label + "\t" + "[" + genderChar + "]");
            }
            if (rowIndex==1){
                System.out.print("\t" + "\t" + "\t");
            }else {
                System.out.print("\t");
            }
            rowIndex++;
            genderChar = ' ';

            if (rowIndex==4){
                rowIndex=1;
                System.out.println();
            }
        }
    }

    @Override
    public boolean checkNeighbourSeat(BusSeat seat, ArrayList<Seat> seats, char gender) { //this method checks neighbor seat's passenger's gender
        int neighbourLabel;
        if (Integer.valueOf(seat.label)%3==2){
            neighbourLabel = Integer.valueOf(seat.label)+1;
            BusSeat neighbourSeat = (BusSeat) seats.stream().filter(s->s.label.equals(String.valueOf(neighbourLabel))).findAny().orElse(null);
            if (neighbourSeat.gender!=null&&!neighbourSeat.gender.equals(gender)) {
                return false;
            }
        }else if (Integer.valueOf(seat.label)%3==0){
            neighbourLabel = Integer.valueOf(seat.label)-1;
            BusSeat neighbourSeat = (BusSeat) seats.stream().filter(s->s.label.equals(String.valueOf(neighbourLabel))).findAny().orElse(null);
            if (neighbourSeat.gender!=null&&!neighbourSeat.gender.equals(gender)) {
                return false;
            }
        }
        return true;
    }

   /* @Override
    public void printSeatSummaries() {
        System.out.println("\n### START OF SEAT SUMMARIES ###\n" + getClass().getName() + " (" + identifier + "), " + "model: " + model + ", capacity: " + capacity);
        for(Seat seat : seatList) {
            System.out.println(seat.getSummary());
        }
        System.out.println("### END OF SEAT SUMMARIES ###");
        System.out.println();

    }*/

    @Override
    public String toString() {
        return "TwoPlusOneBus" + " (" + identifier + ")," + " model: " + model +", capacity: " + capacity;
    }
}
