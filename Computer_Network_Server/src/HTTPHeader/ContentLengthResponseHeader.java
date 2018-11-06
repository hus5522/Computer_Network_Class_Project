package HTTPHeader;

import Former.AHtmlFormer;

public class ContentLengthResponseHeader extends ABaseResponseHeader {

    int contentLength;

    public ContentLengthResponseHeader(AHtmlFormer htmlFormer)
    {
        contentLength = htmlFormer.GetHtmlLength();
    }

    @Override
    public Header GetHeaderType() {
        return Header.Content_Length;
    }

    @Override
    public String toString() {
        return GetHeaderTypeInString(Header.Content_Length) + ": " + contentLength + "\r\n";
    }
}
