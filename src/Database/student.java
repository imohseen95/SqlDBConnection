package Database;

public class student {

    String stID;
    String stName;
    String stDOB;

    public student(){}

    public student(String stID, String stName, String stDOB) {
        this.stID = stID;
        this.stName = stName;
        this.stDOB = stDOB;
    }

    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStDOB() {
        return stDOB;
    }

    public void setStDOB(String stDOB) {
        this.stDOB = stDOB;
    }
}
