package Former;

public class FileListHTMLFormer {

    LocalFileReader fileReader;
    ArrayList<String> fileName;
    String filepath;
    StringBuilder html;
    public FileListHTMLFormer(String filepath){
        this.filepath=filepath;
        fileReader=new LocalFileReader(path);
        fileName=fileReader.GetAllContentsList();
    }

    public String GetHtmlString(){
        html=new StringBuilder();
        html.append(
                "<!DOCTYPE html>\n"+
                        "<html>\n"+
                        "<head>\n"+
                        "<meta charset=\"UTF-8\">\n"+
                        "<title>FileFinder</title>\n"+
                        "</head>\n"+

                        "<body>\n"+
                        "<fieldset>\n"+
                        "<legend>File List:</legend>\n"+
                        "<ul>\n");
        for(String t:fileName) {
            html.append("<li>" + t+ "</li>        <form action=\"" + filepath + "\\" + t+ "\" method = \"post\">\n" +
                    "<button type=\"submit\">Open</button></form>\n");
        }
        html.append(
                "</ul>\n"+
                        "</fieldset>\n"+
                        "</body>\n"+
                        "</html>\n");
        return html.toString();
    }
    public  int GetHtmlLength(){
        return html.length();
    }
}package Former;

public class FileListHTMLFormer {

    LocalFileReader fileReader;
    ArrayList<String> fileName;
    String filepath;
    StringBuilder html;
    public FileListHTMLFormer(String filepath){
        this.filepath=filepath;
        fileReader=new LocalFileReader(path);
        fileName=fileReader.GetAllContentsList();
    }

    public String GetHtmlString(){
        html=new StringBuilder();
        html.append(
                "<!DOCTYPE html>\n"+
                        "<html>\n"+
                        "<head>\n"+
                        "<meta charset=\"UTF-8\">\n"+
                        "<title>FileFinder</title>\n"+
                        "</head>\n"+

                        "<body>\n"+
                        "<fieldset>\n"+
                        "<legend>File List:</legend>\n"+
                        "<ul>\n");
        for(String t:fileName) {
            html.append("<li>" + t+ "</li><form action=\"" + filepath + "\\" + t+ "\" method = \"post\">\n" +
                    "<button type=\"submit\">Open</button></form>\n");
        }
        html.append(
                "</ul>\n"+
                        "</fieldset>\n"+
                        "</body>\n"+
                        "</html>\n");
        return html.toString();
    }
    public  int GetHtmlLength(){
        return html.length();
    }
}