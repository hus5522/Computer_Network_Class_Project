package Helper;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
                fileName.add(file.getName() + " <- 디렉토리");
            } else {
                fileName.add(file.getName());
            }
        }
        return fileName;
    }

    //특정 파일의 수정시간 출력 함수
    public String GetLastModifiedDate(String fileName)
    {
        File directory = new File(filePath);

        //디렉토리 존재 유무 확인
        if(!IsExistedDirectory()) {
            return null;
        }

        for(File file : directory.listFiles()) {

            //찾고자 하는 파일 이름과 디렉토리 내부의 파일 이름이 같으면 그 파일의 수정일자를 반환
            if(file.getName().equals(fileName)) {
                LocalDateTime lastModified = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault());
                String filedate = lastModified.toString().replaceAll("T", " ");
                filedate = filedate.substring(0, filedate.length() - 4);
                return filedate;
            }
        }

        return null;
    }

    //특정 파일의 바이트 출력 함수
    public long GetFileLength(String fileName)
    {
        File directory = new File(filePath);
        long fileSize;

        //디렉토리 존재 유무 확인
        if(!IsExistedDirectory()) {
            return 0l;
        }

        for(File file : directory.listFiles()) {

            //찾고자 하는 파일 이름과 디렉토리 내부의 파일 이름이 같으면 그 파일의 사이즈를 반환
            if(file.getName().equals(fileName)) {
                fileSize = file.length();
                return fileSize;
            }
        }
        //해당 파일이 없으면 -1 리턴
        return -1;
    }

    //특정 파일을 읽어서 문자열로 반환하는 함수
    public String GetContentsInFile(String fileName) throws IOException {
        try {
            File file = new File(filePath+fileName);

            String contents = "";
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);

            while(true) {
                String line = br.readLine();
                if(line == null) break;
                contents += line;
            }

            br.close();
            return contents;

        } catch (Exception e) {
            return "No "+ fileName;
        }

    }
}
