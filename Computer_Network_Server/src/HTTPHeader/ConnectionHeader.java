package HTTPHeader;

import java.util.HashMap;

public class ConnectionHeader extends ABaseHeader {

    private static HashMap<ConnectionType, String> connectionTypeTable = null;

    private void initializeConnectionTypeTable()
    {
        if(connectionTypeTable == null)
        {
            connectionTypeTable = new HashMap<>();
            connectionTypeTable.put(ConnectionType.keep_alive, "keep-alive");
            connectionTypeTable.put(ConnectionType.close, "close");
        }
    }

    public enum ConnectionType
    {
        keep_alive, close
    }

    private StringBuilder field;

    public ConnectionHeader()
    {
        initializeConnectionTypeTable();
        field = new StringBuilder();
    }

    public void AddField(ConnectionType type, int value)
    {
        if(field.length() != 0) {
            field.append(",");
        }
        field.append(connectionTypeTable.get(type) + "=" + value);
    }


    @Override
    public Header GetHeaderType() {
        return Header.Connection;
    }

    @Override
    public String GetHeaderField() {
        return GetHeaderTypeInString(Header.Connection) + ": " + field + "\r\n";
    }
}
