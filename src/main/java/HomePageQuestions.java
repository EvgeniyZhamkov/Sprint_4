package ru.yandex.praktikum.sprint_4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageQuestions {
    private WebDriver driver;

    public HomePageQuestions(WebDriver driver) {
        this.driver = driver;
    }

    public HomePageQuestions scrollAndClickElementDropDownList(String question) { //скролл до элемента и клик по нему
        WebElement element = driver.findElement(By.xpath("//*[text() = '"+ question + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        return this;
    }

    public String getAndVerifyTextDropDownList(String answer) { //получение текста из элемента после клика
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id =\"accordion__panel-" + answer + "\"]/p")));
        return driver.findElement(By.xpath("//*[@id =\"accordion__panel-" + answer + "\"]/p")).getText();
    }
}