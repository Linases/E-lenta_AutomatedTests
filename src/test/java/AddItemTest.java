
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import java.time.Duration;

public class AddItemTest {
    @Parameters({"adName", "adDescription", "city", "phoneNr", "email"})
    @Test(priority = 1)
    public void addItemPositiveTest(
            @Optional("testas") String adName,
            @Optional("testas") String adDescription,
            @Optional("testas") String city,
            @Optional("+37063540125") String phone,
            @Optional("email@email.com") String email
    ) {
        assertTrue(User.addItem(new User(adName, adDescription, city,phone, email)));

    }

    @Test
    public void noAdNameTest() {

        assertFalse(User.addItem(new User("", "Ieškau testuotojos darbo", "Vilnius", "861799202", "email@gmail.com")));
    }

    @Test
    public void textSizeAdNameTest() {

        assertFalse(User.addItem(new User("ieškau bet kokio darbo".repeat(2000), "Ieškau testuotojos darbo", "Vilnius", "861799202", "email@gmail.com")));
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
    public void cityNameSizeTest() {

        assertFalse(User.addItem(new User("darbas", "Ieškau testuotojos darbo", "Madagaskaras".repeat(3000), "861799202", "email@gmail.com")));
    }

    @Test
    public void noPhoneNrTest() {

        assertFalse(User.addItem(new User("Ieškau", "Ieškau testuotojos darbo", "Vilnius", "", "email@gmail.com")));
    }

    @Test
    public void wrongPhoneNrTest() {

        assertFalse(User.addItem(new User("Ieškau", "Ieškau testuotojos darbo", "Vilnius", "26565654", "email@gmail.com")));
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
