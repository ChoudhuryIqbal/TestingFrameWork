package news;

import base.CommonAPI;
import org.testng.annotations.Test;
import reader.ReadNewsData;

import java.io.IOException;

/**
 * Created by a on 7/11/2016.
 */
public class Search extends CommonAPI {
    ReadNewsData readNewsData=new ReadNewsData();


    @Test
    public void searchNews() throws InterruptedException,IOException{

        clickByXpath(".//*[@id='top']/div/div/form/fieldset/a");
        Thread.sleep(1000);
        typeByXpath(".//*[@id='top']/div/div/form/fieldset/input[1]",readNewsData.getData());
        takeEnterXpath(".//*[@id='top']/div/div/form/fieldset/input[1]");
        Thread.sleep(3000);
    }


    @Test(enabled = false)
    public void searchNew() throws InterruptedException{
        clickByXpath(".//*[@id='top']/div/div/form/fieldset/a");
        Thread.sleep(1000);
        typeByXpath(".//*[@id='top']/div/div/form/fieldset/input[1]","Politics");
        takeEnterXpath(".//*[@id='top']/div/div/form/fieldset/input[1]");

        Thread.sleep(1000);

    }
}
