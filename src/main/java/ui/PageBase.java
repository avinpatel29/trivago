package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;


/**
 * @author avinashpatel
 */
public class PageBase {

    public WebDriver driver;
    private static JavascriptExecutor js;
    static Logger log = Logger.getLogger(PageBase.class);

    /**
     * Constructor of the class.
     *
     * @param driver - driver
     */
    public PageBase(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }

    public void enterURL(String url) {
        log.info("Executing GET URL command on the browser");
        driver.get(url);
        waitForPageToLoad();
    }


    public void waitForElementToBePresent(WebElement element) {
        WebDriverWait w = new WebDriverWait(driver, 10);
        try {
            log.info("Waiting for the element:"+element +" to be visible");
            w.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     *  Method which waits for the complete page to load
     */
    public  void waitForPageToLoad() {
        String pageLoadStatus = null;
        do {
            JavascriptExecutor js = (JavascriptExecutor) driver;
             pageLoadStatus = (String)js.executeScript("return document.readyState");
        } while ( !pageLoadStatus.equals("complete") );
    }

    public int getTableRowCount(String element){
        List<WebElement> rows = driver.findElements(By.xpath(element));
        return rows.size();
    }
}
