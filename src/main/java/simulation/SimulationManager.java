package simulation;

import dataModels.Client;
import dataModels.SelectionPolicy;
import dataModels.Server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationManager implements Runnable{

    private int timeLimit;
    private int maxProcessingTime;
    private int minProcessingTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int numberOfServers;
    private int numberOfClients;
    private SelectionPolicy selectionPolicy;

    private Scheduler scheduler;
    private List<Client> generatedClients = new ArrayList<Client>();

    File file = new File("eventLog.txt");
    FileWriter writer;

    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime,int maxArrivalTime, int minArrivalTime, int numberOfServers, int numberOfClients, SelectionPolicy selectionPolicy) {
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.selectionPolicy = selectionPolicy;

        this.scheduler = new Scheduler(numberOfServers);
        this.scheduler.changeStrategy(selectionPolicy);

        this.generateNRandomClients();

        try{
            file.createNewFile();
            writer = new FileWriter("eventLog.txt");}
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getNumberOfServers() {
        return numberOfServers;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public List<Client> getGeneratedClients() {
        return generatedClients;
    }

    private void generateNRandomClients(){

        for(int i = 0; i < numberOfClients; i++){
            int arr = (int)(Math.random()*(maxArrivalTime-minArrivalTime) + minProcessingTime); // se genereaza un nr random inte min si max arrival time
            int proc = (int)(Math.random()*(maxProcessingTime-minProcessingTime) + minProcessingTime);
            generatedClients.add(new Client(i, arr, proc));
        }
        Collections.sort(generatedClients);
    }

    @Override
    public void run() {
        int currentTime = 0, maxNoClients = 0, avgWaitingTime = 0, avgServiceTime = 0, clientsServed = 0, peekHour = 0; //maxNoClients e numarul maxim de clienti care se afla in magazin pt a calc peek hour
        try{
            while(currentTime < timeLimit){
                writer.write("Time" + currentTime + " \n");
                while(generatedClients.size() != 0 && generatedClients.get(0).getArrivalTime() <= currentTime){
                    scheduler.serveClient(generatedClients.get(0)); // servim clientii care au arrival Time-ul egal cu timpul curent
                    clientsServed++;//se determina clientii serviti, cei care nu au fost serviti nu se iau in considerare la calcularea timpului mediu
                    avgWaitingTime += (generatedClients.get(0).getFinishTime() - generatedClients.get(0).getArrivalTime()); // timpul de asteptare este timpul la care a venit minus timpul la care a plecat
                    avgServiceTime += generatedClients.get(0).getProcessingTime(); // suma timpilor de servire
                    generatedClients.remove(0);
                }
                int noClients = 0, i = 0; //numarul curent de clienti
                for(Server s: scheduler.getServers()){ // afisam serverele si calculam numarul de clienti care se afla in magazin la secunda actuala
                    writer.write("queue "+ i + " " + s.toString() + " waiting period " + s.getWaitingPeriod() + " \n");
                    i++; noClients += s.getClients().size();
                }
                if(noClients > maxNoClients) {maxNoClients = noClients; peekHour = currentTime;} // daca nr curent de clienti e mai mare decat maximul de pana acum actualizam
                currentTime++;
                try {
                     Thread.sleep(1000); // asteptam o secunda
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            writer.write("\npeek hour " + peekHour+"\naverage service time "+ avgServiceTime*1.0/clientsServed+"\naverage waiting time "+ avgWaitingTime*1.0/clientsServed);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
