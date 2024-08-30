package com.fengchaoit.convert;

import com.fengchaoit.component.alibtrip.model.bill.FlightBillSettlement;
import com.fengchaoit.webclient.btrip.model.bill.FlightBillSettlementRecord;
import com.fengchaoit.utils.DateTimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

/**
 * 机票账单转换
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 上午10:39 2024/8/30
 */
@Mapper
public interface FlightBillConvert {
    FlightBillConvert INSTANCE = Mappers.getMapper(FlightBillConvert.class);

    FlightBillSettlement convertBtripBillToDwbgBill(FlightBillSettlementRecord bill);

    default Long map(LocalDate value) {
        if (value == null) {
            return null;
        }
        return DateTimeUtils.dateTimeToMilliSecond(value.atStartOfDay());
    }
}