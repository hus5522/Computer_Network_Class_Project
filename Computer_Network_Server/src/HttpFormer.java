
import java.util.Vector;

public class HttpFormer {

    private StringBuilder httpString;
    private AMessageType.MessageType msgType;
    private Vector<ABaseResponseHeader> headerVector;

    public HttpFormer(AMessageType messageType) {
        httpString = new StringBuilder();
        msgType = messageType.GetMessageType();
        httpString.append(messageType.toString());
    }

    public void AddHeaderField(ABaseResponseHeader header)
    {
        httpString.append(header.toString());
    }

    public void SetEndOfHeader()
    {
        httpString.append("\r\n");
    }

    public void AddBody(String body)
    {
        httpString.append(body);
    }

    @Override
    public String toString() {
        return httpString.toString();
    }
}


