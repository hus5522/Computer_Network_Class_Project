package HTTPHeader;

import java.util.HashMap;

public class KeepAliveHeader extends ABaseHeader {

    private HashMap<KeepAliveType, String> keepAliveTable = null;

    private void initializeKeepAliveTable()
    {
        if(keepAliveTable == null)
        {
            keepAliveTable = new HashMap<>();
            keepAliveTable.put(KeepAliveType.timeout, "timeout");
            keepAliveTable.put(KeepAliveType.max, "max");
        }
    }

    public enum KeepAliveType
    {
        timeout, max
    }

    private StringBuilder field;

    public KeepAliveHeader()
    {
        initializeKeepAliveTable();
        field = new StringBuilder();
    }

    public void AddField(KeepAliveType type, int value)
    {
        if(field.length() != 0) {
            field.append(",");
        }
        field.append(keepAliveTable.get(type) + "=" + value);
    }


    @Override
    public Header GetHeaderType() {
        return Header.Keep_Alive;
    }

    @Override
    public String GetHeaderField() {
        return GetHeaderTypeInString(Header.Keep_Alive) + ": " + field + "\r\n";
    }
}
