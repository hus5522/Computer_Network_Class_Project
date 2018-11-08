package Former;

import Control.CloudServerManager;
import Helper.LocalFileReader;
import java.util.Base64;


public class FileContentHTMLFormer {
    LocalFileReader fileReader;
    byte[] content;
    StringBuilder html;
    String extension;
    String binaryString;
    String filepath;

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
        html.append(
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <title>FileFinder</title>\n" +
                        "</head>\n" +
                        "<body>\n" +

                        "<a href=\"data:file/" + extension + ";base64," + binaryString + "\" download=\"" + filepath + "\">" + "Download!" + "</a>" +
                        "</body>\n" +
                        "</html>\n");
        return html.toString();
    }

    public int GetHtmlLength() {
        return html.length();
    }
}
