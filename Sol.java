/*
path C:\Program Files (x86)\java\jdk1.7.0\bin
cd c:\1
javac -Xlint:unchecked *.java
jar cmf manif.txt  ahk_listener_java.jar *.class
*/
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sol {
    public static int iamserver = 1;
    public static int counter = 0;
    public static int cleartic = 8;
    public static int clientnum = 1;
    public static String line1 = "ACT1";
    public static String line2 = "ACT2";
    public static String line3 = "ACT3";
    public static String line4 = "ACT4";
    public static volatile String sl = "";
    public static volatile String c1 = "";
    public static volatile String c2 = "";
    public static String serverstring = "";
    public static String nickname = "n";
    public static String iptarget = "127.0.0.1";
    public static int port = 26000;
    public static Socket clientSocket;
    public static ServerSocket server;
    public static BufferedReader in;
    public static BufferedWriter out;
    public static Socket clientSocket2;
    public static ServerSocket server2;
    public static BufferedReader in2;
    public static BufferedWriter out2;

    public static int inputc =0;
    public static   int fontsize = 42;
    public  static int xfromzero = 0;
    public  static int yfromzero = 0;
    public static   int wide = 640;
    public  static   int height = 70;
    public  static volatile String s=""; //for info display
    public static String spre="pre";
    public  static int key =0;
    static   JLabel label;
    static   TestPane panel = new TestPane();
    static JFrame frame;

    public Sol() {
        frame = new JFrame("JavaChat");
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try{
                    Runtime rt = Runtime.getRuntime();
                    rt.exec("taskkill /F /IM autohotkey.exe"); //all *.ahk scripts
                    rt.exec("taskkill /F /IM ahk_listener.exe"); // exactly ahk_listener.exe
                } catch (Exception e){}
                System.exit(0);
            }
        });
        frame.setUndecorated(true);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.add(panel);
        frame.setBounds(xfromzero,yfromzero,wide,height);
        frame.setVisible(true);
    }

    public static class TestPane extends JPanel {
        public TestPane() {
            setOpaque(false);
            setLayout(new FlowLayout(FlowLayout.LEFT));
            label = new JLabel(s);
            label.setFont(label.getFont().deriveFont(Font.BOLD, fontsize));
            label.setForeground(Color.RED);
            add(label);
        }
    }


    static void readoptions(){
        String str = "";
        try{
            int t;
            File myFile = new File("./ahk_listener_input.txt");
            Scanner scan = new Scanner(myFile);
          scan.nextLine(); str=scan.nextLine();
            try {t = Integer.parseInt(str); iamserver =t; } catch (Exception e) {}
            scan.nextLine(); str=scan.nextLine();
            try {t = Integer.parseInt(str); cleartic =t*2; } catch (Exception e) {}
            scan.nextLine(); str=scan.nextLine();
            try {t = Integer.parseInt(str); clientnum  =t; } catch (Exception e) {}
            scan.nextLine(); nickname=scan.nextLine();
            scan.nextLine(); iptarget=scan.nextLine();
            scan.nextLine(); str=scan.nextLine();
            try {t = Integer.parseInt(str); port  =t; } catch (Exception e) {}

            scan.nextLine(); str=scan.nextLine();
            try {t = Integer.parseInt(str); fontsize  =t; } catch (Exception e) {}
            scan.nextLine(); str=scan.nextLine();
            try {t = Integer.parseInt(str); xfromzero   =t; } catch (Exception e) {}
            scan.nextLine(); str=scan.nextLine();
            try {t = Integer.parseInt(str); yfromzero   =t; } catch (Exception e) {}
            scan.nextLine(); str=scan.nextLine();
            try {t = Integer.parseInt(str); wide   =t; } catch (Exception e) {}
            scan.nextLine(); str=scan.nextLine();
            try {t = Integer.parseInt(str); height   =t; } catch (Exception e) {}

            scan.nextLine(); line1 =scan.nextLine();
            scan.nextLine(); line2 =scan.nextLine();
            scan.nextLine(); line3 =scan.nextLine();
            scan.nextLine(); line4 =scan.nextLine();

            scan.close();
        }
        catch(Exception e) {createoptions();}
    }

    static void createoptions(){
        String nl = "\r\n";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./ahk_listener_input.txt"))) {
            writer.write("run as server (1 - yes, 0 - as client):"+nl);
            writer.write(iamserver+nl);
            writer.write("show message, sec (for server):"+nl);
            writer.write((cleartic/2)+nl);
            writer.write("number of clients: 1 or 2 (for server):"+nl);
            writer.write(clientnum+nl);
            writer.write("nickname (short is better):"+nl);
            writer.write(nickname +nl);
            writer.write("IP to try to connect for a client:"+nl);
            writer.write(iptarget  +nl);
            writer.write("port (for second client will be +1):"+nl);
            writer.write(port   +nl);

            writer.write("font size:"+nl);
            writer.write(fontsize    +nl);
            writer.write("x from top up, pix:"+nl);
            writer.write(xfromzero     +nl);
            writer.write("y from top up, pix:"+nl);
            writer.write(yfromzero      +nl);
            writer.write("wide, pix:"+nl);
            writer.write(wide       +nl);
            writer.write("height , pix:"+nl);
            writer.write(height        +nl);

            writer.write("command1 (short, in Latin alphabet):"+nl);
            writer.write(line1         +nl);
            writer.write("command2:"+nl);
            writer.write(line2         +nl);
            writer.write("command3:"+nl);
            writer.write(line3         +nl);
            writer.write("command4:"+nl);
            writer.write(line4         +nl);

            writer.close();
        }
        catch (Exception e) {}
    }

    public static void main(String[] args) {
        readoptions();
        new Sol();
       Draw upd =  new Draw(); new Thread(upd).start();

        if (iamserver==1){
            Server ser = new Server(); new Thread(ser).start();
            if (clientnum==2){Server2 ser2 = new Server2(); new Thread(ser2).start();}
        }
        else{
           Client ser = new Client(); new Thread(ser).start();
        }
        try{Thread.sleep(2000);}catch(InterruptedException e){System.out.println(e);}

        frame.getContentPane().removeAll();
        frame.setAlwaysOnTop(false);
        frame.setAlwaysOnTop(true);
        frame.setExtendedState(JFrame.NORMAL);
        try {  frame.setLocationByPlatform( true );} catch (Exception e) {}
        label.setText(s);
        panel.add(label);
        frame.add(panel);
        frame.setBounds(xfromzero,yfromzero,wide,height);
        frame.setVisible(true);

        String filen = null;
        File myFile=null;
         inputc = 0;
        while (true){
            filen = "./d1";
            try{ myFile = new File(filen); Scanner scan = new Scanner(myFile); scan.close(); inputc =1;
                try{ myFile.delete(); } catch(Exception e) {} } catch(Exception e) {}
            filen = "./d2";
            try{ myFile = new File(filen); Scanner scan = new Scanner(myFile); scan.close(); inputc =2;
                try{ myFile.delete(); } catch(Exception e) {} } catch(Exception e) {}
            filen = "./d3";
            try{ myFile = new File(filen); Scanner scan = new Scanner(myFile); scan.close(); inputc =3;
                try{ myFile.delete(); } catch(Exception e) {} } catch(Exception e) {}
            filen = "./d4";
            try{ myFile = new File(filen); Scanner scan = new Scanner(myFile); scan.close(); inputc =4;
                try{ myFile.delete(); } catch(Exception e) {} } catch(Exception e) {}
            if (inputc!=0){ key = inputc;  inputc=0;}
            try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}
        }

    }

}