package Former;

import Control.CloudServerManager;
import Helper.LocalFileReader;

import java.util.ArrayList;

public class FileListHTMLFormer {

    LocalFileReader fileReader;
    ArrayList<String> fileName;
    String filepath;
    StringBuilder html;
    String prevPath;

    public FileListHTMLFormer(String filepath) {
        this.filepath = filepath;
        int prevStartIndex = filepath.lastIndexOf("/");
        if(prevStartIndex != -1) {
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
        html.append(
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<meta charset=\"utf-8\">\n" +
                        "<title>FileFinder</title>\n" +
                        "</head>\n" +

                        "<body>\n" +
                        "<fieldset>\n" +
                        "<legend>File List:</legend>\n" +
                        "<ul>\n");
        for (String t : fileName) {

            html.append("<li>" + t + "</li>        <form action=\"" + prevPath + "/" + t + "\" charset=\"utf-8\" method = \"POST\">\n" +
                    "<button type=\"submit\">Open</button></form>\n");
        }
        html.append(
                "</ul>\n" +
                        "</fieldset>\n" +
                        "</body>\n" +
                        "</html>\n");
        return html.toString();
    }

    public int GetHtmlLength() {
        return html.length();
    }
}