package Basetest_Package;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;

public class Checkpoint {
    public static HashMap<String, String> resultmap  = new HashMap<String, String>();
    private static String PASS = "PASS";
    private static String FAIL = "FAIL";

    public static  void clearhash(){
        System.out.print("Clearing Results Hash Map");
        resultmap.clear();

    }
    private static  void setStatus(String mapkey, String status){
        resultmap.put(mapkey,status);
        System.out.println("mapkey is :> " + resultmap.get(mapkey));
    }
    public static  void  mark(String testname, boolean result, String resultMessage){
        testname = testname.toLowerCase();
        String mapKey = testname + "." + resultMessage;
        try {
            if (result) {
                setStatus(mapKey, PASS);
            } else {
                setStatus(mapKey, FAIL);
            }
        } catch (Exception e) {
            System.out.println("Exception Occurred...");
            setStatus(mapKey, FAIL);
            e.printStackTrace();
        }
    }

    public static void markFinal(String testName, boolean result, String resultMessage) {
        testName = testName.toLowerCase();
        String mapKey = testName + "." + resultMessage;
        try {
            if (result) {
                setStatus(mapKey, PASS);
            } else {
                setStatus(mapKey, FAIL);
            }
        } catch (Exception e) {
            System.out.println("Exception Occurred...");
            setStatus(mapKey, FAIL);
            e.printStackTrace();
        }

        ArrayList<String> resultList = new ArrayList<String>();

        for (String key: resultmap.keySet()) {
            resultList.add(resultmap.get(key));
        }

        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.contains(FAIL)) {
                System.out.println("Test Method Failed");
                Assert.assertTrue(false);
            } else {
                System.out.println("Test Method Successful");
                Assert.assertTrue(true);
            }
        }
    }


}

