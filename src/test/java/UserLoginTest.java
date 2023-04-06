import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.time.Duration;

public class UserLoginTest {

    @Test
    public void userSuccessfulLoginWhenEmailVerifiedTest() {
        assertTrue(User.userLogin(new User("Elenta_lina1254", "elenta")));
    }

    @Test
    public void userLoginFailTest() {
        assertFalse(User.userLogin(new User("Elenta_lina1254", "elenta123456")));
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
        User.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        User.driver.get("https://elenta.lt/prisijungti");
        User.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();

    }
}
