package simulation.strategy;

import dataModels.Client;
import dataModels.Server;


import java.util.List;

public class ShortestTimeStrategy implements Strategy {
    @Override
    public void addClient(List<Server> servers, Client c) {
        int minWaitinPeriod = Integer.MAX_VALUE;
        for(Server s: servers){
            if(s.getWaitingPeriod().get()< minWaitinPeriod)
                minWaitinPeriod = s.getWaitingPeriod().get(); //se afla care este cel mai mic timp de asteptare
        }
        for(Server s: servers){
            if(s.getWaitingPeriod().get() == minWaitinPeriod){ // cand se gaseste prima lista care are cel mai mic timp de asteptare se adauga clientul
                s.addClient(c);
                break;
            }
        }
    }
}
