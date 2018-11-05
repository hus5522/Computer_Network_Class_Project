package HTTPHeader;

public abstract class AMessageType {
    public enum MessageType
    {
        Request, Response
    }

    public abstract MessageType GetMessageType();
    public abstract String toString();
}
