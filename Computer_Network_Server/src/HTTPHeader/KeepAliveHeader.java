package HTTPHeader;

import java.util.HashMap;

public class KeepAliveHeader extends ABaseHeader {

    private int timeout, max;

    public KeepAliveHeader(int timeoutValue, int maxValue)
    {
        timeout = timeoutValue;
        max = maxValue;
    }


    @Override
    public Header GetHeaderType() {
        return Header.Keep_Alive;
    }

    @Override
    public String toString() {
        return GetHeaderTypeInString(Header.Keep_Alive) + ": timeout=" + timeout + ",max=" + max + "\r\n";
    }
}
