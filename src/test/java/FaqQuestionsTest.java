import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.HomePageQuestions;

@RunWith(Parameterized.class)
public class FaqQuestionsTest {
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";
    private WebDriver driver;

    private final int idQuestionText;
    private final String questionAnswerText;
    //private final String expectedAnswerText;

    public FaqQuestionsTest (int idQuestionText, String questionAnswerText) {
    this.idQuestionText = idQuestionText;
    this.questionAnswerText = questionAnswerText;
   // this.expectedAnswerText = expectedAnswerText;
}

    @Parameterized.Parameters
    public static Object[][] setTestData() {
        return new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Before
    public void setupTest() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.get(URL);
    }

    @Test
    public void testDropDownListText() {

        HomePageQuestions actualText = new HomePageQuestions(driver);
        actualText.clickRccConfirmButton();
        actualText.checkAccordionItemOpensAndShowsExpectedText(idQuestionText, questionAnswerText);
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
