/**
 *  The class implements the standard action online
 */

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

import static java.awt.SystemColor.text;

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
        if (text != null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    Select select(By locator) {
        Select select = new Select(wd.findElement(locator));
        return select;
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
}
