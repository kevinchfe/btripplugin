package com.fengchaoit.convert;

import com.fengchaoit.component.alibtrip.model.bill.HotelBillSettlement;
import com.fengchaoit.utils.DateTimeUtils;
import com.fengchaoit.webclient.btrip.model.bill.HotelBillSettlementRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 机票账单转换
 *
 * @author chenfei
 * @version 1.0
 * @since Created in 上午10:39 2024/8/30
 */
@Mapper
public interface HotelBillConvert {
    HotelBillConvert INSTANCE = Mappers.getMapper(HotelBillConvert.class);

    @Mapping(target = "checkInDate", source = "checkInDate", qualifiedByName = "mapStringToLong")
    @Mapping(target = "checkOutDate", source = "checkOutDate", qualifiedByName = "mapStringToLong")
    HotelBillSettlement convertBtripBillToDwbgBill(HotelBillSettlementRecord bill);

    /**
     * 转换字符串为Long
     * @param value 字符串
     * @return Long
     */
    @Named("mapStringToLong")
    default Long mapStringToLong(String value) {
        if (value == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(value, formatter);
        return DateTimeUtils.dateTimeToMilliSecond(dateTime);
    }

    default String mapToString(String value) {
        if (value == null) {
            return null;
        }
        return value.replace("T", " ").replace("Z", "");
    }

}
