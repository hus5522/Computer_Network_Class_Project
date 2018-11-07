package HTTPHeader;

import java.util.HashMap;

public abstract class ABaseResponseHeader {
    private static HashMap<Header, String> headerTable = null;

    public enum Header
    {
        Last_Modified, ETag , Accept_Ranges, Content_Length,
        Keep_Alive, Connection, Content_Type, WWW_Authenticate
    }

    private void initializeHeaderTable()
    {
        if(headerTable == null) {
            headerTable = new HashMap<Header, String>();
            headerTable.put(Header.Last_Modified, "Last-Modified");
            headerTable.put(Header.ETag, "ETag");
            headerTable.put(Header.Accept_Ranges, "Accept-Ranges");
            headerTable.put(Header.Content_Length, "Content-Length");
            headerTable.put(Header.Keep_Alive, "Keep-Alive");
            headerTable.put(Header.Connection, "Connection");
            headerTable.put(Header.Content_Type, "Content-Type");
            headerTable.put(Header.WWW_Authenticate, "WWW-Authenticate");
        }
    }

    public String GetHeaderTypeInString(Header header)
    {
        initializeHeaderTable();
        return headerTable.get(header);
    }

    public abstract Header GetHeaderType();
    public abstract String toString();
}
