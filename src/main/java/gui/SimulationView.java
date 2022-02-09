package gui;

import dataModels.Server;
import simulation.SimulationManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SimulationView extends SwingWorker<Void, List<Server>>{

    SimulationManager simulationManager;
    private final JFrame frame = new JFrame("Queue simulator");
    private final JPanel content = new JPanel();
    private final List<JTextField> q = new ArrayList<JTextField>();
    private final JTextField clients = new JTextField();

    public SimulationView(SimulationManager simulationManager) {
        this.simulationManager=simulationManager;

        frame.setSize(new Dimension(600,800));

        content.setLayout( new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(new Color(255,200,200));
        content.add(new JLabel("Clients"));
        content.add(clients);
        for(int i = 0; i< simulationManager.getNumberOfServers(); i++){
            String s = "Queue no. " + i;
            content.add(new JLabel(s));
            q.add(new JTextField());
            q.get(i).setSize(600, 100);
            content.add(q.get(i));
        }
        clients.setText(simulationManager.getGeneratedClients().toString());
        JScrollPane scrollPane = new JScrollPane(content ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.setContentPane(scrollPane);
        frame.setVisible(true);

        Thread thread = new Thread(this.simulationManager);
        thread.start();
    }

    public void setText(int i, String s){
        q.get(i).setText(s);
    } //seteaza textul unui textfield sin arraylist

    @Override
    protected Void doInBackground() throws Exception { // taskul rulat in Background
        for(int i = 0; i< simulationManager.getTimeLimit(); i++) { // din secunda in secunda pana se termina limita de timp a simularii
            publish(simulationManager.getScheduler().getServers()); //"publicam" se trimit la metoda process serverele updatate de threadul de simulation manager
            Thread.sleep(1000);
        }
        return null;
    }

    @Override
    protected void process(List<List<Server>> servers){
        for(int i =0 ; i<q.size();i++){
            this.setText(i,servers.get(servers.size()-1).get(i).toString()); // se seteaza fiecare camp cu ultima valoare publicata
        }

    }

}
