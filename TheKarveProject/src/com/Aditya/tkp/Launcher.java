package com.Aditya.tkp;
import java.awt.*; 
 
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;  
public class Launcher extends Frame implements ActionListener{  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TextField name; 
    JLabel welcome, title;
    String args[];
    Button startGame,exit;  
    Launcher(){  
        name=new TextField();  
        name.setBounds(300,290,200,20);  
        name.setEditable(true); 
        startGame=new Button("Start Game");  
        startGame.setBounds(50,550,100,30);  
        welcome = new JLabel("Welcome player, enter a nick");
        welcome.setBounds(600,330,600,500);
        
        exit=new Button("exit");  
        exit.setBounds(160,550,100,30);  
        startGame.addActionListener(this);  
        exit.addActionListener(this);  
        add(name);
        add(startGame);
        add(exit);
        add(welcome);
        setSize(800,600);  
        setLayout(null);  
        setVisible(true);  
        setResizable(false);
        setTitle("DemonQuest v0.1 alpha");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }         
    public void actionPerformed(ActionEvent e) {  
        String playerName=name.getText();  
        if(e.getSource()==startGame){
        	if(name.getText().isEmpty()) {
        		name.setText("Enter a name");
        		return;
        	}
            Game game = new Game(name.getText()); 
            Game.main(game);
            dispose();
        }
        if(e.getSource()==exit){  
            dispose();
        }
        
    }  
public static void main(String[] args) {  
    new Launcher();  
}  
}  
