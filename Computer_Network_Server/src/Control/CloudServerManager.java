package Control;


import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;


public class CloudServerManager{
    private HashSet<String> ipAddrSet;
    private static CloudServerManager instance;
    private ServerSocket serverSocket;
    private String rootFolderPath = null;
    private String userID;
    private String userPassword;

    /*생성자*/
    private CloudServerManager(){
        try{
            ipAddrSet = new HashSet<>();
            serverSocket=new ServerSocket(80);
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

    public void OperateServer() {
        Scanner s = new Scanner(System.in);
        System.out.print("User ID : ");
        userID = s.nextLine();
        System.out.print("User Password : ");
        userPassword = s.nextLine();

        File existFile = null;

        do {
            if(rootFolderPath != null)
            {
                System.out.println("Wrong Path!");
            }
            System.out.print("Root Folder Path : ");
            rootFolderPath = s.nextLine();
            existFile = new File(rootFolderPath);
        } while(existFile.listFiles() == null || !existFile.isDirectory());

        while(true){
            try{

                ClientHandler client = new ClientHandler(socket);
                client.start();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String GetRootFolderPath()
    {
        return rootFolderPath;
    }

    public String GetUserID()
    {
        return userID;
    }
    public String GetUserPassword()
    {
        return userPassword;
    }
}
