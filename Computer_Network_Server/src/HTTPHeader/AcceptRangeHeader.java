package HTTPHeader;

import org.w3c.dom.ranges.Range;

import java.util.HashMap;

public class AcceptRangeHeader extends ABaseHeader{

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

    public AcceptRangeHeader(RangeType type)
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
