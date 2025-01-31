//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

public  class Plane  extends Vehicle{
    // Plane class should inherit from Vehicle abstract class and override its abstract methods

    public Plane(String model, String identifier, int capacity, int businessSeatCount, ArrayList seatList){
        this.model = model;
        this.identifier = identifier;
        this.capacity = capacity;
        this.seatList = seatList;
        this.businessSeatCount = businessSeatCount;


    }
    /*
     * @param seatLabel: Seat label as string "13A", "21B" etc..
     * NOTE: If it's a business seat, still it does not start with an * asterisk.
     * Asterisk character is only used during seat table printing.
     */

// public static Plane CreatePlane(String model,String identifier,int capacity, int businessSeatCount) throws NotImplementedException


    public static Plane CreatePlane(String model,String identifier,int capacity, int businessSeatCount) { //this method create a Plane or LargePlane object with that parameters according to data
        switch (model){
            case "Boeing":
                ArrayList<Seat> seatListBoeing=new ArrayList<>();
                int row = 1;
                char letter='A';
                for (int i = 1; i <= capacity ; i++) {
                    String seatLabel=String.valueOf(row)+letter;
                    if(i <= businessSeatCount){
                        seatListBoeing.add(new BusinessPlaneSeat(seatLabel, 0, false, 0));
                }else{
                        seatListBoeing.add(new PlaneSeat(seatLabel, 0, false, 0));}
                    letter++;
                    if(i % 6 == 0)
                    {
                        row++;
                        letter='A';
                    }
                }
                Plane plane = new Plane(model,identifier, capacity, businessSeatCount, seatListBoeing);
                Main.vehicles.add(plane);
                System.out.println("Vehicle was added. " + plane.toString());

                break;
            case "Airbus":
                ArrayList<Seat> seatListAirbus =new ArrayList<>();
                int rowAirbus = 1;
                char letterAirbus = 'A';
                for (int i = 1; i <= capacity ; i++) {
                    String seatLabel=String.valueOf(rowAirbus)+letterAirbus;
                    if(i <= businessSeatCount){
                        seatListAirbus.add(new BusinessPlaneSeat(seatLabel, 0, false, 0));
                    }else{
                        seatListAirbus.add(new PlaneSeat(seatLabel, 0, false, 0));}
                    letterAirbus++;
                    if(i % 8 == 0)
                    {
                        rowAirbus++;
                        letterAirbus='A';
                    }
                }
                LargePlane largePlane = new LargePlane(model,identifier,capacity,businessSeatCount,seatListAirbus);
                Main.vehicles.add(largePlane);
                System.out.println("Vehicle was added. " + largePlane.toString());

                break;
            default:
                //throw new NotImplementedException();
                System.out.println("VEHICLE CANNOT BE IMPLENETED");
        }
        return null;
    }
    public void bookSeat(String seatLabel) {
        // TODO: implement seat booking operation here
    }

    @Override
    public String toString() {
        return "Plane" + " (" + identifier + ")," + " model: " + model +", capacity: " + capacity + ", businessSeatCount: " + businessSeatCount; // TODO: this should return a string representation of object
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
                System.out.print("*" +seat.label + "\t" +"[" + bookedChar + "]");
            }else {
                System.out.print(seat.label + "\t" +"[" + bookedChar + "]");
            }
            if (rowIndex==3){
                System.out.print("\t" + "\t" + "\t");
            }else {
                System.out.print("\t");
            }
            rowIndex++;
            bookedChar = ' ';
            if (rowIndex==7){
                rowIndex=1;
                System.out.println();
            }
        }
    }
}
