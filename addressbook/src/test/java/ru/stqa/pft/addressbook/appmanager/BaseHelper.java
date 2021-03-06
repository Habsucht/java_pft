/**
 *  The class implements the standard action online
 */

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class BaseHelper {
    WebDriver wd;

    BaseHelper(WebDriver wd) {
        this.wd = wd;
    }

    void type(By locator, String text) {
        wd.findElement(locator).click();
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    void attach(By locator, File file) {
        if (file != null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
            System.out.println("Add file " + file.getAbsolutePath());
        }
    }

    Select select(By locator) {
        return new Select(wd.findElement(locator));
    }

    void click(By locator) {
        wd.findElement(locator).click();
    }

    void alert() {
        wd.switchTo().alert().accept();
    }

    public void submitModification() {
        click(By.xpath("//*[@name='submit' or @name='update']"));
        /*
        if (isElementPresent(By.name("update"))) {
            click(By.name("update"));
        } else if (isElementPresent(By.name("submit"))){
            click(By.name("submit"));
        }
        */
    }

    boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    boolean selectElementFromList (String selector, String data) {
        if (wd.findElement(By.name(selector)).getText().contains(data)) {
            select(By.name(selector)).selectByVisibleText(data);
            return true;
        } else {
            select(By.name(selector)).selectByVisibleText("[none]");
            return false;
        }
    }
}
