package Bidding.Entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BiddingSystem {
    private String companyName;
    private Integer bidPrice;
    private String metalName;
}
