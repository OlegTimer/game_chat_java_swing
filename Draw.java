public class Draw implements Runnable{

    public  void run(){
        while (true){
            Sol.frame.setAlwaysOnTop(false);
            Sol.frame.setAlwaysOnTop(true);
            Sol.frame.remove(Sol.panel);
            Sol.frame.setBounds(Sol.xfromzero,Sol.yfromzero,Sol.wide,Sol.height);
            Sol.panel.setBounds(Sol.xfromzero,Sol.yfromzero,Sol.wide,Sol.height);
            Sol.label.setText(Sol.s);
            Sol.panel.add(Sol.label);
            Sol.frame.add(Sol.panel);
            try{Thread.sleep(400);}catch(InterruptedException e){System.out.println(e);}
        }
    }
}
