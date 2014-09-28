/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyspeedtest;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Edwin
 */
public class MainWindow extends JFrame {
    //private JButton btnManageList;
    private JButton btnStartCheck;
    private JTextArea txtResults;
    
    MainWindow(String s) {
        setTitle(s);
    }
    
    public void showWindow() {
        setSize(350, 250);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void setComponents() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        
        //btnManageList = new JButton("Manage Proxy List");
        //controlPanel.add(btnManageList);
        
        btnStartCheck = new JButton("Start Speed Test");
        controlPanel.add(btnStartCheck);
        
        btnStartCheck.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ConnectionManager cm = new ConnectionManager();
                        String message = "Connecting to test server...";
                        
                        if (cm.connectTestServer())
                        {
                            message += "\nTesting listed proxy servers, please wait...";
                            setResultText(message);
                            
                            message = "" + cm.testProxyServers();
                            setResultText(message);
                        }
                        else
                        {
                            message += "\nTest server unreachable.";
                            setResultText(message);
                        }
                    }
                }
                );
        
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        
        JLabel fastestProxies = new JLabel("Fastest proxies found:");
        resultPanel.add(fastestProxies, BorderLayout.NORTH);
        
        txtResults = new JTextArea("The test may need some minutes to perform.");
        txtResults.setEditable(false);
        resultPanel.add(txtResults, BorderLayout.CENTER);
        
        JPanel aboutPanel = new JPanel();
        aboutPanel.setLayout(new FlowLayout());
        
        JLabel aboutUs = new JLabel("Code Written by Edwin Tunggawan");
        aboutPanel.add(aboutUs);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(resultPanel, BorderLayout.CENTER);
        mainPanel.add(aboutPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void setResultText(String s) {
        txtResults.setText(s);
    }
}
