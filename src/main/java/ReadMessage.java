import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadMessage implements Runnable{
    ObjectInputStream in;

    public ReadMessage(ObjectInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        Client c = new Client();
        //while(c.get_stop_mark() == 0){
            try {
                while(c.get_stop_mark() == 0) {
                    String s = (String) in.readObject();
                    if (s.length() > 0) {
                        System.out.println(s);
                    }
                }
            } catch (IOException e) {
                System.out.println("connection is canceled");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

       // }
    }
}
