package com.fengchaoit.convert;

import com.fengchaoit.component.alibtrip.model.order.TrainOrder;
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
public interface TrainOrderConvert {
    TrainOrderConvert INSTANCE = Mappers.getMapper(TrainOrderConvert.class);

    TrainOrder convertBtripOrderToDwbgOrder(com.fengchaoit.webclient.btrip.model.order.TrainOrder order);

    default Long map(LocalDateTime value) {
        if (value == null) {
            return null;
        }
        return DateTimeUtils.dateTimeToMilliSecond(value);
    }

}
