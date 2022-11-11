import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
public class FlightSimulator {
    public static void main(String[] args){
        Flight[] flightTerminal = new Flight[20];
        double probarrival;
        double probdequeue;
        double probnewflight;
        String flightclass = null;
        String destination = null;
        int mins;
        int updatedtime = 0;
        long seed;
        boolean depart = false;
        boolean currboarding = false;
        boolean findepart = false;
        Scanner inp = new Scanner(System.in);
        System.out.println("Welcome to RFK Private International Airport!");
        System.out.println("Enter a seed for this simulation:");
        seed = inp.nextLong();
        System.out.println("Enter the probability of a passenger arrival: ");
        probarrival = inp.nextDouble();
        System.out.println("Enter the probability of a passenger is dequeued: ");
        probdequeue = inp.nextDouble();
        System.out.println("Enter the probability that there is a new flight at RFK");
        probnewflight = inp.nextDouble();
        System.out.println("Enter how many minutes this simulation should take: ");
        mins = inp.nextInt();
        //random
        Random rand = new Random();
        rand.setSeed(seed);
        System.out.println(
                "Events: \nNothing to note… \nCurrently Boarding: \nNothing to note… \nDeparting \nNothing to note… \nFinal Departures \nNothing to note…\n\n\n"
        );



        for(int i = 1; i < mins; i++){

            boolean event = false;
            float arrival_chance = rand.nextFloat();
            float goon_plane = rand.nextFloat();
            float flight = rand.nextFloat();
            //if the flight random value is .95 and less then a flight happens
            System.out.println("Minute " + i);
            System.out.println("Events:");
            for(int j = 0; j < 20; j++){//change?
                if (flightTerminal[j] != null) {
                    event = true;
                    break;
                }
            }

            //new flights
            if(flight < probnewflight){//change this prob?
                flight = rand.nextFloat();
                if(flight < .5) {
                    for (int j = 0; j < 20; j++) {
                        if (flightTerminal[j] == null) {//fill in flight i guess
                            BoardingQueue c = new BoardingQueue();
                            System.out.println("Enter the destination of this new Plane Ride:");
                            destination = inp.next();
                            flightTerminal[j] = new Flight(15, 5, 1, 1, 1, 1, true, c,false, destination);
                            System.out.println(flightTerminal[j].getDestination() + "...... is being boarded atm");///////
                            break;
                        }
                    }
                }
                //so basically when this occurs which is 5%, everything stops
                else{
                    for (int j = 0; j < 20; j++) {
                        if (flightTerminal[j] == null) {//fill in flight i guess
                            BoardingQueue c = new BoardingQueue();
                            System.out.println("Enter the destination of this new Plane Ride:");
                            destination = inp.next();
                            flightTerminal[j] = new Flight(15, 5, 1, 1, 1, 1, true,c,true, destination);
                            
                            System.out.println("Airforce 1 to " + flightTerminal[j].getDestination() + "...... is being boarded atm");///////
                            System.out.println("Boarding will resume once AirForce 1 departs");
                            break;
                        }
                    }
                    //new airforce plane basically
                    ////////////////////////////
                    for(int l = i; l < mins; l++) {//loop1
                        updatedtime = l;
                        //loop2
                        System.out.println("Minutes: " + updatedtime);
                        System.out.println("\nEvents");


                        for (int k = 0; k < 20; k++) {//basically looks at each one then checks to see if it be triggering
                            arrival_chance = rand.nextFloat();
                            if (probarrival > arrival_chance ) {//checks if

                                if (flightTerminal[k] != null && flightTerminal[k].isBoarding() && flightTerminal[k].isAirforce1()) {//might have issues with departing
                                    //Set condition such to stop adding people when the plan is not boarding// change the boolean when the timer is done
                                    //fill in id, arrival time which is i

                                    float class_d = rand.nextFloat();
                                    if (class_d < .1) {
                                        flightclass = "f";
                                    } else if (class_d < .2) {
                                        flightclass = "b";
                                    } else if (class_d < .5) {
                                        flightclass = "p";
                                    } else if (class_d < .9) {
                                        flightclass = "e";
                                    } else {
                                        flightclass = "c";
                                        i += 10;
                                    }

                                    Passenger generated = new Passenger(flightTerminal[k].getBoardingQueue().getSize(), updatedtime, flightclass);
                                    //basically if the guy got covid dont add him dont increment size
                                    if (!flightclass.equals("c")) {
                                        flightTerminal[k].getBoardingQueue().setSize(flightTerminal[k].getBoardingQueue().getSize() + 1);
                                        try {
                                            flightTerminal[k].getBoardingQueue().enqueuePassenger(generated);/////////////////////////
                                        } catch (BoardingQueue.FullQueueException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    switch (flightclass) {
                                        case "f" -> System.out.println("First-class passenger (ID" + generated.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " got on the queue");
                                        case "b" -> System.out.println("Business-class passenger (ID" + generated.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " got on the queue");
                                        case "p" -> System.out.println("Premium Economy-class passenger (ID" + generated.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " got on the queue");
                                        case "e" -> System.out.println("Economy-class passenger (ID" + generated.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " got on the queue");
                                        //add 10 mins to i basically
                                        case "c" -> System.out.println("COVID positive passenger found attempting to board flight to " + flightTerminal[k].getDestination() + " All current departures and boarding extended by 10 minutes!");

                                    }
                                    //So there is someone so get the rear of that person and get their passenger id and add 1
                                    event = true;
                                    break;
                                }
                            }

                            //assuming that this is the only airforce 1, we will break
                        }

                        //going on plane

                        //this boards the person then we add the next person to the queue

                        //going from queue to plane

                        for (int k = 0; k < 20; k++) {//might have issues with departing might need to change this
                            //also need to look at dequeuing a empty queue
                            goon_plane = rand.nextFloat();
                            if (probdequeue > goon_plane) {
                                if (flightTerminal[k] != null && flightTerminal[k].getBoardingQueue().getFront() != -1 && flightTerminal[k].isBoarding() && flightTerminal[k].isAirforce1()) {
                                    Passenger outofqueue = null;
                                    try {
                                        outofqueue = flightTerminal[k].getBoardingQueue().dequeuePassenger();
                                    } catch (BoardingQueue.EmptyQueueException e) {
                                        e.printStackTrace();
                                    }
                                    //This is for the show
                                    switch (outofqueue.getPassClass()) {
                                        case First_Class -> System.out.println("First-class passenger (ID" + outofqueue.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " has boarded on a first-class seat!");//change
                                        case Business_Class -> System.out.println("Business-class passenger (ID" + outofqueue.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " has boarded on a Business-class seat!");
                                        case Premium_Economy_CLass -> System.out.println("Premium Economy-class passenger (ID" + outofqueue.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " has boarded on a first-class seat!");
                                        case Economy_Class -> System.out.println("Economy-class passenger (ID" + outofqueue.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " has boarded on a first-class seat!");
                                        case Covid_Class -> System.out.println("COVID positive passenger found attempting to board flight to (ID" + flightTerminal[k].getDestination() + ") All current departures and boarding extended by 10 minutes!");
                                    }
                                    //This is for based on the class set them to the class of the flight check if it is full first, if not, then set them to a lower class by changing that class
                                    flightTerminal[k].addToFlight(outofqueue);//check if the right ones are goingn to seperate things
                                    break;//as long as it breaks out since we are only looking at 1
                                }
                            }
                        }

                        System.out.println("\nCurrently Boarding:");

                        for (int j = 0; j < 20; j++) {
                            if (flightTerminal[j] != null && flightTerminal[j].getMinutesLeftBoarding() > 0 && flightTerminal[j].isAirforce1()) {

                                System.out.println("Flight to " + flightTerminal[j].getDestination() + " has " + flightTerminal[j].getMinutesLeftBoarding()
                                        + " minutes to board, " + flightTerminal[j].getBoardingQueue().getAmountonboard() + " passenger(s), and " + flightTerminal[j].getBoardingQueue().getAmountinqueue() + " person(s) waiting to board.");
                                flightTerminal[j].setMinutesLeftBoarding(flightTerminal[j].getMinutesLeftBoarding() - 1);
                                currboarding = true;
                            }
                        }
                        if(!currboarding){
                            System.out.println("No event occuring");
                        }


                        System.out.println("\nDeparting:");

                        for (int j = 0; j < 20; j++) {
                            if (flightTerminal[j] != null && flightTerminal[j].getMinutesLeftBoarding() <= 0 && flightTerminal[j].getMinutesLeftDeparting() > 0 && flightTerminal[j].isAirforce1()) {
                                flightTerminal[j].setBoarding(false);
                                System.out.println("Flight to " + flightTerminal[j].getDestination() + " has " + flightTerminal[j].getMinutesLeftDeparting()
                                        + " minutes to board, " + flightTerminal[j].getBoardingQueue().getAmountonboard() + " passenger(s)");
                                flightTerminal[j].setMinutesLeftDeparting(flightTerminal[j].getMinutesLeftDeparting() - 1);
                                depart = true;
                            }
                        }
                        if(!depart){
                            System.out.println("No events occurring");
                        }
                        System.out.println("\nFinal Departures:");
                        boolean airforce1left = false;

                        for (int j = 0; j < 20; j++) {
                            if (flightTerminal[j] != null && flightTerminal[j].getMinutesLeftDeparting() <= 0 && flightTerminal[j].isAirforce1()) {
                                System.out.println("Flight to " + flightTerminal[j].getDestination() + " has departed");
                                System.out.format("%25s%15s%15s\n", "Seat Type", "ID", "Arrival Time");
                                flightTerminal[j].toString();
                                flightTerminal[j] = null;//so to clear this airport, we need to clear everything else basically
                                airforce1left = true;
                            }
                        }
                        System.out.println("\n\n\n");
                        if(!airforce1left){
                            System.out.println("No events Occurring");
                        }
                        if(airforce1left){
                            break;
                        }

                    }
                        //////////////////////////////////////////////////////
                    i = updatedtime + 1;
                    System.out.println("Minute " + i);
                    System.out.println("Event\n");
                }
                event = false;
                findepart = false;
                depart = false;
                currboarding = false;
            }
            if(!event){
                System.out.println("No events atm");
            }


            //how are ids made? How are arrivaltimes made?
            //So if a person arrives
            //going on queue
            //might need to account for the idea of full queue in this regarding size.


            for(int k = 0; k < 20; k++) {//basically looks at each one then checks to see if it be triggering
                arrival_chance = rand.nextFloat();
                if(probarrival > arrival_chance) {
                    if (flightTerminal[k] != null && flightTerminal[k].isBoarding()) {//might have issues with departing
                        //Set condition such to stop adding people when the plan is not boarding// change the boolean when the timer is done
                        //fill in id, arrival time which is i

                        float class_d = rand.nextFloat();
                        if (class_d < .1) {
                            flightclass = "f";
                        } else if (class_d < .2) {
                            flightclass = "b";
                        } else if (class_d < .5) {
                            flightclass = "p";
                        } else if (class_d < .9) {
                            flightclass = "e";
                        } else {
                            flightclass = "c";
                            i += 10;
                        }

                        Passenger generated = new Passenger(flightTerminal[k].getBoardingQueue().getSize(), i, flightclass);
                        //basically if the guy got covid dont add him dont increment size
                        if(!flightclass.equals("c")) {
                            flightTerminal[k].getBoardingQueue().setSize(flightTerminal[k].getBoardingQueue().getSize() + 1);
                            try {
                                flightTerminal[k].getBoardingQueue().enqueuePassenger(generated);/////////////////////////
                            } catch (BoardingQueue.FullQueueException e) {
                                e.printStackTrace();
                            }
                        }

                        switch (flightclass) {
                            case "f" -> System.out.println("First-class passenger (ID" + generated.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " got on the queue");
                            case "b" -> System.out.println("Business-class passenger (ID" + generated.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " got on the queue");
                            case "p" -> System.out.println("Premium Economy-class passenger (ID" + generated.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " got on the queue");
                            case "e" -> System.out.println("Economy-class passenger (ID" + generated.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " got on the queue");
                            //add 10 mins to i basically
                            case "c" -> System.out.println("COVID positive passenger found attempting to board flight to " + flightTerminal[k].getDestination() + " All current departures and boarding extended by 10 minutes!");

                        }
                        //So there is someone so get the rear of that person and get their passenger id and add 1
                    }
                }
            }

            //going on plane

            //this boards the person then we add the next person to the queue

            //going from queue to plane

            for(int k = 0; k < 20; k++) {//might have issues with departing might need to change this
                //also need to look at dequeuing a empty queue
                goon_plane = rand.nextFloat();
                if(probdequeue > goon_plane) {
                    if(flightTerminal[k] != null && flightTerminal[k].getBoardingQueue().getFront() !=  -1  && flightTerminal[k].isBoarding()){
                        Passenger outofqueue = null;
                        try {
                            outofqueue = flightTerminal[k].getBoardingQueue().dequeuePassenger();
                        } catch (BoardingQueue.EmptyQueueException e) {
                            e.printStackTrace();
                        }
                        //This is for the show
                        switch (outofqueue.getPassClass()){
                            case First_Class -> System.out.println("First-class passenger (ID" + outofqueue.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " has boarded on a first-class seat!");//change
                            case Business_Class -> System.out.println("Business-class passenger (ID" + outofqueue.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " has boarded on a Business-class seat!");
                            case Premium_Economy_CLass -> System.out.println("Premium Economy-class passenger (ID" + outofqueue.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " has boarded on a first-class seat!");
                            case Economy_Class -> System.out.println("Economy-class passenger (ID" + outofqueue.getPassengerID() + ") on flight to " + flightTerminal[k].getDestination() + " has boarded on a first-class seat!");
                            case Covid_Class -> System.out.println("COVID positive passenger found attempting to board flight to (ID" + flightTerminal[k].getDestination() + ") All current departures and boarding extended by 10 minutes!");
                        }
                        //This is for based on the class set them to the class of the flight check if it is full first, if not, then set them to a lower class by changing that class
                        flightTerminal[k].addToFlight(outofqueue);//check if the right ones are goingn to seperate things

                    }
                }
            }

            System.out.println("\nCurrently Boarding:");

            for(int j = 0; j < 20; j++){
                if(flightTerminal[j] != null && flightTerminal[j].getMinutesLeftBoarding() > 0){

                    System.out.println("Flight to "+ flightTerminal[j].getDestination() + " has " + flightTerminal[j].getMinutesLeftBoarding()
                            + " minutes to board, " + flightTerminal[j].getBoardingQueue().getAmountonboard() + " passenger(s), and " + flightTerminal[j].getBoardingQueue().getAmountinqueue() + " person(s) waiting to board.");
                    flightTerminal[j].setMinutesLeftBoarding(flightTerminal[j].getMinutesLeftBoarding()-1);
                    currboarding = true;
                }
            }
            if(!currboarding){
                System.out.println("No events occurring");
            }

            System.out.println("\nDeparting:");

            for(int j = 0; j < 20; j++){
                if(flightTerminal[j] != null && flightTerminal[j].getMinutesLeftBoarding() <= 0 && flightTerminal[j].getMinutesLeftDeparting() > 0){
                    flightTerminal[j].setBoarding(false);
                    System.out.println("Flight to "+ flightTerminal[j].getDestination() + " has " + flightTerminal[j].getMinutesLeftDeparting()
                            + " minutes to board, " + flightTerminal[j].getBoardingQueue().getAmountonboard() + " passenger(s)");
                    flightTerminal[j].setMinutesLeftDeparting(flightTerminal[j].getMinutesLeftDeparting()-1);
                    depart = true;
                }
            }
            if(!depart){
                System.out.println("No events occurring");
            }

            System.out.println("\nFinal Departures:");
            for(int j = 0; j < 20; j++){
                if(flightTerminal[j] != null && flightTerminal[j].getMinutesLeftDeparting() <= 0){
                    System.out.println("Flight to "+ flightTerminal[j].getDestination() + " has departed");
                    System.out.format("%25s%15s%15s\n","Seat Type", "ID", "Arrival Time");
                    flightTerminal[j].toString();
                    flightTerminal[j] = null;//so to clear this airport, we need to clear everything else basically
                    findepart = true;
                }

            }
            if(!findepart){
                System.out.println("No events occurring");
            }
            System.out.println("\n\n\n");
            findepart = false;
            depart = false;
            currboarding = false;
        }
    }
}
