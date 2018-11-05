package HTTPHeader;

import Helper.FileReader;

public class ContentLengthHeader extends ABaseHeader {

    int contentLength;

    public ContentLengthHeader(FileReader file)
    {
        contentLength = file.GetFileLength();
    }

    @Override
    public Header GetHeaderType() {
        return Header.Content_Length;
    }

    @Override
    public String GetHeaderField() {
        return GetHeaderTypeInString(Header.Content_Length) + ": " + contentLength + "\r\n";
    }
}
