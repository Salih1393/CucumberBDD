package step_definitions;

import io.cucumber.java.DataTableType;
import testData.Coupon;

import java.util.Map;

public class CouponRegistry {

    // Using @DataTableType to map the data table to a Coupon object
    @DataTableType
    public Coupon transform(Map<String, String> entry) {
        // Extract values from the data table and construct a Coupon object
        String percentage = entry.get("percentage");
        String maxUse = entry.get("max use");
        String couponCode = entry.get("coupon code");
        String startDate = entry.get("start date");
        String endDate = entry.get("end date");

        // Make sure the required values are present and return the Coupon object
        if (percentage == null || maxUse == null || couponCode == null || startDate == null || endDate == null) {
            throw new IllegalArgumentException("Missing required coupon data");
        }

        return new Coupon(percentage, maxUse, couponCode, startDate, endDate);
    }
}
