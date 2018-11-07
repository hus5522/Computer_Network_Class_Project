//package HTTPHeader;
//
//import java.util.HashMap;
//
//public class AcceptLanguageHeader extends ABaseResponseHeader {
//
//    private static HashMap<LanguageType, String> langTable = null;
//
//    private void initializeLanguageTable()
//    {
//        if(langTable == null)
//        {
//            langTable = new HashMap<>();
//            langTable.put(LanguageType.en, "en");
//            langTable.put(LanguageType.en_us, "en-us");
//            langTable.put(LanguageType.ko, "ko");
//            langTable.put(LanguageType.ko_KR, "ko-KR");
//        }
//    }
//
//    public enum LanguageType
//    {
//        en, en_us, ko, ko_KR
//    }
//
//    private StringBuilder field;
//
//    public AcceptLanguageHeader()
//    {
//        initializeLanguageTable();
//        field = new StringBuilder();
//    }
//
//    public void AddField(LanguageType type)
//    {
//        if(field.length() != 0) {
//            field.append(",");
//        }
//        field.append(langTable.get(type));
//    }
//
//    @Override
//    public Header GetHeaderType() {
//        return Header.Accept_Language;
//    }
//
//    @Override
//    public String GetHeaderField() {
//        return GetHeaderTypeInString(Header.Accept_Language) + ": " + field + ";q=0.5\r\n";
//    }
//}
