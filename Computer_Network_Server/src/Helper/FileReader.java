package Helper;

import java.io.File;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

public class FileReader {

    private String filepath;
    private ArrayList<String> date;

    public FileReader(String path) {
        this.filepath = path;
        this.date = new ArrayList<String>();
    }

    public int GetComponentNumber() {
        return date.size();
    }

    public String GetAllContents() {
        return null;
    }

    public ArrayList<String> GetLastModifiedDate()
    {
        File directory = new File(filepath);
        String filedate;

        for(File file : directory.listFiles()) {
            LocalDateTime lastModified = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()),TimeZone.getDefault().toZoneId());
            filedate = lastModified.toString().replaceAll("T", " ");
            filedate = filedate.substring(0, filedate.length() - 4);
            date.add(filedate);
            /*
            String dir = file.isDirectory() ? "<DIR>" : "";
            long fileSize = file.length();
            String size = fileSize == 0l ? "" : NumberFormat.getNumberInstance(Locale.KOREA).format(fileSize);
            String name = file.getName();
            */
        }

        return date;
    }

    public int GetFileLength()
    {
        return 0;
    }
}
