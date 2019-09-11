package ui;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CareerPage extends PageBase {

    public CareerPage(WebDriver driver) {
        super(driver);
    }

    public String rowXpath;
    WebDriverWait wait = new WebDriverWait(driver, 30);

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

    @FindBy(how = How.XPATH, using = "//b[contains(text(),\"What you'll do:\")]")
    private WebElement what_youl_will_do_text1;

    @FindBy(how = How.XPATH, using = "//div[@class='col g-1of1 g1-2of3 g2-1of2 p_cell margin--none margin-top-32']//div[3]//p//b")
    private WebElement what_youl_will_do_text2;

    @FindBy(how = How.XPATH, using = "//b[contains(text(),\"What you'll definitely need:\")]")
    private WebElement what_you_will_definetly_need_text1;

    @FindBy(how = How.XPATH, using = "//div[@class='col g-1of1 g1-2of3 g2-1of2 p_cell margin--none margin-top-32']//div[6]//p//b")
    private WebElement what_you_will_definetly_need_text2;

    @FindBy(how = How.XPATH, using = "//b[contains(text(),\"What we'd love you to have:\")]")
    private WebElement what_we_would_love_you_to_have_text1;

    @FindBy(how = How.XPATH, using = "//div[@class='col g-1of1 g1-2of3 g2-1of2 p_cell margin--none margin-top-32']//div[9]//p//b")
    private WebElement what_we_would_love_you_to_have_text2;

    public int getTableRowCount() {
        return getTableRowCount(table_count);
    }

    public String clickJobRole(int jobRowNumber) {
        rowXpath = "//tr[@class='list-jobs-wd'][" + jobRowNumber + "]";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(rowXpath)));
        String path = "//table[@id='list-mobile']//tr[" + jobRowNumber + "]//*[@class='job-description-wd']";
        String jobTitle = driver.findElement(By.xpath(path)).getAttribute("innerHTML");
        WebElement element = driver.findElement(By.xpath(rowXpath));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(element).click().perform();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
        js.executeScript("arguments[0].click()", element);
        waitForElementToBePresent(job_title);
        return jobTitle.trim();
    }

    public boolean verifyJobTitleTag(String jobTitle) {
        waitForElementToBePresent(job_title);
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
//            if (what_youl_will_do_text1.isDisplayed()) {
//                if (what_youl_will_do_text1.getTagName().equals("b"))
//                    flag = true;
//            }
//        } catch (NoSuchElementException e) {
//            if (what_youl_will_do_text2.isDisplayed()) {
//                if (what_youl_will_do_text2.getTagName().equals("b"))
//                    flag = true;
//            }
//        }
        waitForPageToLoad();
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='col g-1of1 g1-2of3 g2-1of2 p_cell margin--none margin-top-32']//p"));
        for (WebElement element : elements) {
            System.out.println(element.getText());
                if (element.getText().equals("What you'll do:")) {
                    flag=true;
            }
        }

        return flag;
    }


    public boolean verifyWhatYouWillDefintelySectionValue() {
        boolean flag = false;
//        try {
//            if (what_you_will_definetly_need_text1.isDisplayed()) {
//                if (what_you_will_definetly_need_text1.getTagName().equals("b"))
//                    flag = true;
//            }
//        } catch (NoSuchElementException e) {
//            if (what_you_will_definetly_need_text2.isDisplayed()) {
//                if (what_you_will_definetly_need_text2.getTagName().equals("b"))
//                    flag = true;
//            }
//        }
        waitForPageToLoad();
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='col g-1of1 g1-2of3 g2-1of2 p_cell margin--none margin-top-32']//p"));
        for (WebElement element : elements) {
//            System.out.println(element.getText());
            if (element.getText().trim().contains("What you'll definitely need:")) {
                flag=true;
            }
        }
        return flag;
    }

    public boolean verifyWhatWeWouldLoveYouToHaveSectionValue() {
        boolean flag = false;
//        try {
//            if (what_we_would_love_you_to_have_text1.isDisplayed()) {
//                if (what_we_would_love_you_to_have_text1.getTagName().equals("b"))
//                    flag = true;
//            }
//        } catch (NoSuchElementException e) {
//            if (what_we_would_love_you_to_have_text2.isDisplayed()) {
//                if (what_we_would_love_you_to_have_text2.getTagName().equals("b"))
//                    flag = true;
//            }
//        }
        waitForPageToLoad();
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='col g-1of1 g1-2of3 g2-1of2 p_cell margin--none margin-top-32']//p"));
        for (WebElement element : elements) {
            if (element.getText().contains("What we'd love you to have:")) {
                flag=true;
            }
        }
        return flag;
    }

    public void goBack() {
        driver.navigate().back();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(rowXpath)));
    }
}
