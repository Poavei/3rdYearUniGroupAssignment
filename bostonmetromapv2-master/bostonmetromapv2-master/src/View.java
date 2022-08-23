import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View implements ActionListener {
    private final GUI gui;
    private final JButton clearButton;
    private final JButton exitButton;
    private final JButton searchButton;
    private final JButton mapButton;
    private final JTextField desInput;
    private final JTextField startInput;
    private final JTextArea displayRoute;
    private final Controller controller;

    public View(){
        gui = new GUI();
        this.clearButton = gui.getClearButton();
        this.exitButton = gui.getExitButton();
        this.searchButton = gui.getSearchButton();
        this.mapButton = gui.getShowMapButton();
        this.desInput = gui.getDestination();
        this.startInput = gui.getStart();
        this.displayRoute = gui.getDisplayRoute();
        clearButton.addActionListener(this);
        searchButton.addActionListener(this);
        exitButton.addActionListener(this);
        mapButton.addActionListener(this);
        this.controller = Controller.getInstance();
    }
    public void returnRouteString(String route){
        this.displayRoute.setText(route);
    }

    public String getDest(){
        return this.desInput.getText();
    }
    public String getCurr(){
        return this.startInput.getText();
    }

    public void noStationError(){
        this.displayRoute.setText("No matching stations for one or both entries, please try again.");
    }
    public void clearGui(){
        this.displayRoute.setText("");
        this.desInput.setText("");
        this.startInput.setText("");
        System.out.println("Clear button pressed.");
    }
    public void search(){
        controller.getRoute();
        System.out.println("Search button pressed.");
    }
    public void exit(){
        System.out.println("Exit button pressed.");
        System.exit(0);
    }
    public void showMap(){
        gui.showMap();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.clearButton){
            clearGui();
        }
        if(e.getSource() == this.searchButton){
            search();
        }
        if(e.getSource() == this.exitButton){
            exit();
        }
        if (e.getSource() == this.mapButton){
            showMap();
        }
    }
}
