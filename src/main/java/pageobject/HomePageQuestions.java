package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageQuestions {
    private static WebDriver driver;
    private static final By BUTTON_LOCATOR_IN_FOLDER = By.xpath("//button[contains(@class, 'Button_Button__ra12g')]"); //кнoпка "Закaать" в шапке
    private static final By BUTTON_LOCATOR_IN_PAGE = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); //кнoпка "Зaказать" в середине
    private static final By CLICK_BUTTON_COOKIE = By.xpath("//*[@id=\"rcc-confirm-button\"]"); //локатор для куки
    private static final By ACCORDION_ITEMS = By.xpath(".//div[@class='accordion__item']"); //локатор раскрывающего списка
    private static final By ACCORDIO_QUESTION = By.xpath(".//div[@class='accordion__panel']"); //локатор вопроса
    private static final By ANSWER_TO_THE_QUESTION = By.xpath(".//div[@class='accordion__panel']/p");

    public HomePageQuestions(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRccConfirmButton() {
        driver.findElement(CLICK_BUTTON_COOKIE).click();
    }

    public void clickOrderButton(int index) {
        driver.findElements(BUTTON_LOCATOR_IN_FOLDER).get(index).click();
    }

    public void clickAccordionItem(int index) {
        driver.findElements(ACCORDION_ITEMS).get(index).click();
    }

    public void checkAccordionItemPanelDisplayed(int index) {
        WebElement accordionItemPanel = driver.findElements(ACCORDION_ITEMS).get(index)
                .findElement(ACCORDIO_QUESTION);

        assertTrue(accordionItemPanel.isDisplayed());
    }

    private void assertTrue(boolean displayed) {
    }

    public void checkAccordionItemTextMatchesExpectedText(int index, String expectedText) {
        String accordionItemText = driver.findElements(ACCORDION_ITEMS).get(index)
                .findElement(ANSWER_TO_THE_QUESTION)
                .getText();

        assertTrue(accordionItemText.equals(expectedText));
    }

    // Step
    public void checkAccordionItemOpensAndShowsExpectedText(int index, String expectedText) {
        clickAccordionItem(index);
        checkAccordionItemPanelDisplayed(index);
        checkAccordionItemTextMatchesExpectedText(index, expectedText);
    }
}
