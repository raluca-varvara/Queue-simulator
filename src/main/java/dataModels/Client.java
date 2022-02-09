package dataModels;

public class Client implements Comparable{

    private int id;
    private int arrivalTime;
    private int processingTime;
    private int finishTime;

    public Client(int id, int arrivalTime, int processingTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void decrementProcessingTime(){ processingTime = processingTime - 1;}

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finalTime) {
        this.finishTime = finalTime;
    }

    @Override
    public int compareTo(Object o) {
        return this.arrivalTime-((Client) o).arrivalTime;
    } //am implementat comparable pt a putea sorta lista de clienti generati

    @Override
    public String toString() {
        return "Client" + id + "[ " + arrivalTime + ", " + processingTime + ']';
    }
}
