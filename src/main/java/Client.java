import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static int stop_mark = 0;
    public int get_stop_mark()
    {
        return stop_mark;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Socket socket;
        String msg = new String();

        while (!msg.equals("/exit")) try {
            socket = new Socket("localhost", 3443);

            ObjectInputStream b_in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());


            Thread thread = new Thread(new ReadMessage(b_in));
            thread.start();
            int mark = 0;
            while (mark == 0) {
                msg = in.nextLine();
                out.writeObject(msg);
                out.flush();
                if (msg.equals("/exit")){
                    mark = 1;
                    stop_mark = 1;
                }
            }
            out.close();
            b_in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
