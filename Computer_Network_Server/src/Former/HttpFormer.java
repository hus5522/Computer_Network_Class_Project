package Former;

import HTTPHeader.AMessageType;

public class HttpFormer {
    // http message's end of line
    public final String END_LINE = "\r\n";

    private StringBuilder httpString;

    public HttpFormer(String httpString) {
        this.httpString = new StringBuilder();
        this.httpString.append(httpString);
    }

    public HttpFormer(AMessageType messageType) {
        httpString = new StringBuilder();
        httpString.append(messageType.GetMessage());
    }


}


