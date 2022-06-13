package Bidding;

import Bidding.Entity.BiddingSystem;
import org.springframework.http.ResponseEntity;

public interface BiddingService {
    public ResponseEntity<Object> processingBid(BiddingSystem bidDetails);
    public ResponseEntity<Object> getDataAtEnd() ;
    public ResponseEntity<Object> getDataHourly() ;
}
