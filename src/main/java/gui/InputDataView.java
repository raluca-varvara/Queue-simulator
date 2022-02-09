package gui;

import dataModels.SelectionPolicy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputDataView {
    private final JFrame frame = new JFrame("Queue simulator");
    private final JPanel content = new JPanel();
    private final JPanel contentData = new JPanel();
    private final JLabel lN = new JLabel("No. clients = ");
    private final JLabel lQ = new JLabel("No. queues = ");
    private final JLabel lTSimulation = new JLabel("Simulation time = ");
    private final JLabel lMinArrival = new JLabel("Minimum arrival time = ");
    private final JLabel lMaxArrival = new JLabel("Maximum arrival time = ");
    private final JLabel lMinService = new JLabel("Minimum wait time = ");
    private final JLabel lMaxService = new JLabel("Maximum wait time = ");
    private final JLabel lSelection = new JLabel("Selection Policy = ");
    private final JComboBox comboBox= new JComboBox();
    private final JTextField tN = new JTextField();
    private final JTextField tQ = new JTextField();
    private final JTextField tTSimulation = new JTextField();
    private final JTextField tMinArrival = new JTextField();
    private final JTextField tMaxArrival = new JTextField();
    private final JTextField tMinService = new JTextField();
    private final JTextField tMaxService = new JTextField();
    private final JButton startSimulation = new JButton("START SIMULATION");

    public InputDataView() {
        frame.setSize(new Dimension(600,300));

        content.setLayout( new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(new Color(255,200,200));
        contentData.setLayout( new GridLayout(4, 4));
        contentData.setBackground(new Color(255,200,200));

        comboBox.setModel(new DefaultComboBoxModel(SelectionPolicy.values()));

        contentData.add(lN);
        contentData.add(tN);
        contentData.add(lQ);
        contentData.add(tQ);
        contentData.add(lTSimulation);
        contentData.add(tTSimulation);
        contentData.add(lSelection);
        contentData.add(comboBox);
        contentData.add(lMinArrival);
        contentData.add(tMinArrival);
        contentData.add(lMaxArrival);
        contentData.add(tMaxArrival);
        contentData.add(lMinService);
        contentData.add(tMinService);
        contentData.add(lMaxService);
        contentData.add(tMaxService);

        content.add(contentData);
        content.add(startSimulation);

        frame.setContentPane(content);
        frame.setVisible(true);
    }

    public int getNbOfClients(){
        int i = 0;
        i = Integer.parseInt(tN.getText());
        return i;
    }

    public int getNbOfQueues(){
        int i = 0;
        i = Integer.parseInt(tQ.getText());
        return i;
    }

    public int getTSimulation(){
        int i = 0;
        i = Integer.parseInt(tTSimulation.getText());
        return i;
    }

    SelectionPolicy getSelectionPolicy(){
        return (SelectionPolicy)comboBox.getSelectedItem();
    }

    public int getMaxArrival(){
        int i = 0;
        i = Integer.parseInt(tMaxArrival.getText());
        return i;
    }

    public int getMinArrival(){
        int i = 0;
        i = Integer.parseInt(tMinArrival.getText());

        return i;
    }

    public int getMaxService(){
        int i = 0;
        i = Integer.parseInt(tMaxService.getText());
        return i;
    }

    public int getMinService(){
        int i = 0;
        i = Integer.parseInt(tMinService.getText());
        return i;
    }

    void addStartSimulationListener(ActionListener ac) {
        startSimulation.addActionListener(ac);
    }

    void showError(String errMessage) {
        JOptionPane.showMessageDialog(frame, errMessage);
    } // adauga un pop up cu eroare
}
