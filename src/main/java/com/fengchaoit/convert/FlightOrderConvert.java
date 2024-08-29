package com.fengchaoit.convert;

import com.fengchaoit.component.alibtrip.model.order.FlightOrder;
import com.fengchaoit.utils.DateTimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

/**
 * 机票订单转换
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午4:31 2024/8/28
 */
@Mapper
public interface FlightOrderConvert {
    FlightOrderConvert INSTANCE = Mappers.getMapper(FlightOrderConvert.class);

    FlightOrder convertBtripOrderToDwbgOrder(com.fengchaoit.webclient.btrip.model.order.FlightOrder order);

    default Long map(LocalDateTime value) {
        if (value == null) {
            return null;
        }
        return DateTimeUtils.dateTimeToMilliSecond(value);
    }

}
