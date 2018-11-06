package HTTPHeader;

import Helper.FileReader;

public class LastModifiedHeader extends ABaseHeader {

    private String field;

    public LastModifiedHeader(FileReader fileReader)
    {
        field = fileReader.GetLastModifiedDate();
    }

    @Override
    public String toString() {
        return GetHeaderTypeInString(Header.Last_Modified) + ": " + field + "\r\n";
    }

    @Override
    public Header GetHeaderType() {
        return Header.Last_Modified;
    }
}
