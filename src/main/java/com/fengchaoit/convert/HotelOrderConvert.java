package com.fengchaoit.convert;

import com.fengchaoit.component.alibtrip.model.order.HotelOrder;
import com.fengchaoit.utils.DateTimeFormatter;
import com.fengchaoit.utils.DateTimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 机票订单转换
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 下午4:31 2024/8/28
 */
@Mapper
public interface HotelOrderConvert {
    HotelOrderConvert INSTANCE = Mappers.getMapper(HotelOrderConvert.class);

    HotelOrder convertBtripOrderToDwbgOrder(com.fengchaoit.webclient.btrip.model.order.HotelOrder order);

    default Long map(LocalDateTime value) {
        if (value == null) {
            return null;
        }
        return DateTimeUtils.dateTimeToMilliSecond(value);
    }

    default String mapToString(LocalDateTime value) {
        if (value == null) {
            return null;
        }
        return DateTimeFormatter.dateTimeToString(value);
    }

    // 将Integer或者Long类型的为0的值转为空字符串
    default String mapZeroToEmpty(Integer value) {
        if (value == null || value == 0) {
            return "";
        }
        return value.toString();
    }
}
