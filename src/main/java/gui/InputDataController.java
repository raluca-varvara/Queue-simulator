package gui;

import simulation.SimulationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputDataController {
    private InputDataView view;

    public InputDataController(InputDataView view) {
        this.view = view;

        view.addStartSimulationListener(new StartSimulationListener());
    }
    class StartSimulationListener implements ActionListener { // exista un singur action listener pentru butonul care incepe simularea
        @Override
        public void actionPerformed(ActionEvent e) {
            try { // se preiau datele si se comunica la SimulationManager
                SimulationManager s = new SimulationManager(view.getTSimulation(), view.getMaxService(), view.getMinService(), view.getMaxArrival(), view.getMinArrival(), view.getNbOfQueues(), view.getNbOfClients(), view.getSelectionPolicy());
                SimulationView simulationView = new SimulationView(s); // se creaza un view pt simulare care are ca si variabila SimulationManager
                simulationView.execute();
            }catch(NumberFormatException er){ // daca nu se intro datele corect se deschide un popup
                view.showError("date gresite, trebuie numere");
            }
        }
    }
}
