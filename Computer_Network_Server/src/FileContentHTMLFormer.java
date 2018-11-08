import java.util.Base64;


public class FileContentHTMLFormer {
    private LocalFileReader fileReader;
    private byte[] content;
    private StringBuilder html;
    private String extension;
    private String binaryString;
    private String filepath;

    public FileContentHTMLFormer(String filepath) {
        this.filepath = filepath;
        fileReader = new LocalFileReader(CloudServerManager.getInstance().GetRootFolderPath() + "/" + filepath);
        int extensionIndex = filepath.indexOf(".") + 1;
        extension = filepath.substring(extensionIndex);
        html = new StringBuilder();
        content = fileReader.GetContentsInFile();
        Base64.Encoder encoder = Base64.getEncoder();
        binaryString = encoder.encodeToString(content);
    }

    public String GetHtmlString() {
        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"utf-8\">\n");
        html.append("    <title>FileFinder</title>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("<a href=\"data:file/");
        html.append(extension);
        html.append(";base64,");
        html.append(binaryString);
        html.append("\" download=\"");
        html.append(filepath);
        html.append("\">");
        html.append("Download!");
        html.append("</a>");
        html.append("</body>\n");
        html.append("</html>\n");
        return html.toString();
    }

    public int GetHtmlLength() {
        return html.length();
    }
}
