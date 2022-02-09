import gui.InputDataController;
import gui.InputDataView;

public class Main {
    public static void main(String[] args) {
        InputDataView v = new InputDataView();
        InputDataController c = new InputDataController(v);
        //gui.SimulationView v = new gui.SimulationView(30);
        /*simulation.SimulationManager s = new simulation.SimulationManager(20, 8, 2,5, 1, 2, 6, dataModels.SelectionPolicy.SHORTEST_TIME);
        Thread thread = new Thread(s);
        thread.start();*/
        //System.out.println(s.generatedClients.toString());
    }
}
