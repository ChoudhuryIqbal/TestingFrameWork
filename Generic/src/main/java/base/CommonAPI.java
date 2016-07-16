package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by rrt on 4/23/2016.
 */
public class CommonAPI {

    public WebDriver driver = null;

    @Parameters({"usecloud", "userName", "accessKey", "os", "browserName", "browserVersion", "url","testName"})
    @BeforeMethod
    public void setUp(@Optional("false") boolean usecloud, @Optional("rahmanww") String userName, @Optional("")
            String accessKey, @Optional("Windows 8") String os, @Optional("firefox") String browserName, @Optional("34")
                              String browserVersion, @Optional("http://www.cnn.com") String url,String testName) throws IOException {
        if (usecloud == true) {
            //run in cloud
            getCloudDriver(userName, accessKey, os, browserName, browserVersion,testName);
        } else {
            //run in local
            getLocalDriver(browserName);
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void clearInputField(String locator){
       // driver.findElement(By.cssSelector(locator)).clear();
        driver.findElement(By.xpath(locator)).clear();
    }
    public void sleepFor(int sec) throws InterruptedException{
        Thread.sleep(sec*1000);
    }

    public WebDriver getLocalDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "Generic/browser-driver/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "Generic/browser-driver/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        return driver;

    }

    public WebDriver getCloudDriver(String userName, String accessKey, String os, String browserName,
                                    String browserVersion,String testName) throws IOException {
        {

            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("platform", os);
            cap.setBrowserName(browserName);
            cap.setCapability("version", browserVersion);
            cap.setCapability("Name",testName);
            driver = new RemoteWebDriver(new URL("http://" + userName + ":" + accessKey +
                    "@ondemand.saucelabs.com:80/wd/hub"), cap);

            return driver;
        }
    }

    @AfterMethod
    public void cleanUp() {
        driver.close();
    }

    public void clickByCss(String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }

    public void clickByXpath(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public void typeByCss(String locator, String value) {
        driver.findElement(By.cssSelector(locator)).sendKeys(value);
    }

    public void typeByXpath(String locator, String value) {
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }
    public void takeEnterXpath(String locator){
        driver.findElement(By.xpath(locator)).sendKeys(Keys.ENTER);
    }

    public void takeEnterKeys(String locator) {
        driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
    }

    public List<WebElement> getListOfWebElements(String locator) {

        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.id(locator));

        return list;
    }

    public List<String> getListOfString(List<WebElement> list) {

        List<String> items = new ArrayList<String>();
        for (WebElement element : list) {
            items.add(element.getText());
        }

        return items;
    }

    public void selectOptionByVisibleText(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    public void navigateBack(){
        driver.navigate().back();


    }
    public void navigateForward(){
        driver.navigate().back();

    }
    public String getTextByCss(String locator){
        String st=driver.findElement(By.cssSelector(locator)).getText();
        return st;
    }
    public String getTextById(String locator){
        return driver.findElement(By.id(locator)).getText();
    }
    public String getTextByName(String locator){
        String st=driver.findElement(By.name(locator)).getText();
        return st;
    }

    public void mouseHoverByCss(String locator){
        try{
            WebElement element=driver.findElement(By.cssSelector(locator));
            Actions action=new Actions(driver);
            Actions hover=action.moveToElement(element);
        }catch (Exception ex){
            System.out.println("First  attempt has been done,This is second try");
            WebElement element=driver.findElement(By.cssSelector(locator));
            Actions action=new Actions(driver);
            action.moveToElement(element).perform();
        }
    }

    public void mouseHoverByXpath(String locator){
        try{
            WebElement element=driver.findElement(By.xpath(locator));
            Actions action=new Actions(driver);
            Actions hover=action.moveToElement(element);
        }
        catch (Exception ex){
            System.out.println("First attempt has been done,This is second try");
            WebElement element=driver.findElement(By.cssSelector(locator));
            Actions action=new Actions(driver);
            action.moveToElement(element).perform();

        }
        //handling alert
        public void okAlert(){
            Alert alert=driver.switchTo().alert();
        alert.accept();
    }

    public void cancelAlert(){
        Alert alert=driver.switchTo().alert();
        alert.dismiss();

    }
    //iframe handle
    public void iframeHandle(WebElement element){
        driver.switchTo().frame(element);

    }
    public void goBackToHomeWindow(){
        driver.switchTo().defaultContent();

    }
    //get links
    public void getLinks(String locator){
        driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
    }
//taking Screen Shot
    public void takeScreenShots() throws IOException{
        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("screenShots.png"));

    }
    //Synchromization
    public void waitUntilClickAble(By locator){
        WebDriverWait wait =new WebDriverWait(driver,10);
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }
public void waitUntilSelectable(By locator){
    WebDriverWait wait=new WebDriverWait(driver,10);
    boolean element=wait.until(ExpectedConditions.elementToBeSelected(locator));
    
}

    }
}