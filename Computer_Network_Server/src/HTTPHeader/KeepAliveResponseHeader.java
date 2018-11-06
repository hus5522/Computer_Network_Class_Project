package HTTPHeader;

public class KeepAliveResponseHeader extends ABaseResponseHeader {

    private int timeout, max;

    public KeepAliveResponseHeader(int timeoutValue, int maxValue)
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
