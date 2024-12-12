import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderingScooterTest {
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;
    private String buttonLocation;
    private String name; //имя
    private String surname; //фамилия
    private String address; //fдрeс
    private int metro; //мeтро
    private String phone; //тeлефон
    private int daysToAdd; //rогда привезти самокат
    private String option; //Срок аренды
    private boolean checkboxBlack; //выбора цвета самоката черныц
    private boolean checkboxGrey; //выбора цвета самоката серый
    private String commentText; //комментарий
    public OrderingScooterTest(String buttonLocation, String name, String surname, String address, int metro, String phone, int daysToAdd, String option,
                               boolean checkboxBlack, boolean checkboxGrey, String commentText) {
        this.buttonLocation = buttonLocation;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.daysToAdd = daysToAdd;
        this.option = option;
        this.checkboxBlack = checkboxBlack;
        this.checkboxGrey = checkboxGrey;
        this.commentText = commentText;

    }

    @Before
    public void setUp() {
        boolean useFirefox = false; //указать false  для fireFox
        driver = getWebDriver(useFirefox);
        driver.get(URL);
    }
    public WebDriver getWebDriver (boolean useFirefox){
        WebDriver driver;
        if (useFirefox) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        return driver;
    }


    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"header","Алексей","Петров","Пушкина, 21",12,"88001234567",1,"сутки",true,true,"погнали"},
                {"middle","Иван","Пупкин","Советская, 88",31,"74950001122",1,"сутки",true,true,"Жду не дождусь"},
        };
    }

    @Test
    public void testOrderWithValidData() {
        Boolean actual = new ru.yandex.praktikum.sprint_4.OrderingScooter(driver)

                // Ввод данных клиента
                .customerDataForm(buttonLocation,name, surname,address,metro,phone)
                // Ввод данных заказа
                .orderDetails(daysToAdd, option, checkboxBlack, checkboxGrey, commentText)
                //Проверка на наличие окна
                .checkOrderConfirmationWindow();

        assertTrue("Окно Заказ оформлен отсутсвует",actual);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
