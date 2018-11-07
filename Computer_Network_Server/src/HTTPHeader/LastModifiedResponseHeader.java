package HTTPHeader;

import Helper.LocalFileReader;

public class LastModifiedResponseHeader extends ABaseResponseHeader {

    private String field;

    public LastModifiedResponseHeader(LocalFileReader fileReader)
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
