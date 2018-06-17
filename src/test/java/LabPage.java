import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LabPage {

    private static WebDriver driver;
    WebDriverWait wait;

    String addressee = "asdQ234fg@list.ru";

    String myLogin = "s78gh9";
    String myPassword = "Df56uio";

    //Кнопки в верхнем поле
    @FindBy(how = How.CSS, css = "#mailbox\\3a login")
    public WebElement login;

    @FindBy(how = How.CSS, css = "#mailbox\\3a domain")
    public WebElement domain;

    @FindBy(how = How.CSS, css = "#mailbox\\3a submit")
    public WebElement submit;

    @FindBy(how = How.CSS, css = "#mailbox\\3a password")
    public WebElement password;

    @FindBy(how = How.CSS, css = "#b-toolbar__left > div > div > div.b-toolbar__group.b-toolbar__group_left")
    public WebElement write;

    @FindBy(how = How.XPATH, xpath = "//*[starts-with (@id,'toolkit-')]/table/tbody/tr[2]/td")
    public WebElement body;

    @FindBy(how = How.XPATH, xpath = "//*[@id='b-toolbar__right']/div[3]/div/div[2]/div[1]/div")
    public WebElement send;

    @FindBy(how = How.XPATH, xpath = "//*/div[1]/div/div[3]/div[1]/div/div/div[2]/div/div/div")
    public WebElement whom;


    //Вход в почту
    public void enter (WebDriver driver, WebElement login, WebElement domain, WebElement password, WebElement submit) {

        login.sendKeys(myLogin);

        //Из выпадающего списка выбираем @list.ru
        domain.click();
        Select select = new Select (domain);
        select.selectByVisibleText("@list.ru");
        domain.click();

        //Вводим пароль
        password.sendKeys(myPassword);
        //Входим
        submit.click();
    }

    public void sendLetter (WebDriver driver, WebElement write, WebElement body, WebElement whom, WebElement send ) {

        //Нажимаем кнопку написать письмо
        write.click();

        // Кому
        whom.sendKeys(addressee);

        //Переключаемся во фрейм для ввода собщения
        WebElement dd = driver.findElement(By.xpath("//*/iframe [starts-with(@id,'toolkit')]"));
        driver.switchTo().frame(dd);

        //Выбираем поле ввода, очищаем его и вводим текст письма
        WebElement ff = driver.findElement(By.cssSelector("#tinymce"));
        ff.click();
        ff.clear();

        //Вводим текст сообщения
        ff.sendKeys("Это ответ на тестовое задание для организации 'Лаборатория качества'");

        //Вернуться из фрейма
        driver.switchTo().defaultContent();

        //Отправляем
      send.click();
    }
}
