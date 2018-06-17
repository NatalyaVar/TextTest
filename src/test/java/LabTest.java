import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LabTest {

    private WebDriver driver;
    private WebDriverWait wait;
    String url = "https://mail.ru";


    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void SendText() throws IOException {
        driver.get(url);

        LabPage labPage = new LabPage();
        PageFactory.initElements(driver, labPage);

        WebElement login = labPage.login;

        //Ждем, пока поле логина станет видимым
        wait.until(ExpectedConditions.visibilityOf(login));

        WebElement domain = labPage.domain;
        WebElement password = labPage.password;
        WebElement submit = labPage.submit;
        WebElement write = labPage.write;
        WebElement body = labPage.body;
        WebElement whom = labPage.whom;
        WebElement send = labPage.send;

        labPage.enter(driver, login, domain, password, submit);

        //Ждем, пока станет видима кнопка написать письмо
        wait.until(ExpectedConditions.visibilityOf(write));
        labPage.sendLetter(driver, write, body, whom, send);

    }
}
