import java.util.HashMap;

public abstract class ABaseRequestHeader {

    private static HashMap<Header, String> headerTable = null;

    public enum Header
    {
        Accept, Accept_Language, Authorization, Keep_Alive, Connection
    }

    private void initializeHeaderTable()
    {
        if(headerTable == null) {
            headerTable = new HashMap<Header, String>();
            headerTable.put(Header.Keep_Alive, "Keep-Alive");
            headerTable.put(Header.Connection, "Connection");
            headerTable.put(Header.Accept, "Accept");
            headerTable.put(Header.Accept_Language, "Accept-Language");
            headerTable.put(Header.Authorization, "Authorization");
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
