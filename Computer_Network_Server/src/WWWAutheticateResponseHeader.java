public class WWWAutheticateResponseHeader extends ABaseResponseHeader{

    private StringBuilder headerField;

    public WWWAutheticateResponseHeader(AuthorizationRequestHeader.EncodingType encodingType)
    {
        headerField = new StringBuilder();
        headerField.append(GetHeaderTypeInString(Header.WWW_Authenticate));
        headerField.append(": ");
        headerField.append(AuthorizationRequestHeader.GetEncodingTypeString(encodingType));
        headerField.append(" realm=\"Welcome to My Cloud Service\"\r\n");
    }

    @Override
    public Header GetHeaderType() {
        return Header.WWW_Authenticate;
    }

    @Override
    public String toString() {
        return headerField.toString();
    }
}
