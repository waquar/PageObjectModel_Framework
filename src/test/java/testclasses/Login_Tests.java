package testclasses;


import Basetest_Package.BaseClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login_Tests extends BaseClass {

    @BeforeClass
    public void setUp(){

    }

    @AfterMethod
    public void afterthemethod(){
        if(navbar.checkUserlogin_status()){
            navbar.logout();
            navbar.log_in();
        }
    }

    @Test(enabled =  false)
    public  void testlogin_positive(){
        navbar = login.signin_testdata("test@email.com", "abcabc");
        boolean result = navbar.checkUserlogin_status();
        Assert.assertTrue(result);
    }
    @Test
    public void testlogincheck(){
        navbar = login.signin_testdata("test@email.com", "abcabc");
        boolean checkstatusoflogin = navbar.verifytheheader();
        Assert.assertTrue(checkstatusoflogin);
    }

    @Test(enabled = false)
    public void testlogin_negative(){
        navbar = login.signin_testdata("fail@email.com", "abcabc");
        boolean result = navbar.checkUserlogin_status();
        Assert.assertFalse(result);

    }

}
