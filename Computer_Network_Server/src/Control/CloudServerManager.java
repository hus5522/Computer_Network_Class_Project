package Control;


<<<<<<< HEAD
=======
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
>>>>>>> bd4df2afa1eceefc98e427d5d690d9a7bf657326
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class CloudServerManager implements Runnable{

    private static CloudServerManager instance;
    /*상수*/
    static final int THREADNUM=5;
    /*전역변수*/
    ServerSocket serverSocket;
    Thread[] threadArr;


    /*생성자*/
    private CloudServerManager(){
        try{
            serverSocket=new ServerSocket(80);
            threadArr=new Thread[THREADNUM];
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /*인스턴스 생성및 리턴*/
    public static CloudServerManager getInstance(){
        if(instance==null)
            instance=new CloudServerManager();
        return instance;
    }
    /*서버 시작*/
    public void start(){
        for(int i=0;i<THREADNUM;i++){
            threadArr[i]=new Thread(this);
            threadArr[i].start();

        }
    }
    @Override
    public void run() {
        while(true){
            try{
                Socket socket=serverSocket.accept();
<<<<<<< HEAD
=======

                /*
                CleintHandler 스레드를 시작시킴
                ClientHandler(socket).start();
                */

>>>>>>> bd4df2afa1eceefc98e427d5d690d9a7bf657326
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
