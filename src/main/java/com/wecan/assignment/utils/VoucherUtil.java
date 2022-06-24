package com.wecan.assignment.utils;

import com.wecan.assignment.common.RedemptionType;
import com.wecan.assignment.services.impl.RedemptionServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoucherUtil {

    private static final Logger logger = LoggerFactory.getLogger(VoucherUtil.class);
    /*
     *       THIS FUNCTION USED TO CHECK AVAILABILITY OF A VOUCHER
     *      - Return TRUE if:
     *          1. RedemptionType = SINGLE && redemptionTimes = 0
     *          2. RedemptionType = MULTIPLE
     *          3. RedemptionType = X_TIME && redemptionTimes < Number(redemptionValue)
     *          4. RedemptionType = BEFORE_CERTAIN_POINT_OF_TIME && Date.now() before Date(redemptionValue)
     *      - Otherwise return FALSE
     */

    public static boolean isVoucherAvailability(RedemptionType redemptionType, String redemptionValue, Integer redemptionTimes) {
        logger.info("Check Voucher Availability - redemptionType {}, redemptionValue {}, redemptionTimes {}",
                redemptionType.name(), redemptionValue, redemptionTimes);
        try {
            switch (redemptionType) {
                case SINGLE:
                    return redemptionTimes == 0;
                case MULTIPLE:
                    return true;
                case X_TIMES:
                    Integer redemptionIntValue = Integer.parseInt(redemptionValue);
                    return redemptionTimes != null ? redemptionTimes < redemptionIntValue : false;
                case BEFORE_CERTAIN_POINT_OF_TIME:
                    DateTime dt = DateTime.parse(redemptionValue);
                    return DateTime.now().isBefore(dt);
                default:
                    logger.error("Invalid Redemption Type");
                    return false;
            }
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }
}
