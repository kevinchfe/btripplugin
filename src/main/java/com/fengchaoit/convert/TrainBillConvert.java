package com.fengchaoit.convert;

import com.fengchaoit.component.alibtrip.model.bill.TrainBillSettlement;
import com.fengchaoit.utils.DateTimeUtils;
import com.fengchaoit.webclient.btrip.model.bill.TrainBillSettlementRecord;
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
public interface TrainBillConvert {
    TrainBillConvert INSTANCE = Mappers.getMapper(TrainBillConvert.class);

    TrainBillSettlement convertBtripBillToDwbgBill(TrainBillSettlementRecord bill);

    default Long map(LocalDate value) {
        if (value == null) {
            return null;
        }
        return DateTimeUtils.dateTimeToMilliSecond(value.atStartOfDay());
    }

    // 将字符串2024-08-29T09:51Z转为2024-08-29 09:51
    default String mapToString(String value) {
        if (value == null) {
            return null;
        }
        if (value.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}Z")) {
            return value.replace("T", " ").replace("Z", "");
        } else {
            return value;
        }

//        return value.replace("T", " ").replace("Z", "");
    }
}
