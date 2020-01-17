package Utilities;

public class Testingutilitiesmethods {
    public static  void main(String[] args){
        String s = Util.getRandomstring(7);
        String s1 = Util.getRandomstring();
        int s3 = Util.getRandomnumber(20,25);
        int s4 = Util.getRandomnumber(12);
      /*  System.out.println(s);
        System.out.println(s1);
        System.out.println(s3);
        System.out.println(s4);*/
        String s5 = Util.getCurrentDateTime();
        System.out.println(s5);
        String s6 = Util.getSimpleDateFormat("DDMMYYYY");
        System.out.println(s6);
    }
}
