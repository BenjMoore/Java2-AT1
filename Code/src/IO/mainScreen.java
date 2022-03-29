package IO;

import Scripts.BubbleSort;

import Server.ChatServer;
import Scripts.read;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class mainScreen extends JFrame implements ActionListener, MouseListener {

    //CHAT RELATED ---------------------------
    private Socket socket = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;
    private ChatServer.ChatClientThread1 client = null;
    private String serverName = "localhost";
    private int serverPort = 4444;
    DList linkedList = new DList();
    BinaryTree theTree = new BinaryTree();
    public static void main(String[] args) throws IOException {new mainScreen();}

    public void connect(String serverName, int serverPort) throws InterruptedException {
        txtConnectionStatus.setText("Establishing connection. Please wait ...");
        try
        {
            this.socket = new Socket(serverName, serverPort);
            txtConnectionStatus.setText("Server Connection: Connected! {Port ["+serverPort+"] || Server Name: ["+serverName+"] }");
            open();
        }
        catch (UnknownHostException uhe)
        {
            System.out.println("Host unknown: " + uhe.getMessage());
            txtConnectionStatus.setText("Error: Host Unknown!!, Ensure Server Has Started");
            socket.wait(1000);
            txtConnectionStatus.setText("Server Connection: Disconnected");

        }
        catch (IOException ioe)
        {
            System.out.println("Unexpected exception: " + ioe.getMessage());
            txtConnectionStatus.setText("Error: IOException, Ensure Server Has Started");

        }
    }

    private void send()
    {
        try
        {
            //streamOut.writeUTF(txtWord1.getText());
            streamOut.flush();
            //txtWord1.setText("");
        }
        catch (IOException ioe)
        {
            println("Sending error: " + ioe.getMessage());
            close();
        }
    }

    public void handle(String msg)
    {
        if (msg.equals(".bye"))
        {
            println("Good bye. Press EXIT button to exit ...");
            close();
        }
        else
        {
            println(msg);



        }
    }

    public void open()
    {
        try
        {
            streamOut = new DataOutputStream(socket.getOutputStream());
            //client = new ChatClientThread1(this, socket);
        }
        catch (IOException ioe)
        {
            println("Error opening output stream: " + ioe);
        }
    }

    public void close()
    {
        try
        {
            if (streamOut != null)
            {
                streamOut.close();
            }
            if (socket != null)
            {
                socket.close();
            }
        }
        catch (IOException ioe)
        {
            println("Error closing ...");
        }
        client.close();
        client.stop();
    }

    void println(String msg)
    {
        //display.appendText(msg + "\n");
        txtLinkedList.setText(msg);
    }

    public void getParameters()
    {
//        serverName = getParameter("host");
//        serverPort = Integer.parseInt(getParameter("port"));

        serverName = "localhost";
        serverPort = 4444;
    }

    // declare buttons and layout
    SpringLayout myLayout = new SpringLayout();

    // Declare Buttons
    JButton btnExit, btnQuestionNo, btnTopic, btnSubtopic
            ,btnSend, btnSort, btnConnect
            ,btnPreorder,btnPostorder,btnInorder
            ,btnDisplay,btnSave;

    // Declare Labels
    JLabel lblPolicyTitle, txtSearch,txtSort
            ,txtLinkedList,txtBinary,txtTitle
            ,txtPreorder,txtPostorder,txtInorder
            ,txtTopic,txtA,txtQN
            ,txtQnA,txtQnB,txtQnC
            ,txtQnD,txtQnE,txtCorrectAns,txtConnectionStatus
            ,txtSubtopic,txtqnNo;

    // Declare Text Fields
    JTextField searchBox, topicBox, questionBox,
            answerBox,aBox,bBox,
            cBox,dBox,eBox, txtOne,subtopicBox;

    // Declare Text Area
    JTextArea LinkedList, BinarySearchtxt;
    public read readclass = new read();
    ArrayList<Object[]> al = new ArrayList();
    MyModel quizModel;
    JTable table;
    int rowIndex = 0;
    public BubbleSort bubble = new BubbleSort();


    // Initialise Main Screen
    public mainScreen() throws IOException {
        //initialise main screen
        setSize(1000, 700);
        setLocation(0, 0);
        AddWindowListenerToForm();
        setLayout(myLayout);
        setResizable(false);
        //new BubbleSort(al);

        setTitle("Perfect Policys Quiz | Version: 1.0 |");
        // Setup UI
        SetupButtons();
        SetupTextfields();
        setupTable();
        SetupJlabels();
        setVisible(true);
        System.out.println("Initial size of al: " + al.size());

    }

    // JTable

    // Setup JTable
    public void setupTable() throws IOException {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        add(topPanel);


        // Create column names
        String columnNames[] =
                { "Question #", "Topic", "Subtopic"};

        // Create some data
       // ArrayList<Object[]> al = new ArrayList();


        al = read.read();
        // constructor of JTable model
        quizModel = new MyModel(al, columnNames);

        // Create a new table instance
        table = new JTable(quizModel);
        table.addMouseListener(this);
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

    // Sort Algorithms

    // Bubble Sort

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
        btnSend = UIComponentLibrary.CreateJButton("Send",100,20,750,325,this,this,myLayout);


    }
    // Setup Lables
    private void SetupJlabels()
    {
        lblPolicyTitle = UIComponentLibrary.CreateAJLabel("                                              Policy Questions                                                                ", 0, 125, this, myLayout);
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

        txtTitle = UIComponentLibrary.CreateAJLabel("                                                                                          Perfect Policy                                                                                                     ",0,10,this,myLayout);
        txtTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        txtTitle.setOpaque(true);
        txtTitle.setBackground(Color.cyan);

        // Sort Buttons

        txtInorder = UIComponentLibrary.CreateAJLabel("In Order", 35,580,this, myLayout);
        txtInorder.setOpaque(true);
        txtInorder.setBackground(Color.cyan);

        txtPreorder = UIComponentLibrary.CreateAJLabel("Pre Order", 232,580,this, myLayout);
        txtPreorder.setOpaque(true);
        txtPreorder.setBackground(Color.cyan);

        txtPostorder = UIComponentLibrary.CreateAJLabel("Post Order", 430,580,this, myLayout);
        txtPostorder.setOpaque(true);
        txtPostorder.setBackground(Color.cyan);

        // Question Viewer
        txtTopic = UIComponentLibrary.CreateAJLabel("Topic:", 500,60,this, myLayout);
        txtSubtopic = UIComponentLibrary.CreateAJLabel("Subtopic",500,90,this,myLayout);
        txtQN = UIComponentLibrary.CreateAJLabel("Q# :", 500,120,this, myLayout);
        txtA = UIComponentLibrary.CreateAJLabel("Options", 720,150,this, myLayout);
        txtQnA = UIComponentLibrary.CreateAJLabel("A:", 500,180,this, myLayout);
        txtQnB = UIComponentLibrary.CreateAJLabel("B:", 500,210,this, myLayout);
        txtQnC = UIComponentLibrary.CreateAJLabel("C:", 500,240,this, myLayout);
        txtQnD = UIComponentLibrary.CreateAJLabel("D:", 500,270,this, myLayout);
        txtQnE = UIComponentLibrary.CreateAJLabel("E:", 500,300,this, myLayout);
        txtqnNo= UIComponentLibrary.CreateAJLabel("", 523,120,this, myLayout);

        // Answers
        txtCorrectAns = UIComponentLibrary.CreateAJLabel("Correct Answer",550,325,this,myLayout);

        // Connection Status
        txtConnectionStatus = UIComponentLibrary.CreateAJLabel("Server Connection: Disconnected",10,640,this,myLayout);

    }
    // Setup Text Fields
    private void SetupTextfields()
    {
        searchBox = UIComponentLibrary.CreateAJTextField(20,60,75,this,myLayout);

        // Linked List

        LinkedList = UIComponentLibrary.CreateAJTextArea(5,70,10,390,this, myLayout);
        LinkedList.setLineWrap(true);
        LinkedList.setWrapStyleWord(true);
        LinkedList.setBorder(BorderFactory.createLineBorder(Color.black));

        // Binary Search
        BinarySearchtxt = UIComponentLibrary.CreateAJTextArea(5,70,10,490,this, myLayout);
        BinarySearchtxt.setBorder(BorderFactory.createLineBorder(Color.black));
        BinarySearchtxt.setLineWrap(true);
        BinarySearchtxt.setWrapStyleWord(true);

        // Question Fields
        topicBox = UIComponentLibrary.CreateAJTextField(35,550,60,this,myLayout);
        subtopicBox = UIComponentLibrary.CreateAJTextField(35,550,90,this,myLayout);
        questionBox = UIComponentLibrary.CreateAJTextField(35,550,120,this,myLayout);
        aBox = UIComponentLibrary.CreateAJTextField(35,550,180,this,myLayout);
        bBox = UIComponentLibrary.CreateAJTextField(35,550,210,this,myLayout);
        cBox = UIComponentLibrary.CreateAJTextField(35,550,240,this,myLayout);
        dBox = UIComponentLibrary.CreateAJTextField(35,550,270,this,myLayout);
        eBox = UIComponentLibrary.CreateAJTextField(35,550,300,this,myLayout);

        txtOne = UIComponentLibrary.CreateAJTextField(7,650,325,this,myLayout);
    }
    // Setup Functionality


    public void bubblesort()
    {
        bubble.bubbleSort(al);
    }
 public void displayQuestion(int index)
 {
     txtqnNo.setText(al.get(index)[0].toString());
     topicBox.setText(al.get(index)[1].toString());
     questionBox.setText(al.get(index)[3].toString());
     subtopicBox.setText(al.get(index)[2].toString());
     aBox.setText(al.get(index)[4].toString());
     bBox.setText(al.get(index)[5].toString());
     cBox.setText(al.get(index)[6].toString());
     dBox.setText(al.get(index)[7].toString());
     eBox.setText(al.get(index)[8].toString());

 }
    public void displayQuestionNumber(int index){ questionBox.setText(al.get(index)[0].toString());}

    private void DisplayText()
    {
        rowIndex = table.getSelectedRow();
        displayQuestionNumber(rowIndex);
        topicBox.setText("Test");
    }

    private void search(){}

    // End Functionality

    // action listner add
    private void AddWindowListenerToForm()
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
    public void mouseClicked(MouseEvent e) {
        rowIndex = table.getSelectedRow();
        displayQuestion(rowIndex);
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
    // Action Listener
    @Override
    public void actionPerformed(ActionEvent actionEvent) // action listner class
    {
        if (actionEvent.getSource() == btnExit) //  exit button
        {
            System.exit(0); // exit
        }
        if(actionEvent.getSource() == btnConnect){
            try
            {
                connect("localhost",4444);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                txtConnectionStatus.setText("Error" + e);
            }
        }
        if(actionEvent.getSource() == btnInorder)
        {

        }
        if(actionEvent.getSource() == btnTopic)
        {
            bubblesort();
            quizModel.fireTableDataChanged();
        }
        if (actionEvent.getSource() == btnDisplay){
            try
            {
                new SecondaryScreen();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if(actionEvent.getSource() == btnSend)
        {
            // question number, topic, subtopic
           linkedList.head.append(new Node( " <-->  " + questionBox.getText()+ "--" + topicBox.getText() + "--" + subtopicBox.getText()));
           LinkedList.setText(linkedList.toString());
           theTree.addNode(Integer.parseInt(txtqnNo.getText()), " || " + questionBox.getText() + "--" + topicBox.getText() + "--" + subtopicBox.getText());

            // System.out.println("Success");
        }
        theTree.binaryString = "";
        if(actionEvent.getSource() == btnInorder)
        {
            theTree.inOrderTraverseTree(theTree.root);
            BinarySearchtxt.setText(theTree.binaryString);
        }
        if(actionEvent.getSource() == btnPreorder)
        {
            theTree.preorderTraverseTree(theTree.root);
            BinarySearchtxt.setText(theTree.binaryString);
        }
        if(actionEvent.getSource() == btnPostorder)
        {
            theTree.postOrderTraverseTree(theTree.root);
            BinarySearchtxt.setText(theTree.binaryString);
        }

}}













