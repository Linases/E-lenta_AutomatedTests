import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.SQLOutput;
import java.util.List;

public class User {
    private static List<WebElement> elements;
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

    public static boolean userRegistration(User user) {

        driver.findElement(By.id("UserName")).sendKeys(user.userName);
        driver.findElement(By.id("Email")).sendKeys(user.email);
        driver.findElement(By.id("Password")).sendKeys(user.password1);
        driver.findElement(By.id("Password2")).sendKeys(user.password2);
        driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click();
        // driver.findElement(By.className("info"));

        return userRegisterValidation();
    }

    public static boolean userRegisterValidation() {
        boolean result = true;

        WebElement success = driver.findElement(By.className("info"));
        WebElement nameError = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[1]/td[2]/span"));
        WebElement emailError = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[4]/td[2]/span"));
//        WebElement password1Error = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[7]/td[2]/span"));
//        WebElement password2Error = driver.findElement(By.xpath("//*[@id=\"main-container\"]/form/fieldset/table/tbody/tr[8]/td[2]/span"));
        if (success.getText().contains("Jūs sėkmingai prisiregistravote")) {
            System.out.println("sėkminga registracija:"+success.getText());
            result = false;
        }
        if (nameError.getText().contains("Įveskite vartotojo vardą")) {
            System.out.println("nameSpace:" + nameError.getText());
            result = false;
        }
        if (nameError.getText().contains("Vartotojas tokiu vardu jau įregistruotas.")) {
            System.out.println("nameAlreadyExists: " + nameError.getText());
            result = false;
        }
        if (emailError.getText().contains("Toks el. pašto adresas jau įregistruotas")) {
            System.out.println("email Already Exists: " + emailError.getText());
            result = false;
        }
        if (emailError.getText().contains("Įveskite el. pašto adresą.")) {
            System.out.println("Enter email:" + emailError.getText());
            result = false;
        }
        if (emailError.getText().contains("El. pašto adresas nėra tinkamas.")) {
            System.out.println("Enter valid email:" + emailError.getText());
            result = false;
        }
//        if (password1Error.getText().contains("Įveskite slaptažodį")) {
//            System.out.println("enter password:" + password1Error.getText());
//            return false;
//        }
//        if (password2Error.getText().contains("Pakartotinai neįvedėte slaptažodžio.")) {
//            System.out.println("Enter second password:"+password2Error.getText());
//            return false;
//        }
//        if (password2Error.getText().contains("Nesutampa slaptažodžiai")) {
//            System.out.println("Passwords don't match : "+password2Error.getText());
//            return false;
//        }
        return result;

    }

    public static boolean userLogin (User user){
        driver.findElement(By.id("UserName")).sendKeys(user.userName);
        driver.findElement(By.id("Password")).sendKeys(user.password1);
        driver.findElement(By.xpath("//*[@id=\"form\"]/fieldset/table/tbody/tr[4]/td[2]/input")).click();
        return true;
    }

}



