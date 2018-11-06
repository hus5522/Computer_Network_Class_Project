package Control;

import Former.HttpFormer;
import HTTPHeader.*;

import java.io.*;
import java.net.Socket;

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

        StringBuilder requestMessage = new StringBuilder();
        try {
            InputStream inFrom = clientSocket.getInputStream();
            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
            byte[] readData = new byte[5000];
            inFrom.read(readData);
//            inFromClient.readFully(readData);
            requestMessage.append(new String(readData));
            RequestHttp requestHttp = new RequestHttp(requestMessage.toString());
            if (requestHttp.isRequestMessage()) {
                // confirm user info
                AuthorizationRequestHeader authorizationRequestHeader = new AuthorizationRequestHeader(requestMessage.toString());

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
            }
            else
            {
                outToClient.close();
                System.out.println(clientSocket.getInetAddress() + " has closed!");
                return;
            }

            String pathName = manager.GetRootFolderPath() + requestHttp.GetPathName();

            outToClient.close();
            System.out.println(clientSocket.getInetAddress() + " has closed!");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
