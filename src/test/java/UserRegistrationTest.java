import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.time.Duration;

public class UserRegistrationTest {

    @Test
    public void userSuccessfulRegistrationTest (){
assertTrue(User.userRegistration(new User("Elenta_lina125", "email2@email.com", "elenta", "elenta")));

    }

    @Test
    public void userNameEmptyTest (){
        assertFalse(User.userRegistration(new User("", "email1@email.com", "elenta", "elenta")));
    }

    @Test
    public void userNameAlreadyExistsTest (){
        assertFalse(User.userRegistration(new User("Elenta_lina124", "email@email.com", "elenta", "elenta")));
    }
    @Test
    public void emailAlreadyExistsTest (){
        assertFalse(User.userRegistration(new User("Elenta_lina125", "email1@email.com", "elenta", "elenta")));
    }
    @Test
    public void emailEmptyTest (){
        assertFalse(User.userRegistration(new User("Elenta_lina125", "", "elenta", "elenta")));
    }
    @Test
    public void emailOneLetterTest (){
        assertFalse(User.userRegistration(new User("Elenta_lina125", "a", "elenta", "elenta")));
    }
    @Test
    public void password1EmptyTest (){
        assertFalse(User.userRegistration(new User("Elenta_lina125", "lina2@gmail.com", "", "elenta")));
    }
    @Test
    public void password2EmptyTest (){
        assertFalse(User.userRegistration(new User("Elenta_lina125", "lina2@gmail.com", "elenta", "")));
    }
    @Test
    public void password2DiffersTest (){
        assertFalse(User.userRegistration(new User("Elenta_lina125", "lina2@gmail.com", "elenta", "elenta1")));
    }
    @BeforeClass

    public void beforeClass() {
        //  System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver111.exe");
        User.driver = new ChromeDriver();
        User.driver.manage().window().maximize();
        User.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        User.driver.get("https://elenta.lt/registracija");
        User.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
    }

    @AfterClass
    public void afterClass() {
//        driver.quit();
    }
}
