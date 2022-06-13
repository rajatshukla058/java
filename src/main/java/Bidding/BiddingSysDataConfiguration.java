package Bidding;

import Bidding.Entity.BiddingSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

@Configuration
public class BiddingSysDataConfiguration {

    @Bean
    public List<String> listOfOrg() {
        final List<String> listOfOrg = new ArrayList<>();
        listOfOrg.add("A");
        listOfOrg.add("B");
        listOfOrg.add("C");
        listOfOrg.add("D");
        return listOfOrg;
    }


}
