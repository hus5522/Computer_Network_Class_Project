package HTTPHeader;

import HTTPHeader.AMessageType;

public class RequestHttp extends AMessageType {
    public enum Method
    {
        GET, POST
    }

    private StringBuilder requestMessage;
    private Method method;

    public RequestHttp(String httpString)
    {
         
    }

    public RequestHttp(Method method, String pathName, String hostName)
    {

        requestMessage = new StringBuilder();
        this.method = method;

        if(method == Method.GET)
        {
            requestMessage.append("GET ");
        }
        else if(method == Method.POST)
        {
            requestMessage.append("POST ");
        }

        requestMessage.append(pathName);
        requestMessage.append(" HTTP/1.1\r\n");
        requestMessage.append("Host: ");
        requestMessage.append(hostName);
        requestMessage.append("\r\n");
    }

    @Override
    public MessageType GetMessageType()
    {
        return MessageType.Request;
    }

    @Override
    public String GetMessage() {
        return requestMessage.toString();
    }
}
