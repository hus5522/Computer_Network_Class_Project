package Former;

public class FileContentHTMLFormer {
    LocalFileReader fileReader;
    String content;
    StringBuilder html;
    public FileContentHTMLFormer(String filepath){
        fileReader=new LocalFileReader(filepath);
        content=GetContentsInFile();
    }
    public String GetHtmlString() {
        html.append(
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>FileFinder</title>\n" +
                "</head>\n" +
                "<body>\n" +
                content+
                "</body>\n" +
                "</html>\n");
        return html.toString();
    }
    public  int GetHtmlLength(){
        return html.length();
    }
}
