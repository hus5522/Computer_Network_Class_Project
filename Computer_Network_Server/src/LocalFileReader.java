import java.io.*;
import java.util.ArrayList;

public class LocalFileReader {
    private String filePath;
    private ArrayList<String> fileName;

    public LocalFileReader(String path) {
        this.filePath = path;
        this.fileName = new ArrayList<String>();
    }

    //디렉토리 존재 유무 파악 함수
    public Boolean IsExistedDirectory() {
        File directory = new File(filePath);

        if(directory.listFiles() == null) {
            return false;
        } else {
            return true;
        }
    }

    //폴더 안에 있는 모든 파일 혹은 디렉토리 출력 함수
    public ArrayList<String> GetAllContentsList() {

        File directory = new File(filePath);

        //디렉토리 존재 유무 확인
        if(!IsExistedDirectory()) {
            return null;
        }

        for(File file : directory.listFiles()) {
            //디렉토리라면
            if(file.isDirectory()) {
                fileName.add(file.getName());
            } else {
                fileName.add(file.getName());
            }
        }
        return fileName;
    }

    //특정 파일을 읽어서 문자열로 반환하는 함수
    public byte[] GetContentsInFile() {
        try {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] binaryBytes = new byte[fileInputStream.available()];
            fileInputStream.read(binaryBytes);
            return binaryBytes;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
