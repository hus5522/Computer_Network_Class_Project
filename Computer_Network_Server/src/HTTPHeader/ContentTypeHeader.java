package HTTPHeader;

import java.util.HashMap;

public class ContentTypeHeader extends ABaseHeader {

    private static HashMap<ContentType, String> contentTypeTable = null;
    private static HashMap<CharType, String> charTypeTable = null;

    private void initializeContentTypeAndCharType()
    {
        if(contentTypeTable == null || charTypeTable == null)
        {
            contentTypeTable = new HashMap<>();
            charTypeTable = new HashMap<>();

            contentTypeTable.put(ContentType.text_html, "text/html");

            charTypeTable.put(CharType.utf_8, "UTF-8");
        }
    }

    public enum ContentType
    {
        text_html
    }

    public enum CharType
    {
        utf_8
    }

    private StringBuilder field;

    public ContentTypeHeader(ContentType content, CharType charSet)
    {
        initializeContentTypeAndCharType();
        field = new StringBuilder();
        field.append(contentTypeTable.get(content));
        field.append(";");
        field.append("charset=");
        field.append(charTypeTable.get(charSet));
    }

    @Override
    public Header GetHeaderType() {
        return Header.Content_Type;
    }

    @Override
    public String toString() {
        return GetHeaderTypeInString(Header.Content_Type) + ": " + field + "\r\n";
    }
}
