package Bidding.controller;


import Bidding.BiddingService;
import Bidding.Entity.BiddingSystem;
import Bidding.config.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalTime;
import java.util.Optional;


@RestController
//@RequestMapping("bidDetails")
public class BiddingController {

    @Autowired
    BiddingService biddingService;

    @PostMapping("/store")
    public ResponseEntity recieveBidsForMetals(@RequestBody BiddingSystem bidDetails) {

        if (!( (LocalTime.now().isAfter(LocalTime.parse(ApplicationConstant.START_TIME)))
                && (LocalTime.now().isBefore(LocalTime.parse(ApplicationConstant.END_TIME))) )) {
            return new ResponseEntity("Bid time should be in between 6AM & 6PM", HttpStatus.EXPECTATION_FAILED);
        }
        return (biddingService.processingBid(bidDetails));

    }

    @GetMapping("/completeData")
    public ResponseEntity getCompleteMetalDataAtEnd() {
        return biddingService.getDataAtEnd();
    }

    @GetMapping("/minMaxData")
    public ResponseEntity getCompleteMetalDataMinMax() {
        return biddingService.getDataHourly();
    }


}
