import java.util.ArrayList;

public class LargePlane extends Plane {
    /** LargePlane class should inherit from Plane class and
     *  override printSeatsTable(), bookSeat(String seatLabel), toString() functions
     */
    int row = 8;

    public LargePlane(String model, String identifier, int capacity, int businessSeatCount, ArrayList seatList) {
        super(model, identifier, capacity, businessSeatCount, seatList);
    }

    @Override
    public String toString() {
       return "LargePlane" + " (" + identifier + ")," + " model: " + model +", capacity: " + capacity + ", businessSeatCount: " + businessSeatCount;
        //return super.toString();
    }

    @Override
    public void bookSeat(String seatLabel) {
        super.bookSeat(seatLabel);
    }

    @Override
    public void printSeatsTable() {
        int rowIndex = 1;
        Character bookedChar = ' ';

        for (Seat seat:seatList){
            if (seat.booked==true){
                bookedChar = 'X';
            }
            if (seat instanceof BusinessPlaneSeat){
                System.out.print("*" + seat.label + "\t" +"[" + bookedChar + "]");
            }else {
                System.out.print(seat.label + "\t" +"[" + bookedChar + "]");
            }
            if (rowIndex==2){
                System.out.print("\t" + "\t" + "\t");
            }else if (rowIndex==6){
                System.out.print("\t" + "\t" + "\t");
            }else {
                System.out.print("\t");
            }
            rowIndex++;
            bookedChar = ' ';
            if (rowIndex==9){
                rowIndex=1;
                System.out.println();
            }
        }

    }

}
