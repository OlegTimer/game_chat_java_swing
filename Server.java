import java.io.*;
import java.net.ServerSocket;
import java.net.InetAddress;

public class Server implements Runnable{

    public  void run(){

        try {
            try  {
                Sol.server = new ServerSocket(Sol.port);

                String localip="";
                InetAddress ip;
                try {
                    ip = InetAddress.getLocalHost();
                    localip= "Server runs. IP: " + ip.getHostAddress();
                } catch (Exception e) {}
                Sol.s = localip;

                Sol.clientSocket = Sol.server.accept();
                String Clientword;
                String[]  temp2;
                try {
                    Sol.s = "";
                    Sol.in = new BufferedReader(new InputStreamReader(Sol.clientSocket.getInputStream()));
                    Sol.out = new BufferedWriter(new OutputStreamWriter(Sol.clientSocket.getOutputStream()));
                    while (true) {//cycle start

                        Sol.serverstring =Sol.c1+" "+Sol.c2+" " +Sol.sl;
                        Sol.s = Sol.serverstring;
                        Sol.out.write(Sol.serverstring + "\n");
                        Sol.out.flush();
                        Sol.key=0;
                         Clientword = Sol.in.readLine();
                        Sol.s = Sol.serverstring;
                     //    System.out.println("client: "+Clientword);
                        try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
                        if (Clientword.equals("")) {} else{
                            try{
                              temp2 = Clientword.split(" ");
                            if (temp2[1].equals("1")){Sol.c1=temp2[0]+":"+Sol.line1;}
                            if (temp2[1].equals("2")){Sol.c1=temp2[0]+":"+Sol.line2;}
                            if (temp2[1].equals("3")){Sol.c1=temp2[0]+":"+Sol.line3;}
                            if (temp2[1].equals("4")){Sol.c1=temp2[0]+":"+Sol.line4;}

                            Sol.counter=Sol.cleartic;
                            } catch (Exception e) {}
                        }

                        if (Sol.key!=0){
                            if (Sol.key==1){Sol.sl = Sol.nickname+":"+Sol.line1;}
                            if (Sol.key==2){Sol.sl = Sol.nickname+":"+Sol.line2;}
                            if (Sol.key==3){Sol.sl = Sol.nickname+":"+Sol.line3;}
                            if (Sol.key==4){Sol.sl = Sol.nickname+":"+Sol.line4;}

                            Sol.counter=Sol.cleartic;
                        }

if (Sol.counter>0) {Sol.counter--;}
if (Sol.counter==1){Sol.counter = 0; Sol.sl = ""; Sol.c1 = "";  Sol.c2 = "";}

                    }//cycle end

                } finally {
                    Sol.clientSocket.close();
                    Sol.in.close();
                    Sol.out.close();
                }
            } finally {
             //   System.out.println("Server is closed");
                Sol.server.close();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    } //run server end


}