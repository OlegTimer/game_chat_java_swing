import java.io.*;
import java.util.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client implements Runnable{
    public  void run(){
        try {
            try {
                Sol.clientSocket = new Socket(Sol.iptarget, Sol.port);
                Sol.in = new BufferedReader(new InputStreamReader(Sol.clientSocket.getInputStream()));
                Sol.out = new BufferedWriter(new OutputStreamWriter(Sol.clientSocket.getOutputStream()));
          //   Sol.out.write("cl hello"+ "\n"); Sol.out.flush();
                String serverWord;
                while(true){//cycle start
                     serverWord = Sol.in.readLine();
                    Sol.s = serverWord;
                  //  System.out.println("server: "+serverWord);
                        Sol.s = serverWord;
                    if (Sol.key!=0){
                    Sol.out.write(Sol.nickname + " "+Sol.key+ "\n");
                    }
                    else{
                        Sol.out.write(""+ "\n");
                    }
                    Sol.out.flush();
                    Sol.key = 0;
                }//cycle end
            } finally {
              //  System.out.println("Client closed");
                Sol.s = "Client closed";
                Sol.clientSocket.close();
                Sol.in.close();
                Sol.out.close();
            }
        } catch (IOException e) {
          //  System.err.println(e);
        }
    }
}
