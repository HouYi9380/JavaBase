package cn.myself.day1;

/**
 * Created by WY on 2017/11/11.
 */
public class FileBean {
    private  String fileName;
    private  byte[] fileContent;

    public FileBean(String fname, byte[] fcontBytes) {
        this.fileName =fname;
        this.fileContent =fcontBytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
