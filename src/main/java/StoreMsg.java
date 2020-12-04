import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class StoreMsg {
    @JsonProperty("packageID")
    private int packageID;
    @JsonProperty("tests")
    private ArrayList<Test> tests;

    @JsonCreator
    public StoreMsg(@JsonProperty("packageID") int packageID, @JsonProperty("tests") ArrayList<Test> tests) {
        this.packageID = packageID;
        this.tests = tests;
    }

    public int getPackageID() {
        return packageID;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}
