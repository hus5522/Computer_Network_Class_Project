package HTTPHeader;

import Helper.FileReader;

public class LastModifiedResponseHeader extends ABaseResponseHeader {

    private String field;

    public LastModifiedResponseHeader(FileReader fileReader)
    {
        //field = fileReader.GetLastModifiedDate();
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
