import java.util.HashMap;

public class ConnectionResponseHeader extends ABaseResponseHeader {

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
    private ConnectionType connectionType;
    public ConnectionResponseHeader(ConnectionType type)
    {
        initializeConnectionTypeTable();
        field = new StringBuilder();
        field.append(connectionTypeTable.get(type));
    }


    @Override
    public Header GetHeaderType() {
        return Header.Connection;
    }

    @Override
    public String toString() {
        return GetHeaderTypeInString(Header.Connection) + ": " + field + "\r\n";
    }
}
