package com.saucelabs.Tests.DBTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import utility.Constant;
import utility.ExcelUtils;

/**
 * Created by ykadytc on 11.11.2014.
 */
public class DeleteProblemTestFactory {
    @Factory(dataProvider = "xlsDeleteProblemTestData")
    public Object[] createInstances(String latitudeString, String longitudeString, String problemTitle,
                                    String problemType, String problemDescription, String problemSolution,
                                    String imageURLsString, String imageCommentsString,
                                    String adminEmail, String adminPassword,
                                    String newUserFirstName, String newUserLastName,
                                    String newUserEmail, String newUserPassword,
                                    String userCommentsString) {
        return new Object[] {new DeleteProblemTest(latitudeString, longitudeString, problemTitle,
                problemType, problemDescription, problemSolution, imageURLsString, imageCommentsString,
                adminEmail, adminPassword)};
    }

    @DataProvider(name = "xlsDeleteProblemTestData", parallel = false)
    public static Object[][] data() throws Exception{
        return ExcelUtils.getTableArray(Constant.Path_DeleteProblemTestData +
                Constant.File_DeleteProblemTestData, "Sheet1");
    }
}
