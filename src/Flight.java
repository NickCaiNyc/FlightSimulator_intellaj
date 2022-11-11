import java.util.Arrays;

public class Flight
{
    private BoardingQueue boardingQueue;

    private String destination;
    private Passenger[] firstClass = new Passenger[2];
    private Passenger[] businessClass = new Passenger[3];
    private Passenger[] premiumEconomy = new Passenger[4];
    private Passenger[] economy = new Passenger[6];
    private int minutesLeftBoarding;
    private int minutesLeftDeparting;
    private int firstClassInd;
    private int businessClassInd;
    private int premiumEconomyInd;
    private int economyInd;
    private boolean boarding;
    private boolean airforce1;
    public Flight(){
        destination = "";
        minutesLeftBoarding = 0;
        minutesLeftDeparting = 0;
        firstClassInd = 0;
        businessClassInd = 0;
        premiumEconomyInd = 0;
        economyInd = 0;
        boarding = true;
        airforce1 = false;
    }
    public Flight (int minutesLeftBoarding, int minutesLeftDeparting, int firstClassInd, int businessClassInd, int premiumEconomyInd, int economyInd, boolean boarding,BoardingQueue boardingQueue, boolean airforce1, String destination){
        this.minutesLeftDeparting = minutesLeftDeparting;
        this.minutesLeftBoarding = minutesLeftBoarding;
        this.firstClassInd = firstClassInd;
        this.businessClassInd = businessClassInd;
        this.premiumEconomyInd = premiumEconomyInd;
        this.economyInd = economyInd;
        this.boarding = boarding;
        this.boardingQueue = boardingQueue;
        this.airforce1 = airforce1;
        this.destination = destination;
    }
//In add flight i made it only to check one level below as it doens't make sense to degrade a first class to economy.
    public void addToFlight(Passenger boardedPass){

        switch (boardedPass.getPassClass()){
            case First_Class -> {
                boolean trigger = false;
                boolean trigger2 = false;
                for(int i = 0; i < firstClass.length; i++){

                    if(firstClass[i] == null){
                        firstClass[i] = boardedPass;
                        trigger = true;
                        break;
                    }
                }
                if(!trigger) {
                    System.out.println("There is no more room in first class");
                }
                if(!trigger){
                    for(int i = 0; i < businessClass.length; i++){
                        if(businessClass[i] == null){
                            businessClass[i] = boardedPass;
                            trigger = true;
                            break;
                        }
                    }
                    for(int i = 0; i < premiumEconomy.length; i++){
                        if(premiumEconomy[i] == null){
                            premiumEconomy[i] = boardedPass;
                            trigger2 = true;
                            break;
                        }
                    }
                    for(int i = 0; i < economy.length; i++){
                        if(economy[i] == null){
                            trigger2 = true;
                            economy[i] = boardedPass;
                            break;
                        }
                    }

                }
                if(!trigger2){
                    System.out.println("No room on other flights as well");
                }
            }
            case Business_Class ->{
                boolean trigger = false;
                boolean trigger2 = false;
                for(int i = 0; i < businessClass.length; i++){
                    if(businessClass[i] == null){
                        businessClass[i] = boardedPass;
                        trigger = true;
                        break;
                    }
                }if(!trigger) {
                    System.out.println("There is no more room in Business Class");
                }
                if(!trigger){

                    for(int i = 0; i < economy.length; i++){
                        if(economy[i] == null){
                            trigger2 = true;
                            economy[i] = boardedPass;
                            break;
                        }
                    }
                    for(int i = 0; i < premiumEconomy.length; i++){
                        if(premiumEconomy[i] == null){
                            premiumEconomy[i] = boardedPass;
                            trigger = true;
                            break;
                        }
                    }
                }
                if(!trigger2){
                    System.out.println("No room on other flights as well");
                }
            }
            case Premium_Economy_CLass ->{
                boolean trigger = false;
                boolean trigger2 = false;
                for(int i = 0; i < premiumEconomy.length; i++){
                    if(premiumEconomy[i] == null){
                        premiumEconomy[i] = boardedPass;
                        trigger = true;
                        break;
                    }
                }
                if(!trigger) {
                    System.out.println("There is no more room in Premium Econommy Class");
                }
                if(!trigger){
                    for(int i = 0; i < economy.length; i++){
                        if(economy[i] == null){
                            trigger2 = true;
                            economy[i] = boardedPass;
                            break;
                        }
                    }

                }
                if(!trigger2){
                    System.out.println("No room on other flights as well");
                }
            }
            case Economy_Class ->{
                boolean trigger = false;
                boolean trigger2 = false;
                for(int i = 0; i < economy.length; i++){
                    if(economy[i] == null){
                        trigger = true;
                        economy[i] = boardedPass;
                        break;
                    }
                }
                if(!trigger) {
                    System.out.println("There is no more room in Economy");
                }

            }
            case Covid_Class ->{
                System.out.println("Not going on plane too bad");
            }
        }
    }


    public boolean isAirforce1() {
        return airforce1;
    }

    public void setAirforce1(boolean airforce1) {
        this.airforce1 = airforce1;
    }

    //getter and setter for everything
    public BoardingQueue getBoardingQueue() {
        return boardingQueue;
    }

    public int getBusinessClassInd() {
        return businessClassInd;
    }

    public boolean isBoarding() {
        return boarding;
    }

    public int getEconomyInd() {
        return economyInd;
    }

    public int getFirstClassInd() {
        return firstClassInd;
    }

    public int getMinutesLeftBoarding() {
        return minutesLeftBoarding;
    }

    public int getMinutesLeftDeparting() {
        return minutesLeftDeparting;
    }

    public int getPremiumEconomyInd() {
        return premiumEconomyInd;
    }

    public Passenger[] getBusinessClass() {
        return businessClass;
    }

    public Passenger[] getEconomy() {
        return economy;
    }

    public Passenger[] getFirstClass() {
        return firstClass;
    }

    public Passenger[] getPremiumEconomy() {
        return premiumEconomy;
    }

    public String getDestination() {
        return destination;
    }

    public void setBoarding(boolean boarding) {
        this.boarding = boarding;
    }

    public void setBoardingQueue(BoardingQueue boardingQueue) {
        this.boardingQueue = boardingQueue;
    }

    public void setBusinessClass(Passenger[] businessClass) {
        this.businessClass = businessClass;
    }

    public void setBusinessClassInd(int businessClassInd) {
        this.businessClassInd = businessClassInd;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setEconomy(Passenger[] economy) {
        this.economy = economy;
    }

    public void setEconomyInd(int economyInd) {
        this.economyInd = economyInd;
    }

    public void setFirstClass(Passenger[] firstClass) {
        this.firstClass = firstClass;
    }

    public void setFirstClassInd(int firstClassInd) {
        this.firstClassInd = firstClassInd;
    }

    public void setMinutesLeftBoarding(int minutesLeftBoarding) {
        this.minutesLeftBoarding = minutesLeftBoarding;
    }

    public void setMinutesLeftDeparting(int minutesLeftDeparting) {
        this.minutesLeftDeparting = minutesLeftDeparting;
    }

    public void setPremiumEconomy(Passenger[] premiumEconomy) {
        this.premiumEconomy = premiumEconomy;
    }

    public void setPremiumEconomyInd(int premiumEconomyInd) {
        this.premiumEconomyInd = premiumEconomyInd;
    }

    public String toString(){
        StringBuilder a = new StringBuilder();

        for(int i = 0; i < firstClass.length; i++){
            if(firstClass[i] != null) {
                a.append(System.out.format("%25s%15s%15s\n", firstClass[i].getPassClass(), firstClass[i].getPassengerID(), firstClass[i].getArrivalTime()));
            }
        }
        for(int i = 0; i < businessClass.length; i++){
            if(businessClass[i] != null) {
                a.append(System.out.format("%25s%15s%15s\n", businessClass[i].getPassClass(), businessClass[i].getPassengerID(), businessClass[i].getArrivalTime()));
            }

        }
        for(int i = 0; i < premiumEconomy.length; i++){
            if(premiumEconomy[i] != null) {
                a.append(System.out.format("%25s%15s%15s\n", premiumEconomy[i].getPassClass(), premiumEconomy[i].getPassengerID(), premiumEconomy[i].getArrivalTime()));
            }
        }
        for(int i = 0; i < economy.length; i++){
            if(economy[i] != null) {
                a.append(System.out.format("%25s%15s%15s\n", economy[i].getPassClass(), economy[i].getPassengerID(), economy[i].getArrivalTime()));
            }
        }
        String Stringbuilderconverter = a.toString();
        return Stringbuilderconverter;
    }
}
