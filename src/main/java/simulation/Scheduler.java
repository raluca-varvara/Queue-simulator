package simulation;

import dataModels.Client;
import dataModels.SelectionPolicy;
import dataModels.Server;
import simulation.strategy.ShortestQueueStrategy;
import simulation.strategy.ShortestTimeStrategy;
import simulation.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private List<Server> servers = new ArrayList<Server>();
    private int maxNoServers;
    private Strategy strategy;

    public Scheduler(int maxNoServers) {
        this.maxNoServers = maxNoServers;

        for(int i =0; i<maxNoServers; i++){
            servers.add(new Server());
            Thread thread = new Thread(servers.get(i));// se creaza thread cu obiectul/ serverul
            thread.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy){ // se schimba strategia in fct de selection policy
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ShortestQueueStrategy();
        }
        else{
            strategy = new ShortestTimeStrategy();
        }
    }

    public void serveClient(Client c){ // in functie de ce strategie am ales servim clientul (il adaugam la o coada)
        strategy.addClient(servers,c);
    }

    public List<Server> getServers() {
        return servers;
    }
}
