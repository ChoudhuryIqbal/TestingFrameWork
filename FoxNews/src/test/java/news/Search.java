package news;

import base.CommonAPI;
import org.testng.annotations.Test;

/**
 * Created by a on 7/11/2016.
 */
public class Search extends CommonAPI {




    @Test(enabled = false)
    public void searchNews() throws InterruptedException{
        clickByXpath(".//*[@id='top']/div/div/form/fieldset/a");
        Thread.sleep(1000);
        typeByXpath(".//*[@id='top']/div/div/form/fieldset/input[1]","Politics");
        takeEnterXpath(".//*[@id='top']/div/div/form/fieldset/input[1]");

        Thread.sleep(1000);

    }
}
