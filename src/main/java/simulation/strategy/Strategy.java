package simulation.strategy;

import dataModels.Client;
import dataModels.Server;

import java.util.List;

public interface Strategy {
    public void addClient(List<Server> servers, Client c);
}
