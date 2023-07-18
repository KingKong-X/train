package com.xingyuan.train.business.mapper.cust;

import java.util.Date;

/**
 * @author Xingyuan Huang
 * @since 2023/7/18 10:33
 */
public interface DailyTrainTicketMapperCust {
    void updateCountBySell(Date date, String trainCode, String seatTypeCode, Integer minStartIndex, Integer maxStartIndex, Integer minEndIndex, Integer maxEndIndex);
}
