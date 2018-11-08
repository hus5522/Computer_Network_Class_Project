package HTTPHeader;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
            startIndex += 5;
        } else if ((startIndex = httpString.indexOf("POST")) != -1) {
            method = Method.POST;
            startIndex += 6;
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
        try {
            pathName = URLDecoder.decode(pathName, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
