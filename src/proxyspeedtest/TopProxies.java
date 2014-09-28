/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyspeedtest;

/**
 *
 * @author Edwin
 */
public class TopProxies {
    private String[] proxySocketAddressList = { "", "", "", "", "" };
    private long[] proxySpeed = { 0, 0, 0, 0, 0 };
    
    TopProxies() {
        
    }
    
    public void evaluateSocket(String socket, long speed) {
        for (int i = 0; i < 5; i++)
        {
            if (proxySocketAddressList[i] == "")
            {
                proxySocketAddressList[i] = socket;
                proxySpeed[i] = speed;
                break;
            }
            else if (i == 4 && proxySpeed[4] > speed)
            {
                proxySocketAddressList[4] = socket;
                proxySpeed[4] = speed;
            }
        }
        
        
        sortSocketListBySpeed();
    }
    
    private void sortSocketListBySpeed() {
        for (int i = 0; i < 5; i++) {
            for (int j = 4; j > i; j--)
            {
                if (proxySocketAddressList[j] != "" && proxySpeed[j-1] > proxySpeed[j])
                {
                    String temp = proxySocketAddressList[j];
                    proxySocketAddressList[j] = proxySocketAddressList[j-1];
                    proxySocketAddressList[j-1] = temp;
                    
                    long tmp = proxySpeed[j];
                    proxySpeed[j] = proxySpeed[j-1];
                    proxySpeed[j-1] = tmp;
                }
            }
        }        
    }
    
    public String[] getTopSockets() {
        String[] topSockets = { "", "", "", "", "" };
        for (int i = 0; i < 5; i++)
            topSockets[i] = proxySocketAddressList[i] + " - " + proxySpeed[i];
        
        return topSockets;
    }
}
