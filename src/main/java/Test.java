import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Test {
    @JsonProperty("testName")
    private String testName;

    @JsonProperty("expectedResult")
    private String expectedResult;

    @JsonProperty("params")
    private ArrayList<Integer> params;

    @JsonProperty("result")
    private boolean result;

    @JsonCreator
    Test(@JsonProperty("testName") String testName, @JsonProperty("expectedResult") String expectedResult,
            @JsonProperty("params") ArrayList<Integer> params) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
        this.result = false;
    }

    Test(String testName, String expectedResult, ArrayList<Integer> params, boolean result) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
        this.result = result;
    }

    String getTestName() {
        return testName;
    }

    String getExpectedResult() {
        return expectedResult;
    }

    ArrayList<Integer> getParams() {
        return params;
    }
}
