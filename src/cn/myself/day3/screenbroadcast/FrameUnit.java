package cn.myself.day3.screenbroadcast;

/**切割屏幕的帧单元
 * Created by WY on 2017/11/14.
 */
public class FrameUnit {
    //时间戳，总个数，当前序号，数据长度，数据内容
    private long frameid;
    private int unitCont;
    private int unitNo;
    private int dataLen;
    private byte[] data;

    public long getFrameid() {
        return frameid;
    }

    public void setFrameid(long frameid) {
        this.frameid = frameid;
    }

    public int getUnitCont() {
        return unitCont;
    }

    public void setUnitCont(int unitCont) {
        this.unitCont = unitCont;
    }

    public int getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(int unitNo) {
        this.unitNo = unitNo;
    }

    public int getDataLen() {
        return dataLen;
    }

    public void setDataLen(int dataLen) {
        this.dataLen = dataLen;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
