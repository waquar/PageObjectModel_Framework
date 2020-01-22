package Utilities;

import com.google.common.collect.Ordering;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Util {
    public static void sleep(long msec, String info){
        if (info != null){
            System.out.println("wait  " + msec + "for" + info);
        }try{
            Thread.sleep(msec);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public static  void sleep(long msec){
        sleep(msec, null);
    }
    public static int getRandomnumber(int min, int max){
        int difference  = max-min;
        return  (int)(min + Math.random() * difference);
    }
    public static int getRandomnumber(int number){
        return getRandomnumber(1, number);
    }
    public static String getRandomstring(int length){
        StringBuilder stringBuilder = new StringBuilder();
        String characters = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghjklmnopqrstuvwxyz0123456789";

        for (int i = 0; i <length; i++){
            int index = (int)(Math.random() * characters.length());
            System.out.println((Math.random() * characters.length()));
            System.out.println(index);
            stringBuilder.append(characters.charAt(index));
        }
        return  stringBuilder.toString();
    }
    public static String getRandomstring(){
        return getRandomstring(10);
    }
    /***
     * Get simple date as string in the specified format
     * @param format MMddyy MMddyyyy
     * @return date as string type
     */
    public static String getSimpleDateFormat(String format){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String formattedDate = formatter.format(date);
        System.out.println("Date with format :: "
                + format + " :: " + formattedDate);
        return formattedDate;
    }

    /***
     * Get simple date time as string
     * @return date time as string type
     */
    public static String getCurrentDateTime(){
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "MM/dd/yyyy HH:mm:ss");
        String date = formatter.format(currentDate.getTime()).replace("/", "_");
        date = date.replace(":", "_");
        System.out.println("Date and Time :: " + date);
        return date;
    }

    /**
     * Checks whether actual String contains expected string and prints both in log
     * @param actualText - actual Text picked up from application under Test
     * @param expText - expected Text to be checked with actual text
     * @return boolean result
     */
    public boolean verifyTextContains(String actualText, String expText) {
        if (actualText.toLowerCase().contains(expText.toLowerCase())){
            System.out.println("Actual Text From Web Application UI   --> : "+ actualText);
            System.out.println("Expected Text From Web Application UI --> : "+ expText);
            System.out.println("### Verification Contains !!!");
            return true;
        }
        else{
            System.out.println("Actual Text From Web Application UI   --> : "+ actualText);
            System.out.println("Expected Text From Web Application UI --> : "+ expText);
            System.out.println("### Verification DOES NOT Contains !!!");
            return false;
        }

    }

    /**
     * Checks whether actual string matches with expected string and prints both in log
     * @param actualText - actual Text picked up from application under Test
     * @param expText - expected Text to be matched with actual text
     * @return
     */
    public static boolean verifyTextMatch(String actualText, String expText) {
        if (actualText.equals(expText)){
            System.out.println("Actual Text From Web Application UI   --> : "+ actualText);
            System.out.println("Expected Text From Web Application UI --> : "+ expText);
            System.out.println("### Verification MATCHED !!!");
            return true;
        }else{
            System.out.println("Actual Text From Web Application UI   --> : "+ actualText);
            System.out.println("Expected Text From Web Application UI --> : "+ expText);
            System.out.println("### Verification DOES NOT MATCH !!!");
            return false;
        }
    }

    /**
     * Verify actual list contains items of the expected list
     *
     * @param actList
     * @param expList
     * @return
     */
    public static Boolean verifyListContains(List<String> actList, List<String> expList) {
        int expListSize = expList.size();
        for (int i = 0; i < expListSize; i++) {
            if (!actList.contains(expList.get(i))) {
                return false;
            }
        }
        System.out.println("Actual List Contains Expected List !!!");
        return true;
    }

    /***
     * Verify actual list matches expected list
     * @param actList
     * @param expList
     * @return
     */
    public static Boolean verifyListMatch(List<String> actList, List<String> expList) {
        boolean found = false;
        int actListSize = actList.size();
        int expListSize = expList.size();
        if (actListSize != expListSize) {
            return false;
        }

        for (int i = 0; i < actListSize; i++) {
            found = false;
            for (int j = 0; j < expListSize; j++) {
                if (verifyTextMatch(actList.get(i), expList.get(j))) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            System.out.println("Actual List Matches Expected List !!!");
            return true;
        }
        else {
            System.out.println("Actual List DOES NOT Match Expected List !!!");
            return false;
        }
    }

    /**
     * Verifies item is present in the list
     * @param actList
     * @param item
     * @return boolean result
     */
    public static Boolean verifyItemPresentInList(List<String> actList, String item){
        int actListSize = actList.size();
        for (int i = 0; i < actListSize; i++) {
            if (!actList.contains(item)) {
                System.out.println("Item is NOT present in List !!!");
                return false;
            }
        }
        System.out.println("Item is present in List !!!");
        return true;
    }

    /**
     * Verify if list is in ascending order
     * @param list
     * @return boolean result
     */
    public static boolean isListAscendingOrder(List<Long> list){
        boolean sorted = Ordering.natural().isOrdered(list);
        return sorted;
    }
    public static String getScreenshotName(String methodname , String Browsername){
        String localdatetime = getCurrentDateTime();
        StringBuilder name =    new StringBuilder().append(Browsername).append("-")
                .append(methodname).append("-").
                        append(localdatetime).append(".png");
        System.out.println(name.toString());
        return  name.toString();
    }
}


