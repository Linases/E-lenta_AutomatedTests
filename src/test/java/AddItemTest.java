
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.time.Duration;

public class AddItemTest {
    @Parameters({"adName", "adDescription", "city", "phoneNr", "email"})
    @Test(priority = 1)
    public void addItemPositiveTest(String adName, String adDescription, String city, String phone, String email) {

        assertTrue(User.addItem(new User("Ieškau darbo", "Ieškau testuotojos darbo", "Vilnius", "861799202", "email@gmail.com")));

    }

    @Test
    public void noAdNameTest() {

        assertFalse(User.addItem(new User("", "Ieškau testuotojos darbo", "Vilnius", "861799202", "email@gmail.com")));
    }

    @Test
    public void noDescriptionTest() {

        assertFalse(User.addItem(new User("Ieškau darbo", "", "Vilnius", "861799202", "email@gmail.com")));

    }

    @Test
    public void noCityTest() {

        assertTrue(User.addItem(new User("Ieškau darbo", "Ieškau testuotojos darbo", "", "861799202", "email@gmail.com")));

    }

    @Test
    public void noPhoneNrTest() {

        assertFalse(User.addItem(new User("Ieškau", "Ieškau testuotojos darbo", "Vilnius", "", "email@gmail.com")));
    }

    @Test
    public void wrongPhoneNrTest() {

        assertFalse(User.addItem(new User("Ieškau", "Ieškau testuotojos darbo", "Vilnius", "64564454", "email@gmail.com")));
    }

    @Test
    public void noEmailTest() {

        assertFalse(User.addItem(new User("Ieškau", "Ieškau testuotojos darbo", "Vilnius", "861799202", "")));
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
