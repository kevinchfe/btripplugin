package com.fengchaoit.convert;

import com.fengchaoit.component.alibtrip.model.order.CarOrder;
import com.fengchaoit.utils.DateTimeFormatter;
import com.fengchaoit.utils.DateTimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
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
public interface CarOrderConvert {
    CarOrderConvert INSTANCE = Mappers.getMapper(CarOrderConvert.class);

    @Mappings({
            @Mapping(target = "publishTime", source = "publishTime", qualifiedByName = "mapStringToFormatString"),
            @Mapping(target = "takenTime", source = "takenTime", qualifiedByName = "mapStringToFormatString"),
            @Mapping(target = "driverConfirmTime", source = "driverConfirmTime", qualifiedByName = "mapStringToFormatString"),
            @Mapping(target = "cancelTime", source = "cancelTime", qualifiedByName = "mapStringToFormatString"),
            @Mapping(target = "payTime", source = "payTime", qualifiedByName = "mapStringToFormatString"),
            @Mapping(target = "applyShowId", source = "applyShowId", qualifiedByName = "mapApplyShowId"),
    })
    CarOrder convertBtripOrderToDwbgOrder(com.fengchaoit.webclient.btrip.model.order.CarOrder order);

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

    default String mapZeroToEmpty(Integer value) {
        if (value == null || value == 0) {
            return "";
        }
        return value.toString();
    }

    @Named("mapStringToFormatString")
    default String mapStringToFormatString(String value) {
        if (value == null) {
            return null;
        }
        return value.replace("T", " ").replace("Z", "");
    }

    @Named("mapApplyShowId")
    default String mapApplyShowId(String value) {
        if (value == null || "0".equals(value)) {
            return "";
        }
        return value;
    }

}
