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
            ,txtSubtopic;

    // Declare Text Fields
    JTextField searchBox, topicBox, questionBox,
            answerBox,aBox,bBox,
            cBox,dBox,eBox, txtOne,subtopicBox;

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

    private void SetupButtons(){}
    private void SetupTextfields(){


    }
    private void SetupJlabels(){}

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

