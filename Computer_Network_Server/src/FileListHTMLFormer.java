import java.util.ArrayList;

public class FileListHTMLFormer {

    private LocalFileReader fileReader;
    private ArrayList<String> fileName;
    private String filepath;
    private StringBuilder html;
    private String prevPath;

    public FileListHTMLFormer(String filepath) {
        this.filepath = filepath;
        int prevStartIndex = filepath.lastIndexOf("/");
        if (prevStartIndex != -1) {
            prevStartIndex += 1;
            prevPath = filepath.substring(prevStartIndex);
        } else {
            prevPath = filepath;
        }
        fileReader = new LocalFileReader(CloudServerManager.getInstance().GetRootFolderPath() + "/" + filepath);
        fileName = fileReader.GetAllContentsList();
    }

    public String GetHtmlString() {
        html = new StringBuilder();
        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n");
        html.append("<head>\n");
        html.append("<meta charset=\"utf-8\">\n");
        html.append("<title>FileFinder</title>\n");
        html.append("</head>\n");

        html.append("<body>\n");
        html.append("<fieldset>\n");
        html.append("<legend>File List:</legend>\n");
        html.append("<ul>\n");
        for (String t : fileName) {
            html.append("<li>");
            html.append(t);
            html.append("</li>\n");
            html.append("<form action=\"");
            html.append(prevPath);
            html.append("/");
            html.append(t);
            html.append("\" method = \"POST\">\n");
            html.append("<button type=\"submit\">Open</button></form>\n");
        }
        html.append("</ul>\n");
        html.append("</fieldset>\n");
        html.append("</body>\n");
        html.append("</html>\n");
        return html.toString();
    }

    public int GetHtmlLength() {
        return html.length();
    }
}