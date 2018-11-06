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
        StringBuilder requestMessage = new StringBuilder();
        StringBuilder responseMessage = new StringBuilder();
        String sentence;
        try {
            DataInputStream inFromClient = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
            byte[] readData = new byte[5000];
            InputStream inFrom = clientSocket.getInputStream();
            inFrom.read(readData);
//            inFromClient.readFully(readData);
            requestMessage.append(new String(readData));
            System.out.println(requestMessage.toString());
            // confirm user info
            AuthorizationRequestHeader authorizationRequestHeader = new AuthorizationRequestHeader(requestMessage.toString());

            String clientID = authorizationRequestHeader.GetUserID();
            String clientPassword = authorizationRequestHeader.GetUserPassword();
            System.out.println("client id : " + clientID + ", cliend pw: " + clientPassword);
            if (!manager.GetUserID().equals(clientID) || !manager.GetUserPassword().equals(clientPassword)) {
                ResponseHttp responseHttp = new ResponseHttp(ResponseHttp.StatusCode.Unauthorized);
                HttpFormer httpMessage = new HttpFormer(responseHttp);
                WWWAutheticateResponseHeader wwwAutheticateResponseHeader = new WWWAutheticateResponseHeader(AuthorizationRequestHeader.EncodingType.Basic);
                httpMessage.AddHeaderField(wwwAutheticateResponseHeader);
                ContentLengthResponseHeader contentLengthResponseHeader = new ContentLengthResponseHeader(null);
                httpMessage.AddHeaderField(contentLengthResponseHeader);
                KeepAliveResponseHeader keepAliveResponseHeader = new KeepAliveResponseHeader(5, 100);
                httpMessage.AddHeaderField(keepAliveResponseHeader);
                ConnectionResponseHeader connectionResponseHeader = new ConnectionResponseHeader(ConnectionResponseHeader.ConnectionType.keep_alive);
                httpMessage.AddHeaderField(connectionResponseHeader);
                ContentTypeResponseHeader contentTypeResponseHeader = new ContentTypeResponseHeader(ContentTypeResponseHeader.ContentType.text_html, ContentTypeResponseHeader.CharType.utf_8);
                httpMessage.AddHeaderField(contentTypeResponseHeader);
                httpMessage.SetEndOfHeader();
                System.out.println(httpMessage.toString());
                outToClient.writeBytes(httpMessage.toString());
                outToClient.close();
                return;
            }
            else
            {
                outToClient.close();
                return;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
