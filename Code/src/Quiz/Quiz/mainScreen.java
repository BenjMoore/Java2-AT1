package Quiz.Quiz;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class mainScreen extends JFrame implements ActionListener {
    // declare buttons and layout
    SpringLayout myLayout = new SpringLayout();
    JButton btnExit, btnSave, btnOpen, btnBinary, btnClear, btnSort, btnRAF;
    JTextField[][] textFields = new JTextField[15][9];
    JTextField txtSearch;
    JTextField txtTeacher, txtClass, txtRoom, txtDate;
    ArrayList<Object[]> al = new ArrayList();
    MyModel quizModel;
    JTable table;


    public mainScreen()
    {
        //initialise main screen
        setSize(700, 500);
        setLocation(450, 200);
        AddWindowListenerToForm();
        setLayout(myLayout);
        SetupButtons();
        SetupTextfields();
        setupTable();
        setVisible(true);
        System.out.println("Initial size of al: " + al.size());
    }
    public void setupTable()
    {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        add(topPanel);

        // Create column names
        String columnNames[] =
                { "Question #", "Topic", "Subtopic"};

        // Create some data
       // ArrayList<Object[]> al = new ArrayList();
        al.add(new Object[] {"Yes","No","-"});
        al.add(new Object[] {"Hi","there","-"});
        al.add(new Object[] {"True","False","-"});
        al.add(new Object[] {"Cat","Dog","-"});

        // constructor of JTable model
        quizModel = new MyModel(al, columnNames);

        // Create a new table instance
        table = new JTable(quizModel);

        // Configure some of JTable's paramters
        table.isForegroundSet();
        table.setShowHorizontalLines(false);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        add(table);

        // Change the text and background colours
        table.setSelectionForeground(Color.white);
        table.setSelectionBackground(Color.red);

        // Add the table to a scrolling pane, size and locate
        JScrollPane scrollPane = table.createScrollPaneForTable(table);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        topPanel.setPreferredSize(new Dimension(400, 120));
        myLayout.putConstraint(SpringLayout.WEST, topPanel, 170, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH, topPanel, 40, SpringLayout.NORTH, this);

    }

    //---------------------------------------------------------------------------------------------------
    // Source: http://www.dreamincode.net/forums/topic/231112-from-basic-jtable-to-a-jtable-with-a-tablemodel/
    // class that extends the AbstractTableModel
    //---------------------------------------------------------------------------------------------------

    class MyModel extends AbstractTableModel
    {
        ArrayList<Object[]> al;

        // the headers
        String[] header;

        // to hold the column index for the Sent column
        int col;

        // constructor
        MyModel(ArrayList<Object[]> obj, String[] header)
        {
            // save the header
            this.header = header;
            // and the data
            al = obj;
            // get the column index for the Sent column
            col = this.findColumn("Sent");
        }

        // method that needs to be overload. The row count is the size of the ArrayList

        public int getRowCount()
        {
            return al.size();
        }

        // method that needs to be overload. The column count is the size of our header
        public int getColumnCount()
        {
            return header.length;
        }

        // method that needs to be overload. The object is in the arrayList at rowIndex
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            return al.get(rowIndex)[columnIndex];
        }

        // a method to return the column name
        public String getColumnName(int index)
        {
            return header[index];
        }

        public Class getColumnClass(int columnIndex)
        {
            if (columnIndex == col)
            {
                return Boolean.class; // For every cell in column 7, set its class to Boolean.class
            }
            return super.getColumnClass(columnIndex); // Otherwise, set it to the default class
        }

        // a method to add a new line to the table
        void add(String qnno, String topic, String subtopic)
        {
            // make it an array[3] as this is the way it is stored in the ArrayList
            // (not best design but we want simplicity)
            Object[] item = new Object[3];
            item[0] = qnno;
            item[1] = topic;
            item[2] = subtopic;
            al.add(item);
            // inform the GUI that I have change
            fireTableDataChanged();
        }
    }




    // Read Code



    private void SetupButtons()
    {
        // set up all buttons
        btnExit = UIComponentLibrary.CreateJButton("Exit", 80, 25, 250, 350, this, this, myLayout);
        btnOpen = UIComponentLibrary.CreateJButton("Open", 80, 25, 150, 350, this, this, myLayout);
        btnSave = UIComponentLibrary.CreateJButton("Save", 80, 25, 50, 350, this, this, myLayout);
        btnBinary = UIComponentLibrary.CreateJButton("Search", 120, 25, 25, 400, this, this, myLayout);
        btnClear = UIComponentLibrary.CreateJButton("Clear", 80, 25, 350, 350, this, this, myLayout);
        btnSort = UIComponentLibrary.CreateJButton("Sort", 80, 25, 450, 350, this, this, myLayout);
        btnRAF = UIComponentLibrary.CreateJButton("RAF", 80, 25, 550, 350, this, this, myLayout);


    }

    private void SetupTextfields()
    {
        // set up all text fields
        txtSearch = UIComponentLibrary.CreateAJTextField(10, 150, 405, this, myLayout);
        txtTeacher = UIComponentLibrary.CreateAJTextField(10, 50, 10, this, myLayout);
        txtClass = UIComponentLibrary.CreateAJTextField(10, 200, 10, this, myLayout);
        txtRoom = UIComponentLibrary.CreateAJTextField(10, 350, 10, this, myLayout);
        txtDate = UIComponentLibrary.CreateAJTextField(10, 500, 10, this, myLayout);
        txtTeacher.setOpaque(true);
        txtClass.setOpaque(true);
        txtRoom.setOpaque(true);
        txtDate.setOpaque(true);
        txtTeacher.setBackground(Color.CYAN);
        txtClass.setBackground(Color.CYAN);
        txtRoom.setBackground(Color.CYAN);
        txtDate.setBackground(Color.CYAN);
        txtTeacher.setText("Teacher");
        txtClass.setText("Class");
        txtRoom.setText("Room");
        txtDate.setText("Date");

        //Iterates through 2D textfield array to generate and build individual textfields
        //Y values indicate Y-Axis of array values X indicate X-Axis eg.array[y][x]

    }

    private void AddWindowListenerToForm() // action listner add
    {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) // action listner class
    {
        if (actionEvent.getSource() == btnExit) //  exit button
        {
            System.exit(0); // exit
        }

    }



}

// loop though text fields
// if












