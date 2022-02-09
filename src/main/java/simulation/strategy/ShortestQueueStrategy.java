package simulation.strategy;

import dataModels.Client;
import dataModels.Server;

import java.util.List;

public class ShortestQueueStrategy implements Strategy {
    @Override
    public void addClient(List<Server> servers, Client c) {
        int nrClients = Integer.MAX_VALUE;
        for(Server s: servers){
            if(s.getClients().size()<nrClients)
                nrClients=s.getClients().size(); //se afla care este minimul de clienti la un server
        }
        for(Server s: servers){
            if(s.getClients().size() == nrClients){ // cand se gaseste prima lista care are numar minim de clienti se adauga clientul
                s.addClient(c);
                break;
            }
        }
    }
}
