package Former;

import HTTPHeader.ABaseHeader;
import HTTPHeader.AMessageType;

import java.util.Vector;

public class HttpFormer {
    // http message's end of line
    public final String END_LINE = "\r\n";

    private StringBuilder httpString;
    private AMessageType.MessageType msgType;
    private Vector<ABaseHeader> headerVector;

    public HttpFormer(AMessageType messageType) {
        httpString = new StringBuilder();
        headerVector = new Vector<ABaseHeader>();
        msgType = messageType.GetMessageType();
        httpString.append(messageType.GetMessage());
    }

    public void AddHeaderField(ABaseHeader header)
    {
        headerVector.add(header);
        httpString.append(header.GetHeaderField());
    }

    public void SetEndOfHeader()
    {
        httpString.append("\r\n");
    }

    public void AddBody(AHtmlFormer htmlFormer)
    {
        if(httpString.lastIndexOf("\r\n\r\n") == -1)
        {
            SetEndOfHeader();
        }

        httpString.append(htmlFormer.GetHtmlString());
    }
}


