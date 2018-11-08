import java.util.HashMap;

public class AcceptRangeResponseHeader extends ABaseResponseHeader {

    private static HashMap<RangeType, String> rangeTypeTable = null;
    private void initializeRangeTypeTable()
    {
        if(rangeTypeTable == null)
        {
            rangeTypeTable = new HashMap<>();
            rangeTypeTable.put(RangeType.bytes, "bytes");
        }
    }

    public enum RangeType
    {
        bytes
    }

    private String field;

    public AcceptRangeResponseHeader(RangeType type)
    {
        initializeRangeTypeTable();
        field = rangeTypeTable.get(type);
    }

    @Override
    public Header GetHeaderType() {
        return Header.Accept_Ranges;
    }

    @Override
    public String toString() {
        return GetHeaderTypeInString(Header.Accept_Ranges) + ": " + rangeTypeTable.get(RangeType.bytes) + "\r\n";
    }
}
