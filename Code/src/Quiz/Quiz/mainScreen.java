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
    // Declare Buttons
    JButton btnExit, btnQuestionNo, btnTopic, btnSubtopic
            , btnClear, btnSort, btnConnect
            ,btnPreorder,btnPostorder,btnInorder
            ,btnDisplay,btnSave;
    // Declare Lables
    JLabel lblPolicyTitle, txtSearch,txtSort
            ,txtLinkedList,txtBinary,txtTitle
            ,txtPreorder,txtPostorder,txtInorder;

    // Declare Text Fields
    JTextField searchBox, txtClass, txtRoom, txtDate;

    // Declare Text Area
    JTextArea LinkedList, BinarySearchtxt;

    ArrayList<Object[]> al = new ArrayList();
    MyModel quizModel;
    JTable table;
    String Version = "V0.1";


    public mainScreen()
    {
        //initialise main screen
        setSize(800, 700);
        setLocation(0, 0);
        AddWindowListenerToForm();
        setLayout(myLayout);
        setResizable(false);


        setTitle("Perfect Policys Quiz | Version: 1.0 |");
        // Setup UI
        SetupButtons();
        SetupTextfields();
        setupTable();
        SetupJlabels();
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


        al.add(new Object[] {"1","Test","-"});
        al.add(new Object[] {"2","Test","-"});
        al.add(new Object[] {"3","Test","-"});
        al.add(new Object[] {"4","Test","-"});
        al.add(new Object[] {"5","Test","-"});
        al.add(new Object[] {"6","Test","-"});
        al.add(new Object[] {"7","Test","-"});
        al.add(new Object[] {"8","Test","-"});

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
        topPanel.setPreferredSize(new Dimension(400, 150));
        myLayout.putConstraint(SpringLayout.WEST, topPanel, 10, SpringLayout.WEST, this);
        myLayout.putConstraint(SpringLayout.NORTH, topPanel, 150, SpringLayout.NORTH, this);

    }

    // Bubble Sort
    public static void bubbleSort(ArrayList<Object[]> arr)
    {

        for(int j=0; j<arr.size(); j++)
        {
            for(int i=j+1; i<arr.size(); i++)
            {
                if((arr.get(i)[0]).toString().compareToIgnoreCase(arr.get(j)[0].toString())<0)
                {
                    Object[] words = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, words);
                }
            }
            System.out.println(arr.get(j)[0] + " - " + arr.get(j)[1]);
        }
    }

    // Create Jtable Model
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




    // Setup Buttons
    private void SetupButtons()
    {
        // set up all buttons
        btnQuestionNo = UIComponentLibrary.CreateJButton("Q #", 80, 20, 280, 310, this, this, myLayout);
        btnSubtopic = UIComponentLibrary.CreateJButton("Subtopic", 90, 20, 170, 310, this, this, myLayout);
        btnTopic = UIComponentLibrary.CreateJButton("Topic", 80, 20, 70, 310, this, this, myLayout);
        btnExit = UIComponentLibrary.CreateJButton("Exit",100,20,100,340,this, this, myLayout);
        btnConnect = UIComponentLibrary.CreateJButton("Connect",100,20,200,340,this, this, myLayout);
        btnInorder = UIComponentLibrary.CreateJButton("Display",100,20,10,600,this, this, myLayout);
        btnPreorder = UIComponentLibrary.CreateJButton("Display",100,20,210,600,this, this, myLayout);
        btnPostorder = UIComponentLibrary.CreateJButton("Display",100,20,410,600,this, this, myLayout);
        btnDisplay = UIComponentLibrary.CreateJButton("Display",100,20,675,580,this, this, myLayout);
        btnSave = UIComponentLibrary.CreateJButton("Save",100,20,675,620,this, this, myLayout);



    }
    // Setup Lables
    private void SetupJlabels()
    {
        lblPolicyTitle = UIComponentLibrary.CreateAJLabel("                                                    Policy Questions                                                       ", 0, 125, this, myLayout);
        lblPolicyTitle.setOpaque(true);
        lblPolicyTitle.setBackground(Color.cyan);

        txtSearch = UIComponentLibrary.CreateAJLabel("Search:", 10,75,this, myLayout);
        txtSort = UIComponentLibrary.CreateAJLabel("Sort By:", 10,310,this, myLayout);

        txtLinkedList = UIComponentLibrary.CreateAJLabel("Linked List:                                                                                                                                                                                                                                            ", 10,370,this, myLayout);
        txtLinkedList.setOpaque(true);
        txtLinkedList.setBackground(Color.cyan);

        txtBinary = UIComponentLibrary.CreateAJLabel("Binary Tree:                                                                                                                                                                                                                                           ", 10,470,this, myLayout);
        txtBinary.setOpaque(true);
        txtBinary.setBackground(Color.cyan);

        txtTitle = UIComponentLibrary.CreateAJLabel("                                                                  Perfect Policy                                                                                           ",0,10,this,myLayout);
        txtTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        txtTitle.setOpaque(true);
        txtTitle.setBackground(Color.cyan);

        txtInorder = UIComponentLibrary.CreateAJLabel("In Order", 35,580,this, myLayout);
        txtInorder.setOpaque(true);
        txtInorder.setBackground(Color.cyan);

        txtPreorder = UIComponentLibrary.CreateAJLabel("Pre Order", 232,580,this, myLayout);
        txtPreorder.setOpaque(true);
        txtPreorder.setBackground(Color.cyan);

        txtPostorder = UIComponentLibrary.CreateAJLabel("Post Order", 430,580,this, myLayout);
        txtPostorder.setOpaque(true);
        txtPostorder.setBackground(Color.cyan);


    }

    // Setup Text Fields
    private void SetupTextfields()
    {
        searchBox = UIComponentLibrary.CreateAJTextField(20,60,75,this,myLayout);

        // Linked List
        LinkedList = UIComponentLibrary.CreateAJTextArea(5,70,10,390,this, myLayout);
        LinkedList.setBorder(BorderFactory.createLineBorder(Color.black));

        // Binary Search
        BinarySearchtxt = UIComponentLibrary.CreateAJTextArea(5,70,10,490,this, myLayout);
        BinarySearchtxt.setBorder(BorderFactory.createLineBorder(Color.black));

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


    // Action Listener
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












