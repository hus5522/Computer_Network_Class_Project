package HTTPHeader;

import Former.AHtmlFormer;

public class ContentLengthResponseHeader extends ABaseResponseHeader {

    int contentLength = 0;

    public ContentLengthResponseHeader(AHtmlFormer htmlFormer)
    {
        if(htmlFormer != null) {
            contentLength = htmlFormer.GetHtmlLength();
        }
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
