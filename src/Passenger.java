public class Passenger
{
    private TravelClass passClass;
    private int passengerID;
    private int arrivalTime;

    public Passenger(){
        passengerID = 0;
        arrivalTime = 0;
        passClass = null;
    }
    public Passenger(int passengerID, int arrivalTime, String passClass){
        this.passengerID = passengerID;
        this.arrivalTime = arrivalTime;
        this.passClass = convertion(passClass);
    }
    private TravelClass convertion(String passClass){
        return switch (passClass) {
            case "f" -> TravelClass.First_Class;
            case "b" -> TravelClass.Business_Class;
            case "p" -> TravelClass.Premium_Economy_CLass;
            case "e" ->//prompt user to state these options
                    TravelClass.Economy_Class;
            case "c" -> TravelClass.Covid_Class;
            default -> TravelClass.Economy_Class;
        };
    }


    public TravelClass getPassClass() {
        return passClass;
    }

    public void setPassClass(TravelClass passClass) {
        this.passClass = passClass;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }
}
