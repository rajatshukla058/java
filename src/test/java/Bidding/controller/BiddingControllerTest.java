package Bidding.controller;

import Bidding.BiddingService;
import Bidding.Entity.BiddingSystem;
import Bidding.controller.BiddingController;
import org.junit.Assert;
import org.junit.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class BiddingControllerTest {

    @InjectMocks
    private BiddingController biddingController;

    @Mock
    BiddingService biddingService;

    @Test
    public void getCompleteMetalDataAtEndTest() {
        Mockito.when(biddingService.getDataAtEnd()).thenReturn(new ResponseEntity<Object>(HttpStatus.OK));
        Assert.assertEquals(biddingController.getCompleteMetalDataAtEnd().getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getCompleteMetalDataMinMaxTest() {
        Mockito.when(biddingService.getDataHourly()).thenReturn(new ResponseEntity<Object>(HttpStatus.OK));
        Assert.assertEquals(biddingController.getCompleteMetalDataMinMax().getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void recieveBidsForMetalsTest() {
        BiddingSystem bidObj = new BiddingSystem();
        Mockito.when(biddingService.processingBid(bidObj)).thenReturn(new ResponseEntity<Object>(HttpStatus.OK));
        Assert.assertEquals(biddingController.recieveBidsForMetals(bidObj).getStatusCode(), HttpStatus.OK);
    }


}

