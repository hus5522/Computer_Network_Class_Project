package Control;

import Former.HttpFormer;
import HTTPHeader.*;
import Helper.LocalFileReader;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private CloudServerManager manager;
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        manager = CloudServerManager.getInstance();
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println(clientSocket.getInetAddress() + " has connected!");

        String requestMessage;

        try {
            InputStream inFrom = clientSocket.getInputStream();
            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
            byte[] readData = new byte[5000];
            inFrom.read(readData);
            requestMessage = new String(readData);
            System.out.println(requestMessage);
            RequestHttp requestHttp = new RequestHttp(requestMessage);
            if (requestHttp.GetPathName().equals("favicon.ico")) {
                outToClient.close();
                return;
            }
            if (requestHttp.isRequestMessage()) {
                // confirm user info
                AuthorizationRequestHeader authorizationRequestHeader = new AuthorizationRequestHeader(requestMessage);

                String clientID = authorizationRequestHeader.GetUserID();
                String clientPassword = authorizationRequestHeader.GetUserPassword();

                System.out.println(clientSocket.getInetAddress() + " try to sign in.");
                System.out.println("ID : " + clientID + ", PW : " + clientPassword);

                if (!manager.GetUserID().equals(clientID) || !manager.GetUserPassword().equals(clientPassword)) {
                    ResponseHttp responseHttp = new ResponseHttp(ResponseHttp.StatusCode.Unauthorized);
                    HttpFormer httpMessage = new HttpFormer(responseHttp);
                    WWWAutheticateResponseHeader wwwAutheticateResponseHeader = new WWWAutheticateResponseHeader(AuthorizationRequestHeader.EncodingType.Basic);
                    ContentLengthResponseHeader contentLengthResponseHeader = new ContentLengthResponseHeader(null);
                    KeepAliveResponseHeader keepAliveResponseHeader = new KeepAliveResponseHeader(5, 100);
                    ConnectionResponseHeader connectionResponseHeader = new ConnectionResponseHeader(ConnectionResponseHeader.ConnectionType.keep_alive);
                    ContentTypeResponseHeader contentTypeResponseHeader = new ContentTypeResponseHeader(ContentTypeResponseHeader.ContentType.text_html, ContentTypeResponseHeader.CharType.utf_8);
                    httpMessage.AddHeaderField(contentTypeResponseHeader);
                    httpMessage.AddHeaderField(wwwAutheticateResponseHeader);
                    httpMessage.AddHeaderField(contentLengthResponseHeader);
                    httpMessage.AddHeaderField(keepAliveResponseHeader);
                    httpMessage.AddHeaderField(connectionResponseHeader);
                    httpMessage.SetEndOfHeader();
                    outToClient.writeBytes(httpMessage.toString());
                    outToClient.close();
                    System.out.println(clientSocket.getInetAddress() + " has closed!");
                    return;
                }
            } else {
                outToClient.close();
                System.out.println(clientSocket.getInetAddress() + " has closed!");
                return;
            }

            String pathName = manager.GetRootFolderPath() + requestHttp.GetPathName();
            System.out.println(pathName);

            StringBuilder files = new StringBuilder();
            LocalFileReader localFileReader = new LocalFileReader(pathName);
            ArrayList<String> fileList = localFileReader.GetAllContentsList();

            int length = 0;
            if(fileList == null)
            {
                localFileReader.GetContentsInFile()

                localFileReader = new LocalFileReader(manager.GetRootFolderPath());
                fileList = localFileReader.GetAllContentsList();
            }

            for (String file : fileList) {
                files.append(file);
                files.append("\r\n");
                length += file.length() + 2;
            }
            ResponseHttp responseHttp = new ResponseHttp(ResponseHttp.StatusCode.OK);
            HttpFormer httpMessage = new HttpFormer(responseHttp);
            ContentLengthResponseHeader contentLengthResponseHeader = new ContentLengthResponseHeader(length);
            KeepAliveResponseHeader keepAliveResponseHeader = new KeepAliveResponseHeader(5, 100);
            ConnectionResponseHeader connectionResponseHeader = new ConnectionResponseHeader(ConnectionResponseHeader.ConnectionType.keep_alive);
            ContentTypeResponseHeader contentTypeResponseHeader = new ContentTypeResponseHeader(ContentTypeResponseHeader.ContentType.text_html, ContentTypeResponseHeader.CharType.utf_8);
            httpMessage.AddHeaderField(contentTypeResponseHeader);
            httpMessage.AddHeaderField(contentLengthResponseHeader);
            httpMessage.AddHeaderField(keepAliveResponseHeader);
            httpMessage.AddHeaderField(connectionResponseHeader);
            httpMessage.SetEndOfHeader();
            httpMessage.AddBody(files.toString());
            outToClient.writeBytes(httpMessage.toString());
            outToClient.close();
            System.out.println(clientSocket.getInetAddress() + " has closed!");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
