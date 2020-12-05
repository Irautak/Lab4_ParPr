import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class TestGroupMsg {
    @JsonProperty("packageID")
    private int packageID;
    @JsonProperty("jsScript")
    private String jsScript;
    @JsonProperty("functionName")
    private String functionName;
    @JsonProperty("tests")
    private ArrayList<Test> tests;

    @JsonCreator
    public TestGroupMsg(@JsonProperty("packageID") int packageID, @JsonProperty("jsScript") String jsScript,
                        @JsonProperty("functionName") String functionName,
                        @JsonProperty("tests") ArrayList<Test> tests) {
        this.packageID = packageID;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public int getPackageID() {
        return packageID;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getJsScript() {
        return jsScript;
    }
}
