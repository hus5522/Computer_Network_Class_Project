package HTTPHeader;

import HTTPHeader.AMessageType;

public class RequestHttp extends AMessageType {
    public enum Method {
        GET, POST
    }
    private boolean isRequest = false;
    private StringBuilder requestMessage;
    private Method method;
    private String pathName;
    private final String hostName = "127.0.0.1";

    public RequestHttp(String httpString) {
        int startIndex;
        if ((startIndex = httpString.indexOf("GET")) != -1) {
            method = Method.GET;
            startIndex += 4;
        } else if ((startIndex = httpString.indexOf("POST")) != -1) {
            method = Method.POST;
            startIndex += 5;
        }
        else
        {
            isRequest = false;
            return;
        }
        isRequest = true;
        int endIndex = httpString.indexOf("HTTP");
        endIndex--;
        pathName = httpString.substring(startIndex, endIndex);
    }

    public RequestHttp(Method method, String pathName) {

        requestMessage = new StringBuilder();
        this.method = method;
        this.pathName = pathName;

        if (method == Method.GET) {
            requestMessage.append("GET ");
        } else if (method == Method.POST) {
            requestMessage.append("POST ");
        }

        requestMessage.append(pathName);
        requestMessage.append(" HTTP/1.1\r\n");
        requestMessage.append("Host: ");
        requestMessage.append(hostName);
        requestMessage.append("\r\n");
    }

    public Method GetMethod() {
        return method;
    }

    public boolean isRequestMessage()
    {
        return isRequest;
    }

    public String GetPathName() {
        return pathName;
    }

    @Override
    public MessageType GetMessageType() {
        return MessageType.Request;
    }

    @Override
    public String toString() {
        return requestMessage.toString();
    }
}
