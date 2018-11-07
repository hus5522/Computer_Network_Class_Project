package HTTPHeader;


import java.util.Base64;
import java.util.HashMap;

public class AuthorizationRequestHeader extends ABaseRequestHeader {

    private static HashMap<EncodingType, String> encodingTypeTable = null;

    public static String GetEncodingTypeString(EncodingType type)
    {
        initializeEncodingTypeTable();
        return encodingTypeTable.get(type);
    }

    private static void initializeEncodingTypeTable()
    {
        if(encodingTypeTable == null)
        {
            encodingTypeTable = new HashMap<>();
            encodingTypeTable.put(EncodingType.Basic, "Basic");
        }
    }


    public enum EncodingType
    {
        Basic
    }

    private EncodingType encodingType;
    private String userID;
    private String userPassword;
    private String headerField;

    public AuthorizationRequestHeader(String httpString)
    {
        initializeEncodingTypeTable();
        int startIndex = httpString.indexOf(GetHeaderTypeInString(Header.Authorization));

        if(startIndex != -1)
        {
            int endIndex = httpString.indexOf("\r\n", startIndex);
            endIndex += 2;
            headerField = httpString.substring(startIndex, endIndex);
            for(HashMap.Entry<EncodingType, String> e : encodingTypeTable.entrySet())
            {
                startIndex = headerField.indexOf(encodingTypeTable.get(e.getKey()));
                if(startIndex != -1)
                {
                    encodingType = e.getKey();
                    break;
                }
            }
            if(startIndex == -1)
            {
                System.out.println("authorization err");
                return;
            }

            if(encodingType == EncodingType.Basic)
            {
                startIndex += 6;
                endIndex = headerField.length();
                endIndex -= 2;
                String encodedUserInfo = headerField.substring(startIndex, endIndex);
                byte[] encodedBytes = encodedUserInfo.getBytes();
                Base64.Decoder decoder = Base64.getDecoder();
                byte[] decodedBytes = decoder.decode(encodedBytes);
                String decodedUserInfo = new String(decodedBytes);
                startIndex = 0;
                endIndex = decodedUserInfo.indexOf(":");
                userID = decodedUserInfo.substring(startIndex, endIndex);
                userPassword = decodedUserInfo.substring(endIndex + 1, decodedUserInfo.length());
            }
        }
        else
        {
            userID = "";
            userPassword = "";
        }
    }

    public String GetUserID()
    {
        return userID;
    }

    public String GetUserPassword()
    {
        return userPassword;
    }

    @Override
    public Header GetHeaderType() {
        return Header.Authorization;
    }

    @Override
    public String toString() {
        return headerField;
    }
}
