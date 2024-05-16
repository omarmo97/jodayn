package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static core.BaseClass.webDriver;
import static helpers.ElementHelper.*;

public class TicketBook {
    public TicketBook(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement cookiesAcceptButton;
    @FindBy(xpath = "(//input[@name='Departure airport'])[1]")
    private WebElement from;

    @FindBy(xpath = "(//input[@name='Arrival airport'])[1]")
    private WebElement to;


    @FindBy(xpath = "(//div[@class='ek-datepicker__column-label label-month'])[1]")
    private WebElement leftMonthElement;

    @FindBy(xpath = "(//div[@class='ek-datepicker__column-label label-month'])[2]")
    private WebElement rightMonthElement;
    @FindBy(xpath = "(//button[text()='Next Month'])[1]")
    private WebElement nextMonthArrow;

    @FindBy(xpath = "//section[@class='location passenger-container']")
    private WebElement passengersDropDown;

    @FindBy(xpath = "//h3[@class='passenger-heading']")
    private WebElement passengerHeading;

    @FindBy(xpath = "(//span[@class='icon icon-plus'])[1]")
    private WebElement adultsPlusIcon;

    @FindBy(xpath = "(//span[@class='icon icon-plus'])[4]")
    private WebElement childPlusIcon;

    @FindBy(xpath = "(//span[@class='increment-field__value js-increment-value'])[1]")
    private WebElement adultsNumber;

    @FindBy(xpath = "(//span[@class='increment-field__value js-increment-value'])[4]")
    private WebElement childNumber;

    @FindBy(xpath = "//input[@name='search-flight-class']")
    private WebElement classSection;

    @FindBy(xpath = "(//p[text()='Business Class'])[1]")
    private WebElement classDropDown;

    @FindBy(xpath = "(//span[text()='Search flights'])[2]")
    private WebElement searchFlight;

    @FindBy(xpath = "//h2[@class='ts-rmsg__hdr']")
    private WebElement flightSite;

    @FindBy(xpath = "//p[@class='ts-lp__price low-currency summary-prise summary-curr-container Currency-Convert-Container']")
    private WebElement lowestPrice;

    @FindBy(css = "ts-fbr-option__trigger  tc-fbr-cursor-style")
    private List<WebElement> flightSelect;

    @FindBy(xpath = "//a[@onclick='javascript:return fnSubmit();']")
    private WebElement continueButton;

    @FindBy(xpath = "(//li[contains(@class ,'ts-pb__step ts-pb__step')])[2]")
    private WebElement passengerTab;


    @FindBy(xpath = "//input[@name='ctl00$c$Cont$txtCountryCode1']")
    private WebElement countryField;

    @FindBy(xpath = "//input[@name='ctl00$c$Cont$txtContactNum1']")
    private WebElement mobileField;

    @FindBy(xpath = "//input[@name='ctl00$c$Cont$txtEmail']")
    private WebElement emailField;

    @FindBy(xpath = "(//span[contains(text() , 'Save & Next')])[1]")
    private WebElement saveAmdNextButton;

    @FindBy(xpath = "//a[@title='Review your selection']")
    private WebElement summaryButton;

    @FindBy(xpath = "//p[@class='ts-sc__total ts-sc__total--right sbox-flight-total']/child::strong")
    private WebElement summaryPrice;

    @FindBy(xpath = "//strong[@class='sbox-totalfare']")
    private WebElement summaryPriceBar;

    @FindBy(xpath = "//a[contains(@id,'option-0')]")
    private List<WebElement> outboundList;
    @FindBy(xpath = "//a[contains(@id,'option-1')]")
    private List<WebElement> inboundList;

    @FindBy(xpath = "//tbody[@contentset='true']//following-sibling::tbody//span[not(contains(text(),'Selected')) and (contains(text(),'Select'))]")
    private List<WebElement> selectButton;


     // To accept the cookies
    public void cookies() {
        if (elementToBeVisible(cookiesAcceptButton).isDisplayed()) {
            this.cookiesAcceptButton.click();

        }
    }

    //To fill the departure airport field
    public void locateFrom(){
        elementToBeVisible(this.from);
        this.from.click();
        this.from.sendKeys("riyadh");
        waitTime(1000);
        this.from.sendKeys(Keys.ENTER);
        waitTime(1000);

    }

    //To fill the arrival airport field
    public void locateTo(){
        elementToBeVisible(this.to);
        this.to.sendKeys("amman");
        waitTime(1000);
        this.to.sendKeys(Keys.ENTER);
        waitTime(1000);
    }

    //To choose the required date (from 18'th of august to 31 of august)
    public void chooseDate(String desiredMonth , int desiredFromDay , int desiredToDay){
        elementToBeVisible(this.rightMonthElement);

        boolean found = false;
        while (!found) {
            String leftMonth = leftMonthElement.getText();
            String rightMonth = rightMonthElement.getText();

            if (leftMonth.contains("August") || rightMonth.contains("August")) {
                found = true;
                break;
            }
            nextMonthArrow.click();

            waitTime(1000);
        }
        waitTime(5000);
        WebElement fromDay = webDriver.findElement(By.xpath("(//div[text()=' August']//parent::div//following-sibling::table//td[@data-year='2024']//button[text()='"+desiredFromDay+"'])[1]"));
        fromDay.click();
        waitTime(6000);
        WebElement toDay = webDriver.findElement(By.xpath("(//div[text()=' August']//parent::div//following-sibling::table//td[@data-year='2024']//button[text()='"+desiredToDay+"'])[1]"));
        toDay.click();
        waitTime(1000);
    }

    //To choose the required adults value , and couldn't be with a select dropdown method because it's not a select locator so it cant be done with it
    public void chooseAdultValue(int desiredAdultsValue) {
        int currentAdultsValue = Integer.parseInt(adultsNumber.getText());
        elementToBeVisible(this.passengerHeading);

        for (int y = currentAdultsValue; y < desiredAdultsValue; y++) {
            if (currentAdultsValue < desiredAdultsValue) {
                this.adultsPlusIcon.click();
                System.out.printf("" + desiredAdultsValue);
                waitTime(2000);
            }
        }
    }

    //To choose the required child value , and couldn't be with a select dropdown method because it's not a select locator so it cant be done with it
    public void chooseChildValue(int desiredChildValue) {
        int currentChildValue = Integer.parseInt(childNumber.getText());
        elementToBeVisible(this.passengerHeading);

        for (int y = currentChildValue; y < desiredChildValue; y++) {
            if (currentChildValue < desiredChildValue) {
                this.childPlusIcon.click();
                System.out.printf("" + currentChildValue);
                waitTime(2000);
            }
        }
    }

    //A method to apply the previous 2 methods
     public void choosePassengers(int desiredAdultsValue ,int desiredChildValue ){
        chooseAdultValue(desiredAdultsValue);
        waitTime(2000);
        chooseChildValue(desiredChildValue);
    }

    //To choose a business class or any required level
    public void chooseClass(){
        elementToBeVisible(this.classSection);
        this.classSection.click();
        elementToBeVisible(this.classDropDown).click();
    }

    //To click on search flight button
    public void clickOnSearchFlight(){
        elementToBeVisible(this.searchFlight);
        this.searchFlight.click();
        waitTime(3000);
    }

    //To assert that the flight site is on SA
    public String flightSite(){
        elementToBeVisible(this.flightSite);
        String site = this.flightSite.getText();
        return site;
    }

    //To assert that the lower price is displayed
    public boolean lowestPrice(){
        elementToBeVisible(this.lowestPrice);
        return lowestPrice.isDisplayed();
    }
     //To select flexibility
    public void selectFlexibility(){
        Random random = new Random();
        waitTime(5000);
        int randomInt = random.nextInt(selectButton.size());
        waitTime(2000);
        selectButton.get(randomInt).click();
    }

    //A method to select a flight ticket
    public void selectInboundFlight(){
            waitTime(5000);
            Random random = new Random();
            int randomInt = random.nextInt(inboundList.size());
            waitTime(2000);
            inboundList.get(randomInt).click();
            waitTime(2000);
            selectFlexibility();
        }

    public void selectOutboundFlight(){
        waitTime(5000);
        Random random = new Random();
        int randomInt = random.nextInt(outboundList.size());
        waitTime(2000);
        outboundList.get(randomInt).click();
        waitTime(2000);
        selectFlexibility();
    }
    //To click on continue button
    public void clickContinue(){
        moveToElement(this.continueButton);
        elementToBeVisible(this.continueButton);
        this.continueButton.click();
    }

    //To assert that the user is on the passenger tab
    public String passengerTab(){
        elementToBeVisible(this.passengerTab);
        return this.passengerTab.getAttribute("class");
    }

    //To fill all the passengers info
    public void passengersInfo(String title , String firstName ,String lastName , String day , String month , String year , int index){

            WebElement titlePath = webDriver.findElement(By.xpath("(//div[@class='table-shadow-holder ts-form'])["+index+"]//input[@class= 'width-232 no-margin texttitle']"));
            WebElement firstNamePath = webDriver.findElement(By.xpath("(//div[@class='table-shadow-holder ts-form'])["+index+"]//input[@data-required-msg='Please enter your first name.']"));
            WebElement lastNamePath = webDriver.findElement(By.xpath("(//div[@class='table-shadow-holder ts-form'])["+index+"]//input[@data-required-msg='Please enter your last name.']"));
            WebElement dayFieldPath = webDriver.findElement(By.xpath("(//div[@class='table-shadow-holder ts-form'])["+index+"]//label[@id='lbltxtDay']"));
            WebElement monthFieldPath = webDriver.findElement(By.xpath("(//div[@class='table-shadow-holder ts-form'])["+index+"]//label[@id='lbltxtMonth']"));
            WebElement yearFieldPath = webDriver.findElement(By.xpath("(//div[@class='table-shadow-holder ts-form'])["+index+"]//label[@id='lbltxtYear']"));

            JavascriptExecutor js = (JavascriptExecutor) webDriver;

            scrollToElement(titlePath);
            titlePath.click();
            webDriver.findElement(By.xpath("(//div[@class='table-shadow-holder ts-form'])["+index+"]//li[text()='"+title+"']")).click();
            waitTime(1000);
            titlePath.sendKeys(Keys.ENTER);
            waitTime(2000);
            firstNamePath.sendKeys(firstName);
            waitTime(2000);
            lastNamePath.sendKeys(lastName);
            waitTime(2000);
            dayFieldPath.click();
            waitTime(1000);
            webDriver.findElement(By.xpath("(//div[@class='table-shadow-holder ts-form'])["+index+"]//li[text()='"+day+"']")).click();
            waitTime(2000);
            monthFieldPath.click();
            webDriver.findElement(By.xpath("(//div[@class='table-shadow-holder ts-form'])["+index+"]//li[text()='"+month+"']")).click();
            waitTime(2000);
            yearFieldPath.click();
            webDriver.findElement(By.xpath("(//div[@class='table-shadow-holder ts-form'])["+index+"]//li[text()='"+year+"']")).click();
            waitTime(3000);
            webDriver.findElement(By.xpath("(//div[@class='table-shadow-holder ts-form'])["+index+"]//span[contains(text() , 'Save & Next')]")).click();
            waitTime(3000);
            js.executeScript("window.scrollBy(0, -200);");


    }

//    To fill the contact fields
    public void contact(String country , String mobile , String email){
        elementToBeVisible(this.countryField);
        this.countryField.sendKeys(country);
        waitTime(1000);
        this.countryField.sendKeys(Keys.ENTER);
        webDriver.findElement(By.xpath("(//div[@class='empty-passenger-details'])[4]")).click();
        waitTime(1000);
        elementToBeVisible(this.mobileField);
        this.mobileField.sendKeys(mobile);
        waitTime(1000);
        elementToBeVisible(this.emailField);
        this.emailField.sendKeys(email);
        waitTime(1500);
    }

    //To click on summary button
    public void clickOnSummaryButton(){
        elementToBeVisible(this.summaryButton);
        this.summaryButton.click();
    }

    //To assert the summary in the view summary is the same as the summary shown in the bar
    public boolean summaryPrice(){
        elementToBeVisible(this.summaryPrice);
        System.out.println(summaryPrice.getText());
        return this.summaryPrice.getText().equalsIgnoreCase(summaryPriceBar.getText());
    }
}
