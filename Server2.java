import java.io.*;
import java.net.ServerSocket;

public class Server2 implements Runnable{

    public  void run(){

        try {
            try  {
                Sol.server2 = new ServerSocket(Sol.port+1);

                Sol.clientSocket2 = Sol.server2.accept();
                String Clientword;
                String[]  temp2;
                try {
                    Sol.in2 = new BufferedReader(new InputStreamReader(Sol.clientSocket2.getInputStream()));
                    Sol.out2 = new BufferedWriter(new OutputStreamWriter(Sol.clientSocket2.getOutputStream()));
                    while (true) {//cycle start

                        Sol.out2.write(Sol.serverstring + "\n");
                        Sol.out2.flush();
                         Clientword = Sol.in2.readLine();
                     //    System.out.println("client2: "+Clientword);
                        try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
                        if (Clientword.equals("")) {} else{
                            try{
                              temp2 = Clientword.split(" ");
                            if (temp2[1].equals("1")){Sol.c2=temp2[0]+":"+Sol.line1;}
                            if (temp2[1].equals("2")){Sol.c2=temp2[0]+":"+Sol.line2;}
                            if (temp2[1].equals("3")){Sol.c2=temp2[0]+":"+Sol.line3;}
                            if (temp2[1].equals("4")){Sol.c2=temp2[0]+":"+Sol.line4;}

                                Sol.counter=Sol.cleartic;
                            } catch (Exception e) {}
                        }

                    }//cycle end

                } finally {
                    Sol.clientSocket2.close();
                    Sol.in2.close();
                    Sol.out2.close();
                }
            } finally {
             //   System.out.println("Server is closed");
                Sol.server2.close();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    } //run server end


}