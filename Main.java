import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static Path commandsFilepath;
    static Path outputFilepath;
    static List<Vehicle> vehicles = new ArrayList<Vehicle>() {};
    static boolean printToOutputFile = true;
    static ArrayList commandList = new ArrayList<>();

    public static void main(String[] args) {
        commandsFilepath = Paths.get(args[0]);
        outputFilepath = Paths.get(args[1]);

        // DON'T CHANGE LINES BELOW, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE
        ByteArrayOutputStream baos = null;
        if (printToOutputFile) {
            baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
        }
        // DON'T CHANGE THESE LINES, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE

        // DO YOUR WORK HERE...

        // DON'T CHANGE LINES BELOW, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE
        try {
            for (int i = 0; i<Files.lines(commandsFilepath).count();i++) { // in this loop commands will be read line by line do the command operations by checking commands' static part.
                if (Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).startsWith("ADD VEHICLE PLANE")){
                    String model = Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[3];
                    String id = Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[4];
                    int capacity = Integer.parseInt(Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[5]);
                    int businessSeatCount = Integer.parseInt(Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[6]);
                    Plane.CreatePlane(model,id,capacity,businessSeatCount);
                    continue;


                }else if (Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).startsWith("ADD VEHICLE BUS")){
                    String model = Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[3];
                    String id = Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[4];
                    int capacity = Integer.parseInt(Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[5]);
                    Bus.CreateBus(model,id,capacity);
                    continue;

                }else if (Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).startsWith("SET BASIC SEAT PRICE")){
                    int price = Integer.parseInt(Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[4]);
                    String id = (Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[6]);
                    Vehicle vehicle = vehicles.stream().filter(v ->v.identifier.equals(id)).findAny().orElse(null);
                    for (Seat seat: vehicle.seatList){
                        if (seat instanceof BusinessPlaneSeat) {
                            seat.setPrice((int) (price*1.8));
                        }else if (seat instanceof PlaneSeat){
                            seat.setPrice(price);
                        }else if (seat instanceof BusinessBusSeat){
                            seat.setPrice((int) (price*1.3));
                        }else if (seat instanceof BusSeat){
                            seat.setPrice(price);
                        }
                    }
                    System.out.println("Basic seat price was set to " + price + " TL for " + id + ".");
                    continue;

                }else if (Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).startsWith("SET BASIC BAGGAGE LIMIT")){
                    int limit = Integer.parseInt(Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[4]);
                    String id = (Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[6]);
                    Vehicle vehicle = vehicles.stream().filter(v ->v.identifier.equals(id)).findAny().orElse(null);
                    String message = null;
                    for (Seat seat: vehicle.seatList){
                        if (seat instanceof BusinessPlaneSeat){
                            ((BusinessPlaneSeat) seat).baggageLimit = limit*2;
                            message = "Basic baggage limit was set to " + limit*2 + " kg for " + id+".";
                        }else if (seat instanceof PlaneSeat){
                            ((PlaneSeat) seat).baggageLimit = limit;
                            message = "Basic baggage limit was set to " + limit + " kg for " + id+".";
                        }
                    }
                    System.out.println(message);
                    continue;

                }else if (Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).startsWith("BOOK PLANESEAT")){
                    String bookedLabel = Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[2];
                    String planeID = Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[4];
                    Vehicle vehicle = vehicles.stream().filter(v -> v.identifier.equals(planeID)).findAny().orElse(null);
                    Seat seat = vehicle.seatList.stream().filter(s ->s.label.equals(bookedLabel)).findAny().orElse(null);
                    if (seat==null){
                       System.out.println("No such seat exists.");
                    }else{
                        if (seat.booked==true){
                            System.out.println(bookedLabel + " cannot be booked, it's already booked.");
                        }else {
                            seat.booked=true;
                            seat.setBooked(true);
                            System.out.println(bookedLabel + " booked.");
                        }
                    }
                    continue;

                }else if (Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).startsWith("BOOK BUSSEAT")){
                    String bookedLabel = Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[2];
                    String gender = Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[3];
                    char g = gender.charAt(0);
                    String busID = Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[5];
                    Vehicle vehicle =  vehicles.stream().filter(v -> v.identifier.equals(busID)).findAny().orElse(null);
                    if (vehicle instanceof Bus) {
                        BusSeat seat = (BusSeat) vehicle.seatList.stream().filter(s ->s.label.equals(bookedLabel)).findAny().orElse(null);
                        if (seat==null){
                            System.out.println("No such seat exists.");
                            continue;
                        }else if (seat.booked==true) {
                            System.out.println(bookedLabel + " cannot be booked, it's already booked.");
                            continue;
                        }
                        if (((Bus) vehicle).checkNeighbourSeat(seat, (ArrayList<Seat>) vehicle.seatList,g)==true){
                            ((Bus) vehicle).bookSeat(bookedLabel,g);
                            System.out.println(bookedLabel + " booked.");
                        }else if (((Bus) vehicle).checkNeighbourSeat(seat, (ArrayList<Seat>) vehicle.seatList,g)==false){
                            System.out.println(seat.label + " cannot be booked because neighbor seat was booked by different gender person.");
                        }
                    }
                    continue;

                }else if (Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).startsWith("PRINT SEATSUMMARY")){
                    String identifier = Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[2];
                    Vehicle vehicle =  vehicles.stream().filter(v -> v.identifier.equals(identifier)).findAny().orElse(null);
                    vehicle.printSeatSummaries();
                    continue;

                }else if (Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).startsWith("PRINT SEATTABLE")){
                    String identifier = Files.readAllLines(Paths.get(String.valueOf(commandsFilepath))).get(i).split(" ")[2];
                    Vehicle vehicle =  vehicles.stream().filter(v -> v.identifier.equals(identifier)).findAny().orElse(null);
                    if (vehicle instanceof Plane){
                        System.out.println("### START OF PLANE INFO ###");
                        System.out.println(vehicle.toString());
                        System.out.println();
                        vehicle.printSeatsTable();
                        System.out.println("### END OF PLANE INFO ###");
                    }else if (vehicle instanceof Bus){
                        System.out.println("### START OF BUS INFO ###");
                        System.out.println(vehicle.toString());
                        System.out.println();
                        vehicle.printSeatsTable();
                        System.out.println("### END OF BUS INFO ###");
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (printToOutputFile) {
            try {
                Files.write(outputFilepath, Arrays.asList(baos.toString().trim().split("\n")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // DON'T CHANGE THESE LINES, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE
    }
}