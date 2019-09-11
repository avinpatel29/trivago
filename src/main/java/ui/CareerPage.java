package ui;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CareerPage extends PageBase {

    public CareerPage(WebDriver driver) {
        super(driver);
    }

    public String rowXpath;
    WebDriverWait wait = new WebDriverWait(driver, 30);
    JavascriptExecutor js = (JavascriptExecutor)driver;

    @FindBy(how = How.XPATH, using = "//div[@id='list-jobs']")
    private WebElement open_position_table;

    public String table_count = "//table[@id='list-mobile']/tbody/tr";

    @FindBy(how = How.XPATH, using = "//b[contains(text(),'Job family')]")
    private WebElement job_family_text;

    @FindBy(how = How.XPATH, using = "//*[@class='col g-1of1 margin--none common-title']/h1")
    private WebElement job_title;

    @FindBy(how = How.XPATH, using = "//span[@id='job_family']")
    private WebElement job_family;

    @FindBy(how = How.XPATH, using = "//div[@class='col g-1of1 g1-1of3 g2-1of4 job-tags margin-top-32']/p[2]/span")
    private WebElement experiencelevel;

    @FindBy(how = How.XPATH, using = "//div[@class='col g-1of1 g1-1of3 g2-1of4 job-tags margin-top-32']/p[3]/span")
    private WebElement location;

    @FindBy(how = How.XPATH, using = "//div[@class='col g-1of1 g1-1of3 g2-1of4 job-tags margin-top-32']/p[4]/span")
    private WebElement language;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn--tertiary btn--small apply-small margin-top-16 no-tablet utm__attached']")
    private WebElement apply_button;

    @FindBy(how = How.XPATH, using = "//div[@class='col g-1of1 g1-2of3 g2-1of2 p_cell margin--none margin-top-32']//p")
    private WebElement section;


    public int getTableRowCount() {
        return getTableRowCount(table_count);
    }

    public String clickJobRole(int jobRowNumber) {
        rowXpath = "//tr[@class='list-jobs-wd'][" + jobRowNumber + "]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(rowXpath)));
        String path = "//table[@id='list-mobile']//tr[" + jobRowNumber + "]//*[@class='job-description-wd']";
        String jobTitle = driver.findElement(By.xpath(path)).getAttribute("innerHTML");
        WebElement element = driver.findElement(By.xpath(rowXpath));

        js.executeScript("arguments[0].scrollIntoView()", element);
        js.executeScript("arguments[0].click()", element);
        waitForElementToBePresent(apply_button);
        log.info("Clicked on the Role: "+jobTitle);
        return jobTitle.trim();
    }

    public boolean verifyJobTitleTag(String jobTitle) {
        wait.until(ExpectedConditions.elementToBeClickable(apply_button));
         if(job_title.getTagName().equals("h1"))
            return true;
        else
            return false;
    }

    public boolean verifyJobFamilyValue() {
        waitForElementToBePresent(job_family);
        if (job_family.getText().isEmpty())
            return false;
        else
            return true;
    }

    public boolean verifyExperienceLevel() {
        waitForElementToBePresent(experiencelevel);
        if (experiencelevel.getText().isEmpty())
            return false;
        else
            return true;
    }

    public boolean verifyLocationValue() {
        waitForElementToBePresent(location);
        if (location.getText().isEmpty())
            return false;
        else
            return true;
    }

    public boolean verifyLanguageValue() {
        waitForElementToBePresent(language);
        if (language.getText().isEmpty())
            return false;
        else
            return true;
    }

    public boolean verifyApplyButtonExists() {
        waitForElementToBePresent(apply_button);
        if (apply_button.isDisplayed())
            return true;
        else
            return false;
    }

    public boolean verifyWhatYouWillDoSectionValue() {
        boolean flag = false;
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='col g-1of1 g1-2of3 g2-1of2 p_cell margin--none margin-top-32']//p//b"));
        for (WebElement element : elements) {
            js.executeScript("arguments[0].scrollIntoView()", element);
                if (element.getText().equals("What you'll do:")) {
                    flag=true;
                    break;
            }
        }
        return flag;
    }


    public boolean verifyWhatYouWillDefintelySectionValue() {
        boolean flag = false;
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='col g-1of1 g1-2of3 g2-1of2 p_cell margin--none margin-top-32']//p//b"));
        for (WebElement element : elements) {
            js.executeScript("arguments[0].scrollIntoView()", element);
            if (element.getText().trim().equals("What you'll definitely need:")) {
                flag=true;
                break;
            }
        }
        return flag;
    }

    public boolean verifyWhatWeWouldLoveYouToHaveSectionValue() {
        boolean flag = false;
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='col g-1of1 g1-2of3 g2-1of2 p_cell margin--none margin-top-32']//p//b"));
        for (WebElement element : elements) {
            js.executeScript("arguments[0].scrollIntoView()", element);
            if (element.getText().equals("What we'd love you to have:")) {
                flag=true;
                break;
            }
        }
        return flag;
    }

    public void goBack() {
        driver.navigate().back();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(rowXpath)));
    }
}
