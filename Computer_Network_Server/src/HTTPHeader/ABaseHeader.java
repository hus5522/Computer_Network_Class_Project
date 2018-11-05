package HTTPHeader;

import java.util.HashMap;

public abstract class ABaseHeader {
    private static HashMap<Header, String> headerTable = null;

    public enum Header
    {
        Last_Modified(0), ETag(1) , Accept_Ranges(2), Content_Length(3),
        Keep_Alive(4), Connection(5), Content_Type(6), Accept(7), Accept_Language(8);

        private int headerCode;
        Header(int header)
        {
            headerCode = header;
        }
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
            headerTable.put(Header.Accept, "Accept");
            headerTable.put(Header.Accept_Language, "Accept-Language");
        }
    }

    public String GetHeaderTypeInString(Header header)
    {
        initializeHeaderTable();
        return headerTable.get(header);
    }

    public abstract Header GetHeaderType();
    public abstract String GetHeaderField();
}
