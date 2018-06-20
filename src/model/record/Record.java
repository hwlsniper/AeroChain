package model.record;

public class Record{
    private String record;

    public Record(String data){
        this.record = data;
    }

    public Record(){}

    @Override
    public String toString() {
        return "Record{" +
                "record='" + record + '\'' +
                '}';
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String data) {
        this.record = data;
    }

    @Override
    public int hashCode(){
        return this.record.hashCode();
    }

    @Override
    public boolean equals(Object record){
        if (!(record instanceof Record)) return false;
        Record x = (Record) record;
        return this.record.equals(x.record);
    }
}
