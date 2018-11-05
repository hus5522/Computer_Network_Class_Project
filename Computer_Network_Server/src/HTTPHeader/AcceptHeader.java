package HTTPHeader;

import java.util.HashMap;
// request header
public class AcceptHeader extends ABaseHeader {

    private static HashMap<MimeType, String> mimeTypeTable = null;

    private void initializeMimeTypeTable() {
        if (mimeTypeTable == null) {
            mimeTypeTable = new HashMap<>();
            mimeTypeTable.put(MimeType.Text_Html, "text/html");
            mimeTypeTable.put(MimeType.application_xhtml_xml, "application/xhtml+xml");
        }
    }

    public enum MimeType {
        Text_Html(0), application_xhtml_xml(1);

        private int mimeCode;

        MimeType(int code) {
            mimeCode = code;
        }
    }

    private StringBuilder field;

    public AcceptHeader()
    {
        initializeMimeTypeTable();
        field = new StringBuilder();
    }

    public void AddField(MimeType type)
    {
        if(field.length() != 0) {
            field.append(",");
        }
        field.append(mimeTypeTable.get(type));
    }

    @Override
    public Header GetHeaderType() {
        return Header.Accept;
    }

    @Override
    public String GetHeaderField() {
        StringBuilder headerField = new StringBuilder();
        headerField.append(GetHeaderTypeInString(Header.Accept));
        headerField.append(": ");
        headerField.append(field);
        headerField.append("\r\n");
        return headerField.toString();
    }
}
