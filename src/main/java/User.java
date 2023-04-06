import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Vector;

public class User {
    public String userName;
    public String email;
    public String password1;
    public String password2;
    public static WebDriver driver;


    public User(String userName, String email, String password1, String password2) {
        this.userName = userName;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
    }

    public User(String userName, String password1) {
        this.userName = userName;
        this.password1 = password1;
    }

    public User(String email) {
        this.email = email;
    }

    public static boolean userRegistration(User user) {

        driver.findElement(By.id("UserName")).sendKeys(user.userName);
        driver.findElement(By.id("Email")).sendKeys(user.email);
        driver.findElement(By.id("Password")).sendKeys(user.password1);
        driver.findElement(By.id("Password2")).sendKeys(user.password2);
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
        // driver.findElement(By.className("info"));

        return userRegisterVerification();
    }

    public static boolean userRegisterVerification() {
        boolean result = true;


        List<WebElement> successs = driver.findElements(By.className("info"));
        if (successs.size() > 0) {
            System.out.println("sėkmingai prisireginot");
            return true;
        }

        List<WebElement> nameError = driver.findElements(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span"));
        List<WebElement> emailError = driver.findElements(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span"));
        List<WebElement> password1Error = driver.findElements(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span"));
        List<WebElement> password2Error = driver.findElements(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[8]/td[2]/span"));

        if (!nameError.isEmpty()) {
            System.out.println("klaida:" + nameError.get(0).getText());
            result = false;
        }
        if (!emailError.isEmpty()) {
            System.out.println("klaida:" + emailError.get(0).getText());
            result = false;
        }
        if (!password1Error.isEmpty()) {
            System.out.println("klaida:" + password1Error.get(0).getText());
            result = false;
        }
        if (password2Error.size() > 0) {
            System.out.println("klaida:" + password2Error.get(0).getText());
            result = false;
        }

        return result;

    }

    public static boolean userLogin(User userLogin) {
        driver.findElement(By.id("UserName")).sendKeys(userLogin.userName);
        driver.findElement(By.id("Password")).sendKeys(userLogin.password1);
        driver.findElement(By.xpath("//*[@id=\"form\"]/fieldset/table/tbody/tr[4]/td[2]/input")).click();
        return loginVerification();
    }

    public static boolean loginVerification() {

        List<WebElement> loginFail = driver.findElements(By.xpath("//*[@id=\"form\"]/fieldset/table/tbody/tr[5]/td/div/ul/li"));
        if (!loginFail.isEmpty())
            System.out.println("klaida:" + loginFail.get(0).getText());
        return false;
    }

    public static boolean userRemindPassword(User userEmail) {
        driver.findElement(By.xpath("//*[@id=\"form\"]/fieldset/table/tbody/tr[8]/td/p/a")).click();
        driver.findElement(By.id("Email")).sendKeys(userEmail.email);
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/div[2]/form/table/tbody/tr[3]/td[2]/input")).click();
        return remindPasswordVerification();
    }

    public static boolean remindPasswordVerification() {
        boolean result = true;
        List<WebElement> successs = driver.findElements(By.xpath("//*[@id=\"main-container\"]/div[2]/div/h2"));
        if (successs.size() > 0) {
            System.out.println("sėkmingai išsiųstas slaptažodis");
            return true;
        }

        List<WebElement> emailError = driver.findElements(By.xpath("//*[@id=\"main-container\"]/div[2]/form/table/tbody/tr[1]/td[2]/span"));
        if (emailError.size() > 0) {
            System.out.println("klaida:" + emailError.get(0).getText());
            result = false;
        }
        if (emailError.isEmpty()) {
            result = true;
        }
        return result;
    }
}

