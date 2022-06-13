package Bidding.Impl;


import Bidding.BiddingService;
import Bidding.Entity.BiddingSystem;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BiddingServiceImplTest {

    @Autowired
    BiddingService biddingService;

    @Test
    public void getDataHourlyNotFoundTest() {

        Assert.assertEquals(biddingService.getDataHourly().getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void getDataAtEndNotFoundTest() {
        Assert.assertEquals(biddingService.getDataAtEnd().getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void processingBidNoCompanyExistTest() {
        BiddingSystem bidObj = new BiddingSystem();
        bidObj.setCompanyName("K");
        Assert.assertEquals(biddingService.processingBid(bidObj).getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void processingBidCompanyExistTest() {
        BiddingSystem bidObj = new BiddingSystem();
        bidObj.setCompanyName("A");
        Assert.assertEquals(biddingService.processingBid(bidObj).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void saveDataHighestBidTtest() {
        BiddingSystem bidObj = new BiddingSystem();
        bidObj.setCompanyName("A");
        bidObj.setMetalName("Gold");
        bidObj.setBidPrice(21);
        Assert.assertEquals(biddingService.processingBid(bidObj).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void saveDataLowestBidTest() {
        BiddingSystem bidObj = new BiddingSystem();
        bidObj.setCompanyName("A");
        bidObj.setMetalName("Gold");
        bidObj.setBidPrice(21);
        biddingService.processingBid(bidObj);
        bidObj.setCompanyName("A");
        bidObj.setMetalName("Gold");
        bidObj.setBidPrice(22);
        Assert.assertEquals(biddingService.processingBid(bidObj).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void saveDataLowesthigheshBidTest() {
        BiddingSystem bidObj = new BiddingSystem();
        bidObj.setCompanyName("A");
        bidObj.setMetalName("Gold");
        bidObj.setBidPrice(21);
        biddingService.processingBid(bidObj);
        bidObj.setCompanyName("A");
        bidObj.setMetalName("Gold");
        bidObj.setBidPrice(22);
        biddingService.processingBid(bidObj);
        bidObj.setCompanyName("A");
        bidObj.setMetalName("Gold");
        bidObj.setBidPrice(23);
        Assert.assertEquals(biddingService.processingBid(bidObj).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void saveDataFirstLowesthigheshBidTest() {
        BiddingSystem bidObj = new BiddingSystem();
        bidObj.setCompanyName("A");
        bidObj.setMetalName("Gold");
        bidObj.setBidPrice(21);
        biddingService.processingBid(bidObj);
        bidObj.setCompanyName("A");
        bidObj.setMetalName("Gold");
        bidObj.setBidPrice(22);
        biddingService.processingBid(bidObj);
        bidObj.setCompanyName("A");
        bidObj.setMetalName("Gold");
        bidObj.setBidPrice(17);
        Assert.assertEquals(biddingService.processingBid(bidObj).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void saveDataLowesthigheshDiffOrderBidTest() {
        BiddingSystem bidObj = new BiddingSystem();
        bidObj.setCompanyName("A");
        bidObj.setMetalName("Gold");
        bidObj.setBidPrice(21);
        biddingService.processingBid(bidObj);
        bidObj.setCompanyName("A");
        bidObj.setMetalName("Gold");
        bidObj.setBidPrice(19);
        biddingService.processingBid(bidObj);
        bidObj.setCompanyName("A");
        bidObj.setMetalName("Gold");
        bidObj.setBidPrice(23);
        Assert.assertEquals(biddingService.processingBid(bidObj).getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void zgetDataHourlyTest() {
        Assert.assertEquals(biddingService.getDataHourly().getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void zgetDataAtEndTest() {
        Assert.assertEquals(biddingService.getDataAtEnd().getStatusCode(), HttpStatus.OK);
    }


}
