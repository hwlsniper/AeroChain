package model.block;

import model.record.Record;

import java.util.Date;
import java.util.List;

public class Block {
    private String merkleRoot;

    private String prevHash;

    private int primary;

    private int view;

    private Date timeStamp;

    private int index;

    private List<Record> data;

    public Block(String merkleRoot, String prevHash, int primary, int view, Date timeStamp, List<Record> data , int index) {
        this.merkleRoot = merkleRoot;
        this.prevHash = prevHash;
        this.primary = primary;
        this.view = view;
        this.timeStamp = timeStamp;
        this.data = data;
        this.index = index;
    }

    public Block(){}

    @Override
    public String toString() {
        return "Block{" +
                "merkleRoot='" + merkleRoot + '\'' +
                ", prevHash='" + prevHash + '\'' +
                ", primary=" + primary +
                ", timeStamp=" + timeStamp +
                ", data=" + data +
                '}';
    }

    public int getIndex() {
        return index;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public int getPrimary() {
        return primary;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public int getView() {
        return view;
    }

    public List<Record> getData() {
        return data;
    }

    public void setData(List<Record> data) {
        this.data = data;
    }
}
