package HTTPHeader;

import Former.AHtmlFormer;
import Helper.FileReader;

public class ContentLengthHeader extends ABaseHeader {

    int contentLength;

    public ContentLengthHeader(AHtmlFormer htmlFormer)
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
