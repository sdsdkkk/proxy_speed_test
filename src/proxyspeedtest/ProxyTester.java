/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyspeedtest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

/**
 *
 * @author Edwin
 */
public class ProxyTester {
    private String serverAddress = "http://twistedcablestestingground.freehosting.com/html/header.html";
    private Proxy proxy = null;
    private long time = 0;
    
    ProxyTester(Proxy p) {
        proxy = p;
    }
    
    public void testProxy() {
        try {
            URL url = new URL(serverAddress);
            long start = System.nanoTime();
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection(proxy);
            System.out.println(urlConn.getConnectTimeout());
            
            if (urlConn.getContent() != null)
                time = System.nanoTime() - start;
        } catch (IOException e) {
            System.err.println("Error creating HTTP connection");
        }
    }
    
    public long getTime() {
        return time;
    }
}
