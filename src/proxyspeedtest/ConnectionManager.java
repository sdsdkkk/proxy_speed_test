/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyspeedtest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edwin
 */
public class ConnectionManager {
    //private String serverAddress = "http://twistedcablestestingground.freehosting.com/html/header.html";
    private String serverAddress = "http://www.google.com";
            
    public boolean connectTestServer() {
        try {
            URL url = new URL(serverAddress);
            //url.openConnection();
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            //urlConn.connect();
            //urlConn.disconnect();
            
            if (urlConn.getContent() != null)
                return true;
            else
                return false;
            //assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
        } catch (IOException e) {
            System.err.println("Error creating HTTP connection");
            return false;
        }
    }
    
    public String testProxyServers() {
        File f = new File("proxy_list.txt");
        
        if (!f.exists()) {
            return "File proxy_list.txt not found.";
        }
        else {
            String s = "";
            TopProxies tp = new TopProxies();
            try {
                BufferedReader in = new BufferedReader(new FileReader(f));
                
                while (true) {
                    String line = null;
                    try {
                        line = in.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (line == null)
                        break;
                    else if (line.startsWith("#") || line.compareTo("") == 0)
                        continue;
                    
                    String[] socketPart = line.split(":");
            
                    ProxyTester pt = new ProxyTester(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(socketPart[0], Integer.parseInt(socketPart[1]))));
                    
                    pt.testProxy();
                    
                    if (pt.getTime() != 0)
                        tp.evaluateSocket(line, pt.getTime());
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            s = "";
            
            String[] topSockets = tp.getTopSockets();
            for (int i = 0; i < 5; i++)
            {
                if (topSockets[i].compareTo(" - 0") != 0)
                s += (topSockets[i] + "\n");
            }
            
            if (s.compareTo("") == 0)
                s = "No usable proxy found."; 
           
            return s;
        }
    }
}
