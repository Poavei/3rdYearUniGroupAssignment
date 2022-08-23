import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class GUI extends JFrame /*implements ActionListener*/{

    private Map<Class, Color> colors;
    private Node node;
    // A boolean to hold the object Running
    private boolean Running;
    // A JButton to START the simulation
    private JButton searchButton;
    // A JButton to RESTART the simulation
    private JButton clearButton;
    // A JButton to QUIT the simulation
    private JButton exitButton;

    private JButton showMapButton;
    private JButton findRoute;

    private JLabel main;

    private JLabel display;
    private JTextArea displayRoute;

    private JLabel start;
    private JTextField startInput;

    private JLabel destination;
    private JTextField desInput;



    public GUI() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Sets Running to false
        this.Running = false;

        setTitle("Boston Metro System");
        setLocation(100, 50);

        // Adds all the buttons created to ActionListener method
        // Calls my Search button "Search"
        this.searchButton = new JButton("Search");

        // Calls my RESTART button "Restart"
        this.clearButton = new JButton("Clear");
        clearButton.setBounds(130, 220, 110, 30);

        this.showMapButton = new JButton("Show Map");

        this.start = new JLabel("Enter Starting Point: ");
        this.startInput = new JTextField(30);
        start.setBounds(110, 90, 130, 30);
        startInput.setBounds(250, 90, 140, 30);

        startInput.setForeground(Color.BLACK);

        this.destination = new JLabel("Enter Destination: ");
        this.desInput = new JTextField(30);
        destination.setBounds(110, 150, 130, 30);
        desInput.setBounds(250, 150, 140, 30);

        this.display = new JLabel("Display Route: ");
        this.displayRoute = new JTextArea();
        display.setBounds(110, 210, 130, 30);
        displayRoute.setBounds(250, 210, 140, 700);

        desInput.setForeground(Color.BLACK);

        // Calls my EXIT button "Exit"
        this.exitButton = new JButton("Exit");
        //this.exitButton.addActionListener(this);
        exitButton.setBounds(190, 450, 120, 30);

        // Creates a separate panel for my buttons
        JPanel p1 = new JPanel(new GridLayout(2, 1));
        p1.add(this.clearButton);
        p1.add(this.exitButton);

        JPanel p2 = new JPanel(new GridLayout(7,2));
        p2.add(this.start);
        p2.add(this.startInput);
        p2.add(this.destination);
        p2.add(this.desInput);
        p2.add(this.searchButton);
        p2.add(this.showMapButton);
        p2.add(this.display);

        JPanel p3 = new JPanel(new GridLayout(1,40));
        p3.add(this.displayRoute);
        JScrollPane scroll = new JScrollPane(p3);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setVisible(true);
        scroll.setPreferredSize(new Dimension(250,250));


        Container contents = getContentPane();
        contents.add(p2, BorderLayout.NORTH);
        contents.add(p1, BorderLayout.SOUTH);
        contents.add(scroll, BorderLayout.CENTER);
        pack();
        setVisible(true);
        initialise();

    }

    private void initialise()
    {
        startInput.setText("");
        desInput.setText("");
    }

    public void showMap(){
        JFrame frame = new JFrame("Metro Map");
        frame.setSize(600, 800);
        frame.setVisible(true);

        frame.getContentPane().setLayout(new FlowLayout());

        ImageIcon icon = new ImageIcon("bostonmetromapv2-master\\src\\map.jpg");
        JLabel label = new JLabel(icon);
        JScrollPane scrollableImage = new JScrollPane(label);

        scrollableImage.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableImage.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableImage.setVisible(true);
        scrollableImage.setPreferredSize(new Dimension(450,450));


        frame.getContentPane().add(scrollableImage);
        //JOptionPane op = new JOptionPane();
        //op.showMessageDialog(null, "Boston Metro Map", "", JOptionPane.INFORMATION_MESSAGE,icon);

        JButton back = new JButton("Back");
        back.setBounds(500, 300, 150, 30);
        back.addActionListener(e -> {
            frame.dispose();

        });
        frame.add(back);
    }




    public void actionPerformed(ActionEvent e) {
        /*if (e.getSource() == this.searchButton) {
            //this.Running = true;
            if(startInput.getText().isEmpty() || desInput.getText().isEmpty())
                JOptionPane.showMessageDialog(fieldView, "Please enter info");
            else {
                String startPoint = (startInput.getText()).toUpperCase();
                String endPoint = (desInput.getText()).toUpperCase();
                displayRoute.setText(Controller.handleInput(startPoint, endPoint));
                //JOptionPane.showMessageDialog(frame, "sent!");
            }


        }
        else if (e.getSource() == this.clearButton)
        {
            this.Running = false;
            initialise();
        }
        else if (e.getSource() == this.exitButton)
        {
            System.exit(0);
        }

        else if(e.getSource() == this.showMapButton){
            showMap();
        }

         */
    }
    public JButton getSearchButton(){
        return this.searchButton;
    }
    public JButton getFindRouteButton(){
        return this.findRoute;
    }
    public JButton getClearButton(){
        return this.clearButton;
    }
    public JButton getExitButton(){
        return this.exitButton;
    }
    public JButton getShowMapButton(){
        return this.showMapButton;
    }
    public JTextField getDestination(){
        return this.desInput;
    }
    public JTextField getStart(){
        return this.startInput;
    }
    public JTextArea getDisplayRoute(){
        return this.displayRoute;
    }

}

