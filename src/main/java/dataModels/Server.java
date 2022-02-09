package dataModels;

import dataModels.Client;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;

    public Server() {                               // Constructor
        clients = new LinkedBlockingQueue<Client>();
        waitingPeriod = new AtomicInteger(0);
    }

    public BlockingQueue<Client> getClients() {     //getter coada de clienti
        return clients;
    }

    public AtomicInteger getWaitingPeriod() {   //getter pt waiting period
        return waitingPeriod;
    }

    public void addClient(Client c){ //cand adaugam un client la server trebuie si sa incrementam waiting period-ul cu timpul lui de procesare
        c.setFinishTime(waitingPeriod.get()+c.getArrivalTime()+c.getProcessingTime());
        clients.add(c);
        waitingPeriod.addAndGet(c.getProcessingTime());
    }

    @Override
    public String toString() {
        String s ="";
        for(Client c: clients){
            s=s+c.toString()+" ";
        }
        s = s + '\n';
        return s;
    }

    @Override
    public void run() {             // implementare run pentru interfata

        while(true){
            while(!clients.isEmpty()) {
                // se ia primul client de la coada
                try {
                    while(clients.peek().getProcessingTime()!=0){

                        Thread.sleep(1000); // se asteapta timpul de procesare pe care il necesita clientul
                        clients.peek().decrementProcessingTime(); // se decrementeaza timpul de asteptare secunda cu secunda
                        waitingPeriod.decrementAndGet(); // se decrementeaza timpul de asteptare de la coada

                    }
                    clients.take();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
