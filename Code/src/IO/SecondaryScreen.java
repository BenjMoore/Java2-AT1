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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import IO.mainScreen;

import java.io.IOException;

public class SecondaryScreen extends JFrame implements ActionListener, MouseListener{

    public static void main(String[] args)throws IOException {new SecondaryScreen();}

    SpringLayout myLayout = new SpringLayout();

    JLabel lblPolicyTitle, txtSearch,txtSort
            ,txtLinkedList,txtBinary,txtTitle
            ,txtPreorder,txtPostorder,txtInorder
            ,txtTopic,txtA,txtQN
            ,txtQnA,txtQnB,txtQnC
            ,txtQnD,txtQnE,txtCorrectAns,txtConnectionStatus
            ,txtSubtopic,txtStaffName;

    // Declare Text Fields
    JTextField searchBox, topicBox, questionBox,
            answerBox,aBox,bBox,
            cBox,dBox,eBox, txtOne,subtopicBox,StaffName;

    public SecondaryScreen() throws IOException
    {
        //initialise main screen
        setSize(500, 600);
        setLocation(0, 0);
        AddWindowListenerToForm();
        setLayout(myLayout);
        setResizable(false);
        //new BubbleSort(al);

        setTitle("Perfect Policys Quiz | Quiz Screen |");
        // Setup UI
        SetupButtons();
        SetupTextfields();
        SetupJlabels();
        setVisible(true);
        System.out.println("Initial size of al: ");
}

    private void SetupButtons()
    {

    }
    private void SetupTextfields()
    {
        topicBox = UIComponentLibrary.CreateAJTextField(35,70,60,this,myLayout);
        subtopicBox = UIComponentLibrary.CreateAJTextField(35,70,90,this,myLayout);
        questionBox = UIComponentLibrary.CreateAJTextField(35,70,120,this,myLayout);
        aBox = UIComponentLibrary.CreateAJTextField(35,70,180,this,myLayout);
        bBox = UIComponentLibrary.CreateAJTextField(35,70,210,this,myLayout);
        cBox = UIComponentLibrary.CreateAJTextField(35,70,240,this,myLayout);
        dBox = UIComponentLibrary.CreateAJTextField(35,70,270,this,myLayout);
        eBox = UIComponentLibrary.CreateAJTextField(35,70,300,this,myLayout);
        StaffName = UIComponentLibrary.CreateAJTextField(25,90,400,this,myLayout);

    }
    private void SetupJlabels()
    {
        txtTitle = UIComponentLibrary.CreateAJLabel("Perfect Policy's", 190,10,this,myLayout);
        txtTopic = UIComponentLibrary.CreateAJLabel("Topic:", 10,60,this, myLayout);
        txtSubtopic = UIComponentLibrary.CreateAJLabel("Subtopic",10,90,this,myLayout);
        txtQN = UIComponentLibrary.CreateAJLabel("Q# :", 10,120,this, myLayout);
        txtA = UIComponentLibrary.CreateAJLabel("Options", 235,150,this, myLayout);
        txtQnA = UIComponentLibrary.CreateAJLabel("A:", 10,180,this, myLayout);
        txtQnB = UIComponentLibrary.CreateAJLabel("B:", 10,210,this, myLayout);
        txtQnC = UIComponentLibrary.CreateAJLabel("C:", 10,240,this, myLayout);
        txtQnD = UIComponentLibrary.CreateAJLabel("D:", 10,270,this, myLayout);
        txtQnE = UIComponentLibrary.CreateAJLabel("E:", 10,300,this, myLayout);
        txtStaffName = UIComponentLibrary.CreateAJLabel("Staff Name",10,400,this, myLayout);

        txtA.setOpaque(true);
        txtTitle.setOpaque(true);
        txtA.setBackground(Color.cyan);
        txtTitle.setBackground(Color.cyan);
        txtTitle.setFont(new Font("Calbri",Font.PLAIN,18));

    }

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
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

