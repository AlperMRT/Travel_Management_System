//import sun.reflect.generics.reflectiveObjects.NotImplementedException;


import java.util.ArrayList;

public class Bus extends Vehicle {
    // Bus class should inherit from Vehicle abstract class and override its abstract methods

    /*
     *
     * @param seatLabel: Seat label as string "13", "21" etc..
     * @param gender: Either "M" or "F" as a char variable
     */
    public Bus(String model, String identifier, int capacity, ArrayList seatList){
        this.model = model;
        this.identifier = identifier;
        this.capacity = capacity;
        this.seatList = seatList;
    }

    public static Bus CreateBus(String model, String identifier, int capacity) { //this method create a Bus or TwoPlusOneBus object with that parameters according to the data
        switch (model) {
            case "Tourismo":
                ArrayList tourismoSeatList = new ArrayList();
                for (int i = 1; i <= capacity; i++) {
                    String seatLabel = String.valueOf(i) ;

                    tourismoSeatList.add(new BusSeat(seatLabel, 0, false,null));
                }
                Bus busTourismo = new Bus(model,identifier,capacity,tourismoSeatList);
                Main.vehicles.add(busTourismo);
                System.out.println("Vehicle was added. " + busTourismo.toString());
                break;

            case "Fortuna":
                ArrayList fortunaSeatList = new ArrayList();
                for (int i = 1; i <= capacity; i++) {
                    String seatLabel = String.valueOf(i);
                    fortunaSeatList.add(new BusSeat(seatLabel, 0, false, null));
                }
                Bus busFortuna = new Bus(model,identifier,capacity,fortunaSeatList);
                Main.vehicles.add(busFortuna);
                System.out.println("Vehicle was added. " + busFortuna.toString());
                break;

            case "Neoplan":
                ArrayList neoplanSeatList = new ArrayList();
                for (int i = 1; i <= capacity; i++) {
                    String seatLabel = String.valueOf(i);
                    if (i%3==1){
                        neoplanSeatList.add(new BusinessBusSeat(seatLabel,0,false,null));
                    }else {
                        neoplanSeatList.add(new BusSeat(seatLabel, 0, false, null));
                    }

                }
                TwoPlusOneBus busNeoplan = new TwoPlusOneBus(model,identifier,capacity,neoplanSeatList,capacity/3);
                Main.vehicles.add(busNeoplan);
                System.out.println("Vehicle was added. " + busNeoplan.toString());
                break;

            case "Starliner":
                ArrayList starlinerSeatList = new ArrayList();
                for (int i = 1; i <= capacity; i++) {
                    String seatLabel = String.valueOf(i);
                    if (i%3==1){
                        starlinerSeatList.add(new BusinessBusSeat(seatLabel,0,false,null));
                    }else {
                        starlinerSeatList.add(new BusSeat(seatLabel, 0, false, null));
                    }

                }
                TwoPlusOneBus busStarliner = new TwoPlusOneBus(model,identifier,capacity,starlinerSeatList,capacity/3);
                Main.vehicles.add(busStarliner);
                System.out.println("Vehicle was added. " + busStarliner.toString());
                break;

                default:
                //throw new NotImplementedException();
                    System.out.println("VEHICLE CANNOT BE IMPLEMENTED");
        }
        return null;
    }
    public boolean checkNeighbourSeat(BusSeat seat, ArrayList<Seat> seats, char gender){  //this method checks the neighbour seat's passenger' gender
        int neighbourLabel;
        if (Integer.valueOf(seat.label)%4==1){
            neighbourLabel = Integer.valueOf(seat.label)+1;
            BusSeat neighbourSeat = (BusSeat) seats.stream().filter(s->s.label.equals(String.valueOf(neighbourLabel))).findAny().orElse(null);
            if (neighbourSeat.gender!=null&&!neighbourSeat.gender.equals(gender)) {
                return false;
            }
        }else if (Integer.valueOf(seat.label)%4==2){
            neighbourLabel = Integer.valueOf(seat.label)-1;
            BusSeat neighbourSeat = (BusSeat) seats.stream().filter(s->s.label.equals(String.valueOf(neighbourLabel))).findAny().orElse(null);
            if (neighbourSeat.gender!=null&&!neighbourSeat.gender.equals(gender)) {
                return false;
            }
        }else if (Integer.valueOf(seat.label)%4==3){
            neighbourLabel = Integer.valueOf(seat.label)+1;
            BusSeat neighbourSeat = (BusSeat) seats.stream().filter(s->s.label.equals(String.valueOf(neighbourLabel))).findAny().orElse(null);
            if (neighbourSeat.gender!=null&&!neighbourSeat.gender.equals(gender)) {
                return false;
            }
        }else if (Integer.valueOf(seat.label)%4==0) {
            neighbourLabel = Integer.valueOf(seat.label) - 1;
            BusSeat neighbourSeat = (BusSeat) seats.stream().filter(s -> s.label.equals(String.valueOf(neighbourLabel))).findAny().orElse(null);
            if (neighbourSeat.gender != null  &&! neighbourSeat.gender.equals(gender)) {
                return false;
            }
        }
        return true;
    }

    public void bookSeat(String seatLabel, char gender) {
        // TODO: implement seat booking operation here
        BusSeat seat = (BusSeat) seatList.stream().filter(s->s.label.equals(seatLabel)).findAny().orElse(null);
        if (seat!=null){
            seat.gender = gender;
            seat.setBooked(true);
        }
    }


    @Override
    public String toString() {
        return "Bus" + " (" + identifier + ")," + " model: " + model +", capacity: " + capacity; // TODO: this should return a string representation of object
    }

    @Override
    public void printSeatsTable() {
        int rowIndex = 1;
        Character genderChar = ' ';
        for (Seat seat:seatList){
            if (seat.booked==true&&seat instanceof BusSeat){
                genderChar = ((BusSeat) seat).gender;
            }
            System.out.print(seat.label + "\t" + "[" + genderChar + "]");
            if (rowIndex==2){
                System.out.print("\t" + "\t" + "\t");
            }else {
                System.out.print("\t");
            }
            rowIndex++;
            genderChar = ' ';
            if (rowIndex==5){
                rowIndex=1;
                System.out.println();
            }
        }
    }
}
