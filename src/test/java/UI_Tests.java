import commonutilities.GetConfig;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.ITestNGListener;
import org.testng.annotations.Test;
import ui.TestBaseUI;

public class UI_Tests extends TestBaseUI implements ITestNGListener {

    @Test(priority = 1, description="Testcase to check conditions on every job profile")
    public void testcase_1() throws ConfigurationException {
        container.careerPage.enterURL(GetConfig.getProperties("CAREER_URL"));
        int rowCount=container.careerPage.getTableRowCount();
        for(int i=0;i<rowCount;i++){
            String jobRoleTitle= container.careerPage.clickJobRole(i+1);
            Assert.assertTrue(container.careerPage.verifyJobTitleTag(jobRoleTitle),"Job Title is not h1 tag");
            Assert.assertTrue(container.careerPage.verifyJobFamilyValue(),"Job Family value is empty");
            Assert.assertTrue(container.careerPage.verifyExperienceLevel(),"Experience value is empty");
            Assert.assertTrue(container.careerPage.verifyLocationValue(),"Location value is empty");
            Assert.assertTrue(container.careerPage.verifyLanguageValue(),"Language value is empty");
            Assert.assertTrue(container.careerPage.verifyApplyButtonExists(),"Apply button is not present");
            Assert.assertTrue(container.careerPage.verifyWhatYouWillDoSectionValue(),"What you'll do doesn't have <b> tag");
            Assert.assertTrue(container.careerPage.verifyWhatYouWillDefintelySectionValue(),"What you'll definitely need doesn't have <b> tag");
            Assert.assertTrue(container.careerPage.verifyWhatWeWouldLoveYouToHaveSectionValue(),"What we'd love you to have doesn't have <b> tag");
            container.careerPage.goBack();
        }
    }
}
