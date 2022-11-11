import java.util.Arrays;

public class BoardingQueue {
    private Passenger[] passengerQueue = new Passenger[10];
    private int front;
    private int rear;
    private int size;
    private int amountinqueue;
    private int amountonboard;

    public BoardingQueue(){
        front = -1;
        rear = -1;
        size = 1;
        amountinqueue = 0;
        amountonboard = 0;
    }

    public BoardingQueue(int front, int rear, int size, int amountinqueue){
        this.front = front;
        this.rear = rear;
        this.size = size;
        this.amountinqueue = amountinqueue;
    }

    public void enqueuePassenger(Passenger newPass)throws FullQueueException{
        if ((rear+1)%10 == front) {
            throw new FullQueueException();
        }
        if (front == -1) { // isEmpty()
            front = 0; rear = 0;
        }
        else {
            rear = (rear + 1) % 10;
        }
        amountinqueue += 1;
        passengerQueue[rear] = newPass;
    }
    public Passenger LastPersonInQueue(){
        Passenger answer;
        answer = passengerQueue[rear];
        return answer;
    }

    public Passenger dequeuePassenger() throws EmptyQueueException {
        Passenger answer;
        if (front == -1) {// isEmpty()
            System.out.println("There is noone to take out of the queue");
            throw new EmptyQueueException();
        }
        answer = passengerQueue[front];
        amountonboard += 1;
        amountinqueue -= 1;
        if (front == rear) {//if there was only 1 thing in the queue
            front = -1; rear = -1;
        }
        else{
            front = (front+1)%10;
        }

        return answer;
    }

    public int getAmountonboard() {
        return amountonboard;
    }

    public void setAmountonboard(int amountonboard) {
        this.amountonboard = amountonboard;
    }

    public int getAmountinqueue() {
        return amountinqueue;
    }

    public void setAmountinqueue(int amountinqueue) {
        this.amountinqueue = amountinqueue;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public int getSize() {
        return size;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public void setPassengerQueue(Passenger[] passengerQueue) {
        this.passengerQueue = passengerQueue;
    }

    public Passenger[] getPassengerQueue() {
        return passengerQueue;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public class FullQueueException extends Exception {
    }

    public class EmptyQueueException extends Exception {
    }


}
