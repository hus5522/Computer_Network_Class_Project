import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
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
            byte[] readData = new byte[10000];
            inFrom.read(readData);
            requestMessage = new String(readData);

            RequestHttp requestHttp = new RequestHttp(requestMessage);
            if (requestHttp.GetPathName().equals("favicon.ico")) {
                System.out.println("Ignored favicon.ico");
                System.out.println(clientSocket.getInetAddress() + " has closed!");
                outToClient.close();
                return;
            }
            if (requestHttp.isRequestMessage()) {
                // confirm user info
                AuthorizationRequestHeader authorizationRequestHeader = new AuthorizationRequestHeader(requestMessage);

                String clientID = authorizationRequestHeader.GetUserID();
                String clientPassword = authorizationRequestHeader.GetUserPassword();

                System.out.println(clientSocket.getInetAddress() + " tried to sign in.");
                System.out.println("ID : " + clientID + ", PW : " + clientPassword);

                if (!manager.GetUserID().equals(clientID) || !manager.GetUserPassword().equals(clientPassword)) {
                    System.out.println(clientSocket.getInetAddress() + " was Unathorized!");
                    ResponseHttp responseHttp = new ResponseHttp(ResponseHttp.StatusCode.Unauthorized);
                    HttpFormer httpMessage = new HttpFormer(responseHttp);
                    WWWAutheticateResponseHeader wwwAutheticateResponseHeader = new WWWAutheticateResponseHeader(AuthorizationRequestHeader.EncodingType.Basic);
                    ContentLengthResponseHeader contentLengthResponseHeader = new ContentLengthResponseHeader(0);
                    KeepAliveResponseHeader keepAliveResponseHeader = new KeepAliveResponseHeader(5, 100);
                    ConnectionResponseHeader connectionResponseHeader = new ConnectionResponseHeader(ConnectionResponseHeader.ConnectionType.keep_alive);
                    ContentTypeResponseHeader contentTypeResponseHeader = new ContentTypeResponseHeader(ContentTypeResponseHeader.ContentType.text_html, ContentTypeResponseHeader.CharType.utf_8);
                    httpMessage.AddHeaderField(contentTypeResponseHeader);
                    httpMessage.AddHeaderField(wwwAutheticateResponseHeader);
                    httpMessage.AddHeaderField(contentLengthResponseHeader);
                    httpMessage.AddHeaderField(keepAliveResponseHeader);
                    httpMessage.AddHeaderField(connectionResponseHeader);
                    httpMessage.SetEndOfHeader();

                    outToClient.write(httpMessage.toString().getBytes(StandardCharsets.UTF_8));
                    outToClient.close();
                    System.out.println(clientSocket.getInetAddress() + " has closed!");
                    return;
                }
            } else {
                outToClient.close();
                System.out.println(clientSocket.getInetAddress() + " has closed!");
                return;
            }

            String pathName = manager.GetRootFolderPath() + "/" + requestHttp.GetPathName();
            System.out.println(clientSocket.getInetAddress() + " requested " + pathName);
            String htmlString = null;
            StringBuilder files = new StringBuilder();
            LocalFileReader localFileReader = new LocalFileReader(pathName);
            ArrayList<String> fileList = localFileReader.GetAllContentsList();

            int length = 0;
            // 디렉토리가 아니면 fileList = null
            if(fileList == null)
            {
                byte[] contents = localFileReader.GetContentsInFile();
                // 파일도 아닐 경우, root 디렉토리 기준 파일리스트를 출력하는 html 생성
                if(contents == null)
                {
                    FileListHTMLFormer listHtml = new FileListHTMLFormer("");
                    htmlString = listHtml.GetHtmlString();
                    length = htmlString.length();
                } // 파일일 경우, 파일 내용을 출력하는 html 을 생성
                else
                {
                    FileContentHTMLFormer contentHtml = new FileContentHTMLFormer(requestHttp.GetPathName());
                    htmlString = contentHtml.GetHtmlString();
                    length = htmlString.length();
                }
            } // 디렉토리일 경우, 파일리스트를 출력하는 html 을 생성
            else {
                FileListHTMLFormer listHtml = new FileListHTMLFormer(requestHttp.GetPathName());
                htmlString = listHtml.GetHtmlString();
                length = htmlString.length();
            }
            ResponseHttp responseHttp = new ResponseHttp(ResponseHttp.StatusCode.OK);
            HttpFormer httpMessage = new HttpFormer(responseHttp);
            AcceptRangeResponseHeader acceptRangeResponseHeader = new AcceptRangeResponseHeader(AcceptRangeResponseHeader.RangeType.bytes);
            ContentLengthResponseHeader contentLengthResponseHeader = new ContentLengthResponseHeader(length);
            KeepAliveResponseHeader keepAliveResponseHeader = new KeepAliveResponseHeader(5, 100);
            ConnectionResponseHeader connectionResponseHeader = new ConnectionResponseHeader(ConnectionResponseHeader.ConnectionType.keep_alive);
            ContentTypeResponseHeader contentTypeResponseHeader = new ContentTypeResponseHeader(ContentTypeResponseHeader.ContentType.text_html, ContentTypeResponseHeader.CharType.utf_8);
            httpMessage.AddHeaderField(acceptRangeResponseHeader);
            httpMessage.AddHeaderField(contentLengthResponseHeader);
            httpMessage.AddHeaderField(keepAliveResponseHeader);
            httpMessage.AddHeaderField(connectionResponseHeader);
            httpMessage.AddHeaderField(contentTypeResponseHeader);
            httpMessage.SetEndOfHeader();
            httpMessage.AddBody(htmlString);
            outToClient.write(httpMessage.toString().getBytes(StandardCharsets.UTF_8));
            outToClient.close();
            System.out.println(clientSocket.getInetAddress() + " has closed!");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
