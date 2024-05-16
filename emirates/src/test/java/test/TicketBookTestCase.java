package test;

import core.BaseClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.TicketBook;

public class TicketBookTestCase extends BaseClass {

    private TicketBook ticketBook;

    private SoftAssert softAssert;

@Test(description = "" , priority = 1)
    public void ticketBook(){
    ticketBook = new TicketBook(webDriver);
    softAssert = new SoftAssert();

    //The scenario and assertions for the page steps
    ticketBook.cookies();
    ticketBook.locateFrom();
    ticketBook.locateTo();
    ticketBook.chooseDate("August" , 18 , 31);
    ticketBook.choosePassengers(2 , 1);
    ticketBook.chooseClass();
    ticketBook.clickOnSearchFlight();
    softAssert.assertEquals(ticketBook.flightSite() , "You have been taken to our Saudi Arabia site");
    softAssert.assertTrue(ticketBook.lowestPrice());
    ticketBook.selectOutboundFlight();
    ticketBook.selectInboundFlight();
    ticketBook.clickContinue();
    softAssert.assertTrue(ticketBook.passengerTab().contains("current"));
    ticketBook.passengersInfo("Mr" , "omar" , "hantash" , "5" ,"May" , "1997" ,1  );
    ticketBook.passengersInfo("Miss" , "dana" , "hantash" , "5" ,"December" , "2000" ,2  );
    ticketBook.passengersInfo("Mr" , "khalid" , "hantash" , "5" ,"December" , "2012" ,3  );
    ticketBook.contact("jordan" , "0795154866" , "omarmohammad905@gmail.com");
    ticketBook.clickOnSummaryButton();
    softAssert.assertTrue(ticketBook.summaryPrice());
    softAssert.assertAll();
}
}
