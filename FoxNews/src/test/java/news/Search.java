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
        String [] data=readNewsData.getData();


        clickByXpath(".//*[@id='top']/div/div/form/fieldset/a");
        Thread.sleep(1000);
        int counter=0;
        for (String st:data){
            if(counter==0){
                typeByXpath(".//*[@id='top']/div/div/form/fieldset/input[1]",st);
                takeEnterXpath(".//*[@id='top']/div/div/form/fieldset/input[1]");
                Thread.sleep(3000);
                counter++;

            }
            else{
                typeByXpath(".//*[@id='top']/div/div/form/fieldset/input[1]",st);
                takeEnterXpath(".//*[@id='top']/div/div/form/fieldset/input[1]");
                Thread.sleep(3000);
                clearInputField(".//*[@id='search-container']/form/input");
            }
        }

    }
/**

    @Test(enabled = false)
    public void searchNew() throws InterruptedException{
        clickByXpath(".//*[@id='top']/div/div/form/fieldset/a");
        Thread.sleep(1000);
        typeByXpath(".//*[@id='top']/div/div/form/fieldset/input[1]","Politics");
        takeEnterXpath(".//*[@id='top']/div/div/form/fieldset/input[1]");

        Thread.sleep(1000);

    }**/
}
