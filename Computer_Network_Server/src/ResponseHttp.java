import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ResponseHttp extends AMessageType {

    private static HashMap<StatusCode, String> statusPhrase = null;

    private final String serverName = "Apache/2.4.6 (CentOS) OpenSSL/1.0.2k-fips PHP/5.4.16 mod_perl/2.0.10 Perl/v5.16.3";

    public enum StatusCode
    {
        Switching_Protocol(101),
        OK(200),
        Not_Modified(304),
        Bad_Request(400), Unauthorized(401), Not_Found(404);

        private int code;
        StatusCode(int code)
        {
            this.code = code;
        }
    }

    private StringBuilder responseMessage;
    private StatusCode code;

    public ResponseHttp(StatusCode code)
    {
        initializeStatusPhrase();
        this.code = code;
        responseMessage = new StringBuilder();
        responseMessage.append("HTTP/1.1 ");
        responseMessage.append(this.code.code);
        responseMessage.append(" ");
        responseMessage.append(statusPhrase.get(code));
        responseMessage.append("\r\n");

        responseMessage.append("Date: ");
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", new Locale("en", "US"));
        String dateString = dateFormat.format(today);
        responseMessage.append(dateString);
        responseMessage.append(" GMT");
        responseMessage.append("\r\n");
        responseMessage.append("Server: ");
        responseMessage.append(serverName);
        responseMessage.append("\r\n");
    }

    private void initializeStatusPhrase()
    {
        if(statusPhrase == null) {
            statusPhrase = new HashMap<StatusCode, String>();
            statusPhrase.put(StatusCode.Switching_Protocol, "Switching Protocol");
            statusPhrase.put(StatusCode.OK, "OK");
            statusPhrase.put(StatusCode.Not_Modified, "Not Modified");
            statusPhrase.put(StatusCode.Bad_Request, "Bad Request");
            statusPhrase.put(StatusCode.Unauthorized, "Unauthorized");
            statusPhrase.put(StatusCode.Not_Found, "Not Found");
        }
    }

    @Override
    public MessageType GetMessageType()
    {
        return MessageType.Response;
    }

    @Override
    public String toString() {
        return responseMessage.toString();
    }
}
