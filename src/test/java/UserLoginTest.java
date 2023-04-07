import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.time.Duration;

public class UserLoginTest {

    @Parameters ({"userName", "password1"})
    @Test
    public void userSuccessfulLoginWhenEmailVerifiedTest(String userName, String password1 ) {
        assertTrue(User.userLogin(new User("Elenta_geras", "HRKtAi")));
    }

    @Test
    public void wrongPasswordTest() {
        assertFalse(User.userLogin(new User("Elenta_lina1254", "elenta123456")));
    }

    @Test
    public void wrongUserNameTest() {
        assertFalse(User.userLogin(new User("Elenta_geras123", "HRKtAi")));
    }

    @Test
    public void emailResendTest() {
        assertTrue(User.userRemindPassword(new User("lina.tauraite@gmail.com")));
    }

    @Test
    public void emailResendFailTest() {
        assertFalse(User.userRemindPassword(new User("lina.tauraite@email.com")));
    }

    @BeforeClass

    public void beforeClass() {
        User.driver = new ChromeDriver();
        User.driver.manage().window().maximize();
        User.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        User.driver.get("https://elenta.lt/prisijungti");
        User.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();

    }
}
