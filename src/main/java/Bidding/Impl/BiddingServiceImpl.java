package Bidding.Impl;


import Bidding.BiddingService;
import Bidding.Entity.BiddingSystem;
import Bidding.config.ApplicationConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BiddingServiceImpl implements BiddingService {

    private final List<String> listOfOrg;
    private final static Map<String, HashMap<String, BiddingSystem>> BidDetailsInHoursObj
            = new HashMap<String, HashMap<String, BiddingSystem>>();

    private final static Map<String, HashMap<String, BiddingSystem>> BidDetailsMaxMinValObj
            = new HashMap<String, HashMap<String, BiddingSystem>>();

    @Autowired
    BiddingServiceImpl(List<String> listOfOrg) {
        this.listOfOrg = listOfOrg;
    }

    public ResponseEntity<Object> processingBid(BiddingSystem bidDetails) {

        Iterator<String> iter = listOfOrg.iterator();
        Boolean found = false;
        String inputCompanyName = bidDetails.getCompanyName();
        while (iter.hasNext()) {
            String companyName = iter.next();
            if (inputCompanyName.equals(companyName)) {
                found = true;
                break;
            }
        }
        if (!found) {
            return new ResponseEntity<Object>("This Company Not Exist In Record", HttpStatus.NOT_FOUND);
        } else {
            saveData(bidDetails);
            return new ResponseEntity<Object>("Data Saved Successfully ! ", HttpStatus.OK);
            //return new ResponseEntity<Object>(BidDetailsInHoursObj, HttpStatus.OK);

        }
    }

    void saveData(BiddingSystem bidDetails) {
        if (Optional.ofNullable(BidDetailsInHoursObj.get(bidDetails.getMetalName())).isPresent()) {
            HashMap<String, BiddingSystem> highestLowestBidInfo = BidDetailsInHoursObj.get(bidDetails.getMetalName());
            HashMap<String, BiddingSystem> highestLowestBidMinMaxInfo = BidDetailsMaxMinValObj.get(bidDetails.getMetalName());

            BiddingSystem highestBid = null;
            BiddingSystem lowestBid = null;
            BiddingSystem lowestBidMinMax = null;
            if (Optional.ofNullable(highestLowestBidInfo.get(ApplicationConstant.HIGHEST_BID)).isPresent()) {
                highestBid = highestLowestBidInfo.get(ApplicationConstant.HIGHEST_BID);
            }
            if (Optional.ofNullable(highestLowestBidInfo.get(ApplicationConstant.LOWEST_BID)).isPresent()) {
                lowestBid = highestLowestBidInfo.get(ApplicationConstant.LOWEST_BID);
            }

            if (Optional.ofNullable(highestLowestBidMinMaxInfo.get(ApplicationConstant.LOWEST_BID)).isPresent()) {
                lowestBidMinMax = highestLowestBidMinMaxInfo.get(ApplicationConstant.LOWEST_BID);
            }

            HashMap<String, BiddingSystem> lowhighBidMap = new HashMap<String, BiddingSystem>();
            HashMap<String, BiddingSystem> lowhighBidMinMaxMap = new HashMap<String, BiddingSystem>();
            if (Optional.ofNullable(highestBid).isPresent() && highestBid.getBidPrice() < bidDetails.getBidPrice()) {
                if (Optional.ofNullable(highestLowestBidInfo.get(ApplicationConstant.LOWEST_BID)).isPresent()) {
                    setBidValue(highestBid, bidDetails);
                    lowestBid = highestLowestBidInfo.get(ApplicationConstant.LOWEST_BID);
                    lowhighBidMap.put(ApplicationConstant.LOWEST_BID, lowestBid);
                    lowhighBidMap.put(ApplicationConstant.HIGHEST_BID, highestBid);
                    BidDetailsInHoursObj.put(bidDetails.getMetalName(), lowhighBidMap);
                    lowestBidMinMax = highestLowestBidMinMaxInfo.get(ApplicationConstant.LOWEST_BID);
                    lowhighBidMinMaxMap.put(ApplicationConstant.LOWEST_BID, lowestBidMinMax);
                    lowhighBidMinMaxMap.put(ApplicationConstant.HIGHEST_BID, highestBid);
                    BidDetailsMaxMinValObj.put(bidDetails.getMetalName(), lowhighBidMinMaxMap);

                } else {
                    int lowBid = highestBid.getBidPrice();
                    setBidValue(highestBid, bidDetails);
                    lowestBid = new BiddingSystem();
                    lowestBid.setBidPrice(lowBid);
                    lowestBid.setMetalName(highestBid.getMetalName());
                    lowestBid.setCompanyName(highestBid.getCompanyName());
                    lowhighBidMap.put(ApplicationConstant.LOWEST_BID, lowestBid);
                    lowhighBidMap.put(ApplicationConstant.HIGHEST_BID, highestBid);
                    BidDetailsInHoursObj.put(bidDetails.getMetalName(), lowhighBidMap);
                    lowestBidMinMax = new BiddingSystem();
                    lowestBidMinMax.setBidPrice(lowBid);
                    lowestBidMinMax.setMetalName(highestBid.getMetalName());
                    lowestBidMinMax.setCompanyName(highestBid.getCompanyName());
                    lowhighBidMinMaxMap.put(ApplicationConstant.LOWEST_BID, lowestBidMinMax);
                    lowhighBidMinMaxMap.put(ApplicationConstant.HIGHEST_BID, highestBid);
                    BidDetailsMaxMinValObj.put(bidDetails.getMetalName(), lowhighBidMinMaxMap);
                }
            } else {
                if (!Optional.ofNullable(lowestBid).isPresent()) {
                    lowestBid = new BiddingSystem();
                    lowestBidMinMax = new BiddingSystem();
                }
                setBidValue(lowestBid, bidDetails);
                lowhighBidMap.put(ApplicationConstant.LOWEST_BID, lowestBid);
                lowhighBidMap.put(ApplicationConstant.HIGHEST_BID, highestBid);
                BidDetailsInHoursObj.put(bidDetails.getMetalName(), lowhighBidMap);
                HashMap<String, BiddingSystem> lowestBidForMinMaxMap = BidDetailsMaxMinValObj.get(bidDetails.getMetalName());
                if (Optional.ofNullable(lowestBidForMinMaxMap.get(ApplicationConstant.LOWEST_BID)).isPresent()) {
                    BiddingSystem lowestBidForMinMax = lowestBidForMinMaxMap.get(ApplicationConstant.LOWEST_BID);
                    if (lowestBidForMinMax.getBidPrice() > bidDetails.getBidPrice()) {
                        setBidValue(lowestBidMinMax, bidDetails);
                        lowhighBidMinMaxMap.put(ApplicationConstant.LOWEST_BID, lowestBidMinMax);
                        lowhighBidMinMaxMap.put(ApplicationConstant.HIGHEST_BID, highestBid);
                        BidDetailsMaxMinValObj.put(bidDetails.getMetalName(), lowhighBidMinMaxMap);
                    }
                } /*else {
                    setBidValue(lowestBidMinMax, bidDetails);
                    lowhighBidMinMaxMap.put(ApplicationConstant.LOWEST_BID, lowestBidMinMax);
                    lowhighBidMinMaxMap.put(ApplicationConstant.HIGHEST_BID, highestBid);
                    BidDetailsMaxMinValObj.put(bidDetails.getMetalName(), lowhighBidMinMaxMap);
                }*/

            }
        } else {
            BiddingSystem highestBid = new BiddingSystem();
            setBidValue(highestBid, bidDetails);
            HashMap<String, BiddingSystem> highestBidMap = new HashMap<String, BiddingSystem>();
            highestBidMap.put(ApplicationConstant.HIGHEST_BID, highestBid);
            BidDetailsInHoursObj.put(bidDetails.getMetalName(), highestBidMap);
            BidDetailsMaxMinValObj.put(bidDetails.getMetalName(), highestBidMap);
        }
    }

    public ResponseEntity<Object> getDataHourly() {
        if (BidDetailsMaxMinValObj.isEmpty())
            return new ResponseEntity<Object>("No Max-Min Data found  !", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Object>(BidDetailsMaxMinValObj, HttpStatus.OK);
    }

    public ResponseEntity<Object> getDataAtEnd() {
        if (BidDetailsInHoursObj.isEmpty())
            return new ResponseEntity<Object>("No Data found In hourly basis !", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Object>(BidDetailsInHoursObj, HttpStatus.OK);

    }

    private void setBidValue(BiddingSystem highLowBid, BiddingSystem bidDetails) {
        highLowBid.setBidPrice(bidDetails.getBidPrice());
        highLowBid.setCompanyName(bidDetails.getCompanyName());
        highLowBid.setMetalName(bidDetails.getMetalName());
    }



}
