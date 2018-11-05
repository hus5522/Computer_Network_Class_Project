package Control;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CloudServerManager implements Runnable{

    /*상수*/
    static final int THREADNUM=5;
    /*전역변수*/
    ServerSocket serverSocket;
    Thread[] threadArr;

    private static CloudServerManager instance;
    /*생성자*/
    private CloudServerManager(){
        try{
            serverSocket=new ServerSocket(7777);
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

                
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
