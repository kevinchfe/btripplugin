package com.fengchaoit.controller;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.Constant;
import com.fengchaoit.component.alibtrip.model.bill.*;
import com.fengchaoit.component.alibtrip.model.order.CarOrder;
import com.fengchaoit.component.alibtrip.model.order.FlightOrder;
import com.fengchaoit.component.alibtrip.model.order.HotelOrder;
import com.fengchaoit.component.alibtrip.model.order.TrainOrder;
import com.fengchaoit.component.alibtrip.param.BillSettlementParam;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
import com.fengchaoit.component.feishu.datasync.model.Field;
import com.fengchaoit.component.feishu.datasync.model.PrimaryKey;
import com.fengchaoit.component.feishu.datasync.model.TableData;
import com.fengchaoit.component.feishu.datasync.model.TableMeta;
import com.fengchaoit.convert.*;
import com.fengchaoit.dto.order.CarPriceDto;
import com.fengchaoit.dto.order.FlightPriceDto;
import com.fengchaoit.dto.order.HotelPriceDto;
import com.fengchaoit.dto.order.TrainPriceDto;
import com.fengchaoit.entity.Data;
import com.fengchaoit.entity.Order;
import com.fengchaoit.entity.PrimaryOrder;
import com.fengchaoit.exception.BusinessException;
import com.fengchaoit.model.Meta;
import com.fengchaoit.model.SyncType;
import com.fengchaoit.param.TableMetaParam;
import com.fengchaoit.starter.mvc.annotation.ResponseUnWrapper;
import com.fengchaoit.starter.mvc.vo.Result;
import com.fengchaoit.utils.DateTimeFormatter;
import com.fengchaoit.webclient.btrip.AliBtripAccountHolder;
import com.fengchaoit.webclient.btrip.AliBtripApi;
import com.fengchaoit.webclient.btrip.model.order.OrderResult;
import com.fengchaoit.webclient.btrip.param.order.OrderListQueryParam;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 飞书插件控制器
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 17:47 2024/8/19
 */
@Slf4j
@RestController
@RequestMapping("/plugin/connector")
public class PluginController {
    private final AliBtripApi aliBtripApi;
    private final StringRedisTemplate stringRedisTemplate;

    public PluginController(AliBtripApi aliBtripApi, StringRedisTemplate stringRedisTemplate) {
        this.aliBtripApi = aliBtripApi;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 获取插件元数据配置信息
     *
     * @return 元数据配置信息
     */
    @ResponseUnWrapper
    @GetMapping("/meta.json")
    public Meta meta() {
        return Meta.create()
                .extraData(builder -> {
                    builder.syncType(SyncType.TRIGGER);
//                    builder.dataSourceConfigUiUri("https://ext.baseopendev.com/ext/data-sync-fe-demo/c70fa2864a002386423f26411f21a3c674bc2f9c/index.html");
//                    builder.dataSourceConfigUiUri("https://developer-ld.fengchaoit.com/#/newchannel");
                    builder.dataSourceConfigUiUri("https://dev.fengchaoit.com/dwbgPlugin/#/newchannel");
                    builder.initHeight(510);
                    builder.initWeight(520);
                })
                .protocol(builder -> {
                    builder.type("http");
                    builder.appendUri("tableMeta", "/table_meta");
                    builder.appendUri("records", "/records");
                    builder.appendUri("subscribeOrNot", "/subscribeOrNot");
                    builder.appendUri("getRecordByPrimaryKeys", "/getRecordByPrimaryKeys");
                })
                .build();
    }

    /**
     * 获取表字段信息
     *
     * @param param 请求对象
     * @return 数据
     */
    @PostMapping(value = "/table_meta")
    public Result tableMeta(@RequestBody TableMetaParam param) {
        return process(param, (RecordHandler) (category, type, startTime, corpName, pageToken, pageSize) -> {
            if (category == Constant.FEISHU_BILL) {
                return switch (type) {
                    case 1 -> {
                        List<Field> fields = processTableMeta(FlightBillSettlement.class);
                        TableMeta tableMeta = TableMeta.of(corpName + "机票账单", fields);
                        yield Result.success().data(tableMeta).build();
                    }
                    case 2 -> {
                        List<Field> fields = processTableMeta(HotelBillSettlement.class);
                        TableMeta tableMeta = TableMeta.of(corpName + "酒店账单", fields);
                        yield Result.success().data(tableMeta).build();
                    }
                    case 3 -> {
                        List<Field> fields = processTableMeta(TrainBillSettlement.class);
                        TableMeta tableMeta = TableMeta.of(corpName + "火车账单", fields);
                        yield Result.success().data(tableMeta).build();
                    }
                    case 4 -> {
                        List<Field> fields = processTableMeta(CarBillSettlement.class);
                        TableMeta tableMeta = TableMeta.of(corpName + "用车账单", fields);
                        yield Result.success().data(tableMeta).build();
                    }
                    default -> Result.fail("未找到对应类型").build();
                };
            } else if (category == Constant.FEISHU_ORDER) {
                return switch (type) {
                    case 1 -> {
                        List<Field> fields = processTableMeta(FlightOrder.class);
                        TableMeta tableMeta = TableMeta.of(corpName + "机票订单", fields);
                        yield Result.success().data(tableMeta).build();
                    }
                    case 2 -> {
                        List<Field> fields = processTableMeta(HotelOrder.class);
                        TableMeta tableMeta = TableMeta.of(corpName + "酒店订单", fields);
                        yield Result.success().data(tableMeta).build();
                    }
                    case 3 -> {
                        List<Field> fields = processTableMeta(TrainOrder.class);
                        TableMeta tableMeta = TableMeta.of(corpName + "火车订单", fields);
                        yield Result.success().data(tableMeta).build();
                    }
                    case 4 -> {
                        List<Field> fields = processTableMeta(CarOrder.class);
                        TableMeta tableMeta = TableMeta.of(corpName + "用车订单", fields);
                        yield Result.success().data(tableMeta).build();
                    }
                    default -> Result.fail("未找到对应类型").build();
                };
            }
            return Result.fail("未找到对应类型").build();
        });
    }

    /**
     * 获取表记录
     *
     * @return 数据
     */
    @PostMapping("/records")
    public Result records(@RequestBody TableMetaParam param) {
        return process(param, (RecordHandler) (category, type, startTime, corpName, pageToken, pageSize) -> {
            int page = NumberUtils.toInt(pageToken, 1);

            if (category == Constant.FEISHU_BILL) { // 账单
                LocalDateTime endTime;
                List<Field> fields;
                List<SettlementRecord> allData = new ArrayList<>();
                TableData.Builder tableDataBuilder = TableData.create().hasMore(false);

                LocalDateTime targetDate = startTime.plusMonths(1);
                if (targetDate.isAfter(LocalDateTime.now().minusHours(2))) {
                    targetDate = LocalDateTime.now().minusHours(2);
                }
//                targetDate = LocalDateTime.of(2024, 8, 20, 0, 0, 0);
                do {
                    endTime = startTime.plusDays(1);
                    System.out.println("startTime = " + startTime);
                    System.out.println("endTime = " + endTime);
                    // endTime只能小于等于当前时间减两小时
                    endTime = endTime.isAfter(LocalDateTime.now().minusHours(2)) ? LocalDateTime.now().minusHours(2) : endTime;
                    com.fengchaoit.webclient.btrip.model.bill.BillSettlement<?> billSettlement;
                    switch (type) {
                        case 1 -> {
                            billSettlement = billSettlementQuery(aliBtripApi::flightBillSettlement,
                                    startTime, endTime, page, pageSize, "机票");
                            List<com.fengchaoit.webclient.btrip.model.bill.FlightBillSettlementRecord> records = (List<com.fengchaoit.webclient.btrip.model.bill.FlightBillSettlementRecord>) billSettlement.getRecords();
                            List<FlightBillSettlement> dwbgBills = records.stream().map(FlightBillConvert.INSTANCE::convertBtripBillToDwbgBill).toList();
                            fields = processTableMeta(FlightBillSettlement.class);
                            allData.addAll(dwbgBills);
                        }
                        case 2 -> {
                            billSettlement = billSettlementQuery(aliBtripApi::hotelBillSettlement,
                                    startTime, endTime, page, pageSize, "酒店");
                            List<com.fengchaoit.webclient.btrip.model.bill.HotelBillSettlementRecord> records = (List<com.fengchaoit.webclient.btrip.model.bill.HotelBillSettlementRecord>) billSettlement.getRecords();
                            List<HotelBillSettlement> dwbgBills = records.stream().map(HotelBillConvert.INSTANCE::convertBtripBillToDwbgBill).toList();
                            fields = processTableMeta(HotelBillSettlement.class);
                            allData.addAll(dwbgBills);
                        }
                        case 3 -> {
                            billSettlement = billSettlementQuery(aliBtripApi::trainBillSettlement,
                                    startTime, endTime, page, pageSize, "火车");
                            List<com.fengchaoit.webclient.btrip.model.bill.TrainBillSettlementRecord> records = (List<com.fengchaoit.webclient.btrip.model.bill.TrainBillSettlementRecord>) billSettlement.getRecords();
                            List<TrainBillSettlement> dwbgBills = records.stream().map(TrainBillConvert.INSTANCE::convertBtripBillToDwbgBill).toList();
                            fields = processTableMeta(TrainBillSettlement.class);
                            allData.addAll(dwbgBills);
                        }
                        case 4 -> {
                            billSettlement = billSettlementQuery(aliBtripApi::carBillSettlement,
                                    startTime, endTime, page, pageSize, "用车");
                            List<com.fengchaoit.webclient.btrip.model.bill.CarBillSettlementRecord> records = (List<com.fengchaoit.webclient.btrip.model.bill.CarBillSettlementRecord>) billSettlement.getRecords();
                            List<CarBillSettlement> dwbgBills = records.stream().map(CarBillConvert.INSTANCE::convertBtripBillToDwbgBill).toList();
                            fields = processTableMeta(CarBillSettlement.class);
                            allData.addAll(dwbgBills);
                        }
                        default -> {
                            return Result.fail("未找到对应类型").build();
                        }
                    }
                    startTime = endTime;
//                } while (startTime.isBefore(LocalDateTime.now().minusDays(1)));
                } while (startTime.isBefore(targetDate));
                List<PrimaryKey> items = allData.stream().map(it -> (PrimaryKey) it).toList();
                TableData tableData = processRecordToData(tableDataBuilder, fields, items, category, type);
                return Result.success().data(tableData).build();
            } else { // 订单
                LocalDateTime endTime = LocalDateTime.now();
                TableData.Builder tableDataBuilder = TableData.create().hasMore(false);
                switch (type) {
                    case 1 -> {
                        OrderResult<List<com.fengchaoit.webclient.btrip.model.order.FlightOrder>> order = aliBtripApi.flightOrderListQuery(OrderListQueryParam.builder()
                                .updateStartTime(DateTimeFormatter.dateTimeToString(startTime))
                                .updateEndTime(DateTimeFormatter.dateTimeToString(endTime))
                                .page(Math.max(1, page))
                                .pageSize(pageSize)
                                .build());

                        List<com.fengchaoit.webclient.btrip.model.order.FlightOrder> records = order.getModule();
                        if (order.getPageInfo() != null) {
                            // 判断是否有下一页
                            boolean hasMore = order.getPageInfo().getTotalNumber() > pageSize * page;
                            if (hasMore) {
                                tableDataBuilder.hasMore(true).nextPageToken(String.valueOf(page + 1));
                            }
                        }
                        List<FlightOrder> dwbgOrders = records.stream().filter(record -> record.getId() != null).map(FlightOrderConvert.INSTANCE::convertBtripOrderToDwbgOrder).toList();
                        // 循环records取出priceInfoList并赋值给flightPriceDtos
                        List<FlightPriceDto> flightPriceDtos = new ArrayList<>();
                        for (com.fengchaoit.webclient.btrip.model.order.FlightOrder record : records) {
                            List<com.fengchaoit.webclient.btrip.model.order.FlightOrder.PriceInfoListDTO> priceInfoList = record.getPriceInfoList();
                            for (com.fengchaoit.webclient.btrip.model.order.FlightOrder.PriceInfoListDTO price : priceInfoList) {
                                FlightPriceDto flightPriceDto = getFlightPriceDto(record, price);
                                flightPriceDtos.add(flightPriceDto);
                            }
                        }
                        for (FlightOrder dwbgOrder : dwbgOrders) {
                            setFlightOrderPrice(dwbgOrder, flightPriceDtos);
                        }

                        List<Field> fields = processTableMeta(dwbgOrders.get(0).getClass());
                        List<PrimaryKey> items = dwbgOrders.stream().filter(it -> it.getId() != null).map(it -> (PrimaryKey) it).toList();
                        TableData tableData = processRecordToData(tableDataBuilder, fields, items, category, type);
                        return Result.success().data(tableData).build();
                    }
                    case 2 -> {
                        OrderResult<List<com.fengchaoit.webclient.btrip.model.order.HotelOrder>> order = aliBtripApi.hotelOrderListQuery(OrderListQueryParam.builder()
                                .updateStartTime(DateTimeFormatter.dateTimeToString(startTime))
                                .updateEndTime(DateTimeFormatter.dateTimeToString(endTime))
                                .page(Math.max(1, page))
                                .pageSize(pageSize)
                                .build());

                        List<com.fengchaoit.webclient.btrip.model.order.HotelOrder> records = order.getModule();
                        if (order.getPageInfo() != null) {
                            // 判断是否有下一页
                            boolean hasMore = order.getPageInfo().getTotalNumber() > pageSize * page;
                            if (hasMore) {
                                tableDataBuilder.hasMore(true).nextPageToken(String.valueOf(page + 1));
                            }
                        }

                        List<HotelOrder> dwbgOrders = records.stream().filter(record -> record.getId() != null).map(HotelOrderConvert.INSTANCE::convertBtripOrderToDwbgOrder).toList();

                        List<HotelPriceDto> hotelPriceDtos = new ArrayList<>();
                        for (com.fengchaoit.webclient.btrip.model.order.HotelOrder record : records) {
                            List<com.fengchaoit.webclient.btrip.model.order.HotelOrder.PriceInfoListDTO> priceInfoList = record.getPriceInfoList();
                            for (com.fengchaoit.webclient.btrip.model.order.HotelOrder.PriceInfoListDTO price : priceInfoList) {
                                HotelPriceDto hotelPriceDto = getHotelPriceDto(record, price);
                                hotelPriceDtos.add(hotelPriceDto);
                            }
                        }
                        for (HotelOrder dwbgOrder : dwbgOrders) {
                            setHotelOrderPrice(dwbgOrder, hotelPriceDtos);
                        }

                        List<Field> fields = processTableMeta(dwbgOrders.get(0).getClass());
                        List<PrimaryKey> items = dwbgOrders.stream().filter(it -> it.getId() != null).map(it -> (PrimaryKey) it).toList();
                        TableData tableData = processRecordToData(tableDataBuilder, fields, items, category, type);
                        return Result.success().data(tableData).build();
                    }
                    case 3 -> {
                        OrderResult<List<com.fengchaoit.webclient.btrip.model.order.TrainOrder>> order = aliBtripApi.trainOrderListQuery(OrderListQueryParam.builder()
                                .updateStartTime(DateTimeFormatter.dateTimeToString(startTime))
                                .updateEndTime(DateTimeFormatter.dateTimeToString(endTime))
                                .page(Math.max(1, page))
                                .pageSize(pageSize)
                                .build());

                        List<com.fengchaoit.webclient.btrip.model.order.TrainOrder> records = order.getModule();
                        if (order.getPageInfo() != null) {
                            // 判断是否有下一页
                            boolean hasMore = order.getPageInfo().getTotalNumber() > pageSize * page;
                            if (hasMore) {
                                tableDataBuilder.hasMore(true).nextPageToken(String.valueOf(page + 1));
                            }
                        }
                        List<TrainOrder> dwbgOrders = records.stream().filter(record -> record.getId() != null).map(TrainOrderConvert.INSTANCE::convertBtripOrderToDwbgOrder).toList();
                        List<TrainPriceDto> trainPriceDtos = new ArrayList<>();
                        for (com.fengchaoit.webclient.btrip.model.order.TrainOrder record : records) {
                            List<com.fengchaoit.webclient.btrip.model.order.TrainOrder.PriceInfoListDTO> priceInfoList = record.getPriceInfoList();
                            for (com.fengchaoit.webclient.btrip.model.order.TrainOrder.PriceInfoListDTO price : priceInfoList) {
                                TrainPriceDto trainPriceDto = getTrainPriceDto(record, price);
                                trainPriceDtos.add(trainPriceDto);
                            }
                        }
                        for (TrainOrder dwbgOrder : dwbgOrders) {
                            setTrainOrderPrice(dwbgOrder, trainPriceDtos);
                        }
                        List<Field> fields = processTableMeta(dwbgOrders.get(0).getClass());
                        List<PrimaryKey> items = dwbgOrders.stream().filter(it -> it.getId() != null).map(it -> (PrimaryKey) it).toList();
                        TableData tableData = processRecordToData(tableDataBuilder, fields, items, category, type);
                        return Result.success().data(tableData).build();
                    }
                    case 4 -> {
                        OrderResult<List<com.fengchaoit.webclient.btrip.model.order.CarOrder>> order = aliBtripApi.carOrderListQuery(OrderListQueryParam.builder()
                                .updateStartTime(DateTimeFormatter.dateTimeToString(startTime))
                                .updateEndTime(DateTimeFormatter.dateTimeToString(endTime))
                                .page(Math.max(1, page))
                                .pageSize(pageSize)
                                .build());

                        List<com.fengchaoit.webclient.btrip.model.order.CarOrder> records = order.getModule();
                        if (order.getPageInfo() != null) {
                            // 判断是否有下一页
                            boolean hasMore = order.getPageInfo().getTotalNumber() > pageSize * page;
                            if (hasMore) {
                                tableDataBuilder.hasMore(true).nextPageToken(String.valueOf(page + 1));
                            }
                        }
                        List<CarOrder> dwbgOrders = records.stream().filter(record -> record.getId() != null).map(CarOrderConvert.INSTANCE::convertBtripOrderToDwbgOrder).toList();
                        List<CarPriceDto> carPriceDtos = new ArrayList<>();
                        for (com.fengchaoit.webclient.btrip.model.order.CarOrder record : records) {
                            if (record.getId() == null) {
                                continue;
                            }
                            List<com.fengchaoit.webclient.btrip.model.order.CarOrder.PriceInfoListDTO> priceInfoList = record.getPriceInfoList();
                            if (priceInfoList == null || priceInfoList.isEmpty()) {
                                continue;
                            }
                            for (com.fengchaoit.webclient.btrip.model.order.CarOrder.PriceInfoListDTO price : priceInfoList) {
                                CarPriceDto carPriceDto = getCarPriceDto(record, price);
                                carPriceDtos.add(carPriceDto);
                            }
                        }
                        for (CarOrder dwbgOrder : dwbgOrders) {
                            setCarOrderPrice(dwbgOrder, carPriceDtos);
                        }
                        List<Field> fields = processTableMeta(dwbgOrders.get(0).getClass());
                        List<PrimaryKey> items = dwbgOrders.stream().map(it -> (PrimaryKey) it).toList();
                        TableData tableData = processRecordToData(tableDataBuilder, fields, items, category, type);
                        return Result.success().data(tableData).build();
                    }
                }
            }
            return Result.success().build();
        });
    }

    /**
     * 订阅或退订 数据源变更事件
     *
     * @param body 参数
     * @return 数据
     */
    @PostMapping("/subscribeOrNot")
    public void subscribeOrNot(@RequestBody String body) {
        System.out.println("subscribeOrNot :" + body);
        String params = JSONObject.parseObject(body).getString("params");
        String context = JSONObject.parseObject(body).getString("context");
        String tenantKey = JSONObject.parseObject(context).getString("tenantKey");
        String packID = JSONObject.parseObject(context).getString("packID");
        String subscribeKey = JSONObject.parseObject(params).getString("subscribeKey");
        System.out.println("subscribeKey22222 = " + subscribeKey);
        System.out.println("=======================================");

//        String cacheTenantKey = tenantKey;
//        String cacheKey = SUBSCRIBE_Prefix + tenantKey;

        stringRedisTemplate.opsForValue().set(Constant.CACHE_FEISHU_SUBSCRIBE_KEY, subscribeKey);
        stringRedisTemplate.opsForValue().set(Constant.CACHE_FEISHU_PACKID_KEY, packID);
        System.out.println("订阅");
    }

    /**
     * 基于primaryKey获取record
     *
     * @param body 请求对象
     * @return 数据
     */
    @PostMapping("/getRecordByPrimaryKeys")
    public Data getRecordByPrimaryKeys(@RequestBody String body) {
        System.out.println("getRecordByPrimaryKeys :" + body);
        // 解析primaryKeys
        String json = JSONObject.parseObject(body).getString("params");

        List<PrimaryOrder> primaryOrders = List.of(
                PrimaryOrder.of("1", Order.of("1", "订单1", String.valueOf(400))),
                PrimaryOrder.of("2", Order.of("2", "订单2", String.valueOf(500))),
                PrimaryOrder.of("3", Order.of("3", "订单3", String.valueOf(600)))
        );
        return new Data(String.valueOf(2), false, primaryOrders);
    }

    /**
     * 处理参数
     *
     * @param param   参数
     * @param handler 处理器
     * @return 结果集
     */
    private Result process(TableMetaParam param, Handler handler) {
        String requestParam = param.getParams();
        JSONObject jsonObject = JSONObject.parseObject(requestParam);
        String paramKv = jsonObject.getString("datasourceConfig");
        JSONObject paramJson = JSONObject.parseObject(paramKv);
        String pageToken = jsonObject.getString("pageToken");
        int category = paramJson.getIntValue("category");
        int type = paramJson.getIntValue("type");
        int pageSize = jsonObject.getIntValue("maxPageSize", category == Constant.FEISHU_BILL ? 100 : 50);
        pageSize = Math.min(100, pageSize);

        String appKey = paramJson.getString("appKey");
        String appSecret = paramJson.getString("appSecret");
        String corpId = paramJson.getString("corpId");
        String corpName = paramJson.getString("corpName");
        String startTimeStr = paramJson.getString("startTime");
        LocalDateTime startTime = LocalDateTime.parse(startTimeStr + "T00:00:00", java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (handler instanceof RecordHandler recordHandler) {
            AliBtripAccountHolder.setAccount(appKey, appSecret, corpId);
            return recordHandler.handle(category, type, startTime, corpName, pageToken, pageSize);
        } else {
            return handler.MetaHandle(type);
        }
    }

    /**
     * 处理请求体
     */
    private void processRequestBody(String body) {
        String params = JSONObject.parseObject(body).getString("params");
        String context = JSONObject.parseObject(body).getString("context");
        String tenantKey = JSONObject.parseObject(context).getString("tenantKey");
        String subscribeKey = JSONObject.parseObject(params).getString("subscribeKey");

        // 将tenantKey作为key subscribeKey为值存入redis
        stringRedisTemplate.opsForValue().set(Constant.CACHE_FEISHU_SUBSCRIBE_KEY, subscribeKey);
    }

    /**
     * 获取表元数据
     *
     * @param clazz 类clazz
     * @return 表元数据
     */
    private List<Field> processTableMeta(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        ReflectionUtils.doWithFields(clazz, field -> {
            TableField tableField = AnnotatedElementUtils.findMergedAnnotation(field, TableField.class);
            Objects.requireNonNull(tableField, "字段：" + field.getName() + " 过滤失败，未获取到字段描述信息");
            JsonProperty jsonProperty = AnnotatedElementUtils.findMergedAnnotation(field, JsonProperty.class);
            String fieldName = field.getName();
            if (Objects.nonNull(jsonProperty)) {
                fieldName = jsonProperty.value();
            }

            Field tempTableFieldItem = Field.of(field, fieldName, tableField.fieldName(), tableField.fieldType(), tableField.primary(), tableField.description());
            fields.add(tempTableFieldItem);
        }, field -> AnnotatedElementUtils.isAnnotated(field, TableField.class));
        return fields;
    }

    /**
     * 处理记录项数据
     *
     * @param tableDataBuilder 数据构建器
     * @param fields           字段信息
     * @param items            数据信息
     * @return 数据
     */
    private TableData processRecordToData(TableData.Builder tableDataBuilder, List<Field> fields, List<PrimaryKey> items, int category, int type) {
        for (PrimaryKey item : items) {
            tableDataBuilder.record(item.primary(), record -> {
                for (Field field : fields) {
                    java.lang.reflect.Field f = field.getField();
                    ReflectionUtils.makeAccessible(f);
                    Object value = ReflectionUtils.getField(f, item);

                    if (value != null) {
                        value = getItem(category, type, f, item, value);
                    }

                    record.append(field.getId(), value);
                }
            });
        }
        return tableDataBuilder.build();
    }

    /**
     * 处理特殊字段值
     *
     * @param type  1-机票 2-酒店 3-火车 4-用车
     * @param f     字段
     * @param item  订单数据
     * @param value 字段值
     * @return 处理后的字段值
     */
    private Object getItem(int category, int type, java.lang.reflect.Field f, PrimaryKey item, Object value) {
        if (category == Constant.FEISHU_BILL) {
            switch (f.getName()) {
                case "status" -> {
                    return SettlementRecord.EnumUtils.getDescription(SettlementRecord.StatusEnum.class, (int) value);
                }
                case "feeType" -> {
                    return SettlementRecord.EnumUtils.getDescription(SettlementRecord.FeeTypeEnum.class, (int) value);
                }
                case "settlementType" -> {
                    return SettlementRecord.EnumUtils.getDescription(SettlementRecord.SettlementTypeEnum.class, (int) value);
                }
                case "capitalDirection" -> {
                    return SettlementRecord.EnumUtils.getDescription(SettlementRecord.CapitalDirectionEnum.class, (int) value);
                }
                case "voucherType" -> {
                    return SettlementRecord.EnumUtils.getDescription(SettlementRecord.VoucherTypeEnum.class, (int) value);
                }
            }
        } else {
            if ("tripType".equals(f.getName()) && item instanceof FlightOrder && value != null) {
                return com.fengchaoit.component.alibtrip.model.order.Order.EnumUtils.getDescription(com.fengchaoit.component.alibtrip.model.order.Order.FlightTripTypeEnum.class, (int) value);
            } else if ("status".equals(f.getName()) && item instanceof FlightOrder) {
                return com.fengchaoit.component.alibtrip.model.order.Order.EnumUtils.getDescription(com.fengchaoit.component.alibtrip.model.order.Order.FlightStatusEnum.class, (int) value);
            } else if ("orderStatus".equals(f.getName()) && item instanceof HotelOrder) {
                return com.fengchaoit.component.alibtrip.model.order.Order.EnumUtils.getDescription(com.fengchaoit.component.alibtrip.model.order.Order.HotelStatusEnum.class, (int) value);
            } else if ("hotelSupportVatInvoiceType".equals(f.getName())) {
                return com.fengchaoit.component.alibtrip.model.order.Order.EnumUtils.getDescription(com.fengchaoit.component.alibtrip.model.order.Order.HotelSupportVatInvoiceTypeEnum.class, (int) value);
            } else if ("status".equals(f.getName()) && item instanceof TrainOrder) {
                return com.fengchaoit.component.alibtrip.model.order.Order.EnumUtils.getDescription(com.fengchaoit.component.alibtrip.model.order.Order.TrainStatusEnum.class, (int) value);
            } else if ("orderStatus".equals(f.getName()) && item instanceof CarOrder) {
                return com.fengchaoit.component.alibtrip.model.order.Order.EnumUtils.getDescription(com.fengchaoit.component.alibtrip.model.order.Order.CarStatusEnum.class, (int) value);
            } else if ("carLevel".equals(f.getName()) && item instanceof CarOrder) {
//                return com.fengchaoit.component.alibtrip.model.order.Order.EnumUtils.getDescription(com.fengchaoit.component.alibtrip.model.order.Order.CarLevelEnum.class, (int) value);
            } else if ("serviceType".equals(f.getName()) && item instanceof CarOrder) {
                return com.fengchaoit.component.alibtrip.model.order.Order.EnumUtils.getDescription(com.fengchaoit.component.alibtrip.model.order.Order.CarServiceTypeEnum.class, (int) value);
            } else if ("businessCategory".equals(f.getName()) && item instanceof CarOrder) {
                return com.fengchaoit.component.alibtrip.model.order.Order.StringEnumUtils.getDescription(com.fengchaoit.component.alibtrip.model.order.Order.CarReasonEnum.class, (String) value);
            } else if ("userConfirm".equals(f.getName()) && item instanceof CarOrder && value != null) {
                return com.fengchaoit.component.alibtrip.model.order.Order.EnumUtils.getDescription(com.fengchaoit.component.alibtrip.model.order.Order.CarUserConfirmEnum.class, (int) value);
            } else if ("provider".equals(f.getName()) && item instanceof CarOrder) {
                return com.fengchaoit.component.alibtrip.model.order.Order.EnumUtils.getDescription(com.fengchaoit.component.alibtrip.model.order.Order.CarProviderEnum.class, (int) value);
            }
        }
        return value;
    }

    /**
     * 获取机票价格信息
     *
     * @param record 订单
     * @param price  价格信息
     * @return 机票价格dto
     */
    private static FlightPriceDto getFlightPriceDto(com.fengchaoit.webclient.btrip.model.order.FlightOrder record, com.fengchaoit.webclient.btrip.model.order.FlightOrder.PriceInfoListDTO price) {
        BigDecimal price1 = price.getPrice();
        Integer type1 = price.getType();
        Integer categoryCode = price.getCategoryCode();
        FlightPriceDto flightPriceDto = new FlightPriceDto();
        flightPriceDto.setId(record.getId());
        flightPriceDto.setType(type1);
        flightPriceDto.setCategoryCode(categoryCode);
        flightPriceDto.setPrice(price1);
        flightPriceDto.setPayType(price.getPayType());
        return flightPriceDto;
    }

    /**
     * 获取酒店价格信息
     *
     * @param record 订单
     * @param price  价格信息
     * @return 机票价格dto
     */
    private static HotelPriceDto getHotelPriceDto(com.fengchaoit.webclient.btrip.model.order.HotelOrder record, com.fengchaoit.webclient.btrip.model.order.HotelOrder.PriceInfoListDTO price) {
        BigDecimal price1 = price.getPrice();
        Integer type1 = price.getType();
        Integer categoryCode = price.getCategoryCode();
        HotelPriceDto hotelPriceDto = new HotelPriceDto();
        hotelPriceDto.setId(record.getId());
        hotelPriceDto.setType(type1);
        hotelPriceDto.setCategoryCode(categoryCode);
        hotelPriceDto.setPrice(price1);
        hotelPriceDto.setPayType(price.getPayType());
        return hotelPriceDto;
    }

    /**
     * 获取火车价格信息
     *
     * @param record 订单
     * @param price  价格信息
     * @return 机票价格dto
     */
    private static TrainPriceDto getTrainPriceDto(com.fengchaoit.webclient.btrip.model.order.TrainOrder record, com.fengchaoit.webclient.btrip.model.order.TrainOrder.PriceInfoListDTO price) {
        BigDecimal price1 = price.getPrice();
        Integer type1 = price.getType();
        Integer categoryCode = price.getCategoryCode();
        TrainPriceDto trainPriceDto = new TrainPriceDto();
        trainPriceDto.setId(record.getId());
        trainPriceDto.setType(type1);
        trainPriceDto.setCategoryCode(categoryCode);
        trainPriceDto.setPrice(price1);
        trainPriceDto.setPayType(price.getPayType());
        return trainPriceDto;
    }

    /**
     * 获取火车价格信息
     *
     * @param record 订单
     * @param price  价格信息
     * @return 机票价格dto
     */
    private static CarPriceDto getCarPriceDto(com.fengchaoit.webclient.btrip.model.order.CarOrder record, com.fengchaoit.webclient.btrip.model.order.CarOrder.PriceInfoListDTO price) {
        BigDecimal price1 = price.getPrice();
        Integer type1 = price.getType();
        Integer categoryCode = price.getCategoryCode();
        CarPriceDto carPriceDto = new CarPriceDto();
        carPriceDto.setId(record.getId());
        carPriceDto.setType(type1);
        carPriceDto.setCategoryCode(categoryCode);
        carPriceDto.setPrice(price1);
        carPriceDto.setPayType(price.getPayType());
        return carPriceDto;
    }

    /**
     * 设置订单价格
     *
     * @param dwbgOrder       订单
     * @param flightPriceDtos 价格信息列表
     */
    private static void setFlightOrderPrice(FlightOrder dwbgOrder, List<FlightPriceDto> flightPriceDtos) {
        for (FlightPriceDto flightPriceDto1 : flightPriceDtos) {
            // 转为long
            Long orderId = Long.valueOf(dwbgOrder.getId());
            if (flightPriceDto1.getId().equals(orderId)) {
                setSingleFlightOrderPrice(dwbgOrder, flightPriceDto1);
            }
        }
    }

    /**
     * 设置订单价格
     *
     * @param dwbgOrder      订单
     * @param flightPriceDto 价格信息
     */
    private static void setSingleFlightOrderPrice(FlightOrder dwbgOrder, FlightPriceDto flightPriceDto) {
        BiConsumer<FlightOrder, FlightPriceDto> handler = FlightOrderPriceUtil.categoryHandlers.get(flightPriceDto.getCategoryCode());
        if (handler != null) {
            handler.accept(dwbgOrder, flightPriceDto);
        } else {
            log.warn("未找到对应交易类目编码");
        }
    }

    /**
     * 设置订单价格
     *
     * @param dwbgOrder      订单
     * @param hotelPriceDtos 价格信息列表
     */
    private static void setHotelOrderPrice(HotelOrder dwbgOrder, List<HotelPriceDto> hotelPriceDtos) {
        for (HotelPriceDto hotelPriceDto : hotelPriceDtos) {
            // 转为long
            Long orderId = Long.valueOf(dwbgOrder.getId());
            if (hotelPriceDto.getId().equals(orderId)) {
                setSingleHotelOrderPrice(dwbgOrder, hotelPriceDto);
            }
        }
    }

    /**
     * 设置订单价格
     *
     * @param order         订单
     * @param hotelPriceDto 价格信息
     */
    private static void setSingleHotelOrderPrice(HotelOrder order, HotelPriceDto hotelPriceDto) {
        BiConsumer<HotelOrder, HotelPriceDto> handle = HotelOrderPriceUtil.categoryHandlers.get(hotelPriceDto.getCategoryCode());
        if (handle != null) {
            handle.accept(order, hotelPriceDto);
        } else {
            log.warn("未找到对应交易类目编码");
        }
    }

    /**
     * 设置订单价格
     *
     * @param dwbgOrder      订单
     * @param trainPriceDtos 价格信息列表
     */
    private static void setTrainOrderPrice(TrainOrder dwbgOrder, List<TrainPriceDto> trainPriceDtos) {
        for (TrainPriceDto trainPriceDto : trainPriceDtos) {
            // 转为long
            Long orderId = Long.valueOf(dwbgOrder.getId());
            if (trainPriceDto.getId().equals(orderId)) {
                setSingleTrainOrderPrice(dwbgOrder, trainPriceDto);
            }
        }
    }

    /**
     * 设置订单价格
     *
     * @param order         订单
     * @param trainPriceDto 价格信息
     */
    private static void setSingleTrainOrderPrice(TrainOrder order, TrainPriceDto trainPriceDto) {
        BiConsumer<TrainOrder, TrainPriceDto> handle = TrainOrderPriceUtil.categoryHandlers.get(trainPriceDto.getCategoryCode());
        if (handle != null) {
            handle.accept(order, trainPriceDto);
        } else {
            log.warn("未找到对应交易类目编码");
        }
    }

    /**
     * 设置订单价格
     *
     * @param dwbgOrder    订单
     * @param carPriceDtos 价格信息列表
     */
    private static void setCarOrderPrice(CarOrder dwbgOrder, List<CarPriceDto> carPriceDtos) {
        for (CarPriceDto carPriceDto : carPriceDtos) {
            // 转为long
            Long orderId = Long.valueOf(dwbgOrder.getId());
            if (carPriceDto.getId().equals(orderId)) {
                setSingleCarOrderPrice(dwbgOrder, carPriceDto);
            }
        }
    }

    /**
     * 设置订单价格
     *
     * @param order       订单
     * @param carPriceDto 价格信息
     */
    private static void setSingleCarOrderPrice(CarOrder order, CarPriceDto carPriceDto) {
        BiConsumer<CarOrder, CarPriceDto> handle = CarOrderPriceUtil.categoryHandlers.get(carPriceDto.getCategoryCode());
        if (handle != null) {
            handle.accept(order, carPriceDto);
        } else {
            log.warn("未找到对应交易类目编码");
        }
    }

    /**
     * 预数据处理
     */
    public interface Handler {
        /**
         * 处理器
         *
         * @param type 1-机票 2-酒店 3-火车 4-用车
         */
        default Result MetaHandle(int type) {
            return Result.fail("方法未实现").build();
        }
    }

    /**
     * 记录处理器
     */
    interface RecordHandler extends Handler {
        /**
         * 处理器
         *
         * @param category  1-账单 2-订单
         * @param type      1-机票 2-酒店 3-火车 4-用车
         * @param startTime 开始时间
         * @param corpName  企业名称
         * @param pageToken 分页参数
         * @param pageSize  每页显示条数
         * @return 结果集
         */
        Result handle(int category, int type, LocalDateTime startTime, String corpName, String pageToken, int pageSize);
    }

    /**
     * 账单查询
     *
     * @param func      执行器
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNo    页码
     * @param desc      描述 1:机票 2:酒店 3:火车 4:打车
     * @param <T>
     * @return 账单
     */
    private <T extends com.fengchaoit.webclient.btrip.model.bill.SettlementRecord> com.fengchaoit.webclient.btrip.model.bill.BillSettlement<T> billSettlementQuery(Function<BillSettlementParam, com.fengchaoit.webclient.Result<com.fengchaoit.webclient.btrip.model.bill.BillSettlement<T>>> func,
                                                                                                                                                                   LocalDateTime startTime,
                                                                                                                                                                   LocalDateTime endTime,
                                                                                                                                                                   int pageNo,
                                                                                                                                                                   int pageSize,
                                                                                                                                                                   String desc) {
        BillSettlementParam billSettlementParam = BillSettlementParam.builder()
                .periodStart(DateTimeFormatter.dateTimeToString(startTime))
                .periodEnd(DateTimeFormatter.dateTimeToString(endTime))
                .pageNo(Math.max(1, pageNo))
                .pageSize(pageSize)
                .build();
        com.fengchaoit.webclient.Result<com.fengchaoit.webclient.btrip.model.bill.BillSettlement<T>> result = func.apply(billSettlementParam);
        if (result.isSuccess()) {
            return result.getModule();
        }
        log.error("拉取{}账单失败，原因：{}", desc, result.getMessage());
        throw new BusinessException("获取" + desc + "账单异常， 响应编码为：" + result.getCode() + "，响应消息为：" + result.getMessage());
    }


    /**
     * 订单查询
     *
     * @param func      执行器
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNo    页码
     * @param pageSize  每页显示条数
     * @param desc      描述 1:机票 2:酒店 3:火车 4:打车
     * @param <T>
     * @return 订单
     */
    private <T extends com.fengchaoit.webclient.btrip.model.order.Order> com.fengchaoit.webclient.btrip.model.order.Order orderListQuery(Function<OrderListQueryParam,
            OrderResult<com.fengchaoit.webclient.btrip.model.order.Order>> func,
                                                                                                                                         LocalDateTime startTime,
                                                                                                                                         LocalDateTime endTime,
                                                                                                                                         int pageNo,
                                                                                                                                         int pageSize,
                                                                                                                                         String desc) {
        OrderListQueryParam orderListQueryParam = OrderListQueryParam.builder()
                .updateStartTime(DateTimeFormatter.dateTimeToString(startTime))
                .updateEndTime(DateTimeFormatter.dateTimeToString(endTime))
                .page(Math.max(1, pageNo))
                .pageSize(pageSize)
                .build();
        com.fengchaoit.webclient.btrip.model.order.OrderResult<? extends com.fengchaoit.webclient.btrip.model.order.Order> result = func.apply(orderListQueryParam);
        if (result.isSuccess()) {
            return result.getModule();
        }
        log.error("拉取{}订单失败，原因：{}", desc, result.getMessage());
        throw new BusinessException("获取" + desc + "订单异常， 响应编码为：" + result.getCode() + "，响应消息为：" + result.getMessage());
    }

    /**
     * 机票价格转换工具
     */
    public static class FlightOrderPriceUtil {

        // 定义一个接口来处理不同的 CategoryCode
        private static final Map<Integer, BiConsumer<FlightOrder, FlightPriceDto>> categoryHandlers = new HashMap<>();

        static {
            categoryHandlers.put(1, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setBook, dwbgOrder::setBookPayType, flightPriceDto));
            categoryHandlers.put(2, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setChange, dwbgOrder::setChangePayType, flightPriceDto));
            categoryHandlers.put(3, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setInsurance, dwbgOrder::setInsurancePayType, flightPriceDto));
            categoryHandlers.put(4, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setItineraryPostage, dwbgOrder::setItineraryPostagePayType, flightPriceDto));
            categoryHandlers.put(5, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setTicketOrderServiceFee, dwbgOrder::setTicketOrderServiceFeePayType, flightPriceDto));
            categoryHandlers.put(6, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setTicketRefundFee, dwbgOrder::setTicketRefundFeePayType, flightPriceDto));
            categoryHandlers.put(99, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setTicketAdjustmentDeduction, dwbgOrder::setTicketAdjustmentDeductionPayType, flightPriceDto));
            categoryHandlers.put(101, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setBookingRefund, dwbgOrder::setBookingRefundPayType, flightPriceDto));
            categoryHandlers.put(102, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setChangeRefund, dwbgOrder::setChangeRefundPayType, flightPriceDto));
            categoryHandlers.put(103, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setInsuranceRefund, dwbgOrder::setInsuranceRefundPayType, flightPriceDto));
            categoryHandlers.put(104, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setItineraryPostageRefund, dwbgOrder::setItineraryPostageRefundPayType, flightPriceDto));
            categoryHandlers.put(105, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setTicketCompensation, dwbgOrder::setTicketCompensationPayType, flightPriceDto));
            categoryHandlers.put(106, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setTicketChangeOrderServiceFee, dwbgOrder::setTicketChangeOrderServiceFeePayType, flightPriceDto));
            categoryHandlers.put(107, (dwbgOrder, flightPriceDto) -> setPrice(dwbgOrder::setTicketServiceFeeRefund, dwbgOrder::setTicketServiceFeeRefundPayType, flightPriceDto));
        }

        // 通用处理方法
        private static void setPrice(Consumer<BigDecimal> setPrice, Consumer<String> setPayType, FlightPriceDto flightPriceDto) {
            BigDecimal price = flightPriceDto.getPrice() != null ? flightPriceDto.getPrice() : BigDecimal.ZERO; // 处理getPrice为null的情况
            if (flightPriceDto.getType() == 1) {
                setPrice.accept(price);
            } else {
                setPrice.accept(price.negate());
            }
            OrderPayType payType = OrderPayType.fromCode(flightPriceDto.getPayType());
            setPayType.accept(payType.getDescription());
        }
    }

    /**
     * 酒店价格转换工具
     */
    public static class HotelOrderPriceUtil {
        private static final Map<Integer, BiConsumer<HotelOrder, HotelPriceDto>> categoryHandlers = new HashMap<>();

        static {
            categoryHandlers.put(1, (dwbgOrder, hotelPriceDto) -> setPrice(dwbgOrder::setBook, dwbgOrder::setBookPayType, hotelPriceDto));
            categoryHandlers.put(2, (dwbgOrder, hotelPriceDto) -> setPrice(dwbgOrder::setServiceFee, dwbgOrder::setServiceFeePayType, hotelPriceDto));
            categoryHandlers.put(99, (dwbgOrder, hotelPriceDto) -> setPrice(dwbgOrder::setAdjustment, dwbgOrder::setAdjustmentPayType, hotelPriceDto));
            categoryHandlers.put(101, (dwbgOrder, hotelPriceDto) -> setPrice(dwbgOrder::setRefund, dwbgOrder::setRefundPayType, hotelPriceDto));
            categoryHandlers.put(102, (dwbgOrder, hotelPriceDto) -> setPrice(dwbgOrder::setCompensation, dwbgOrder::setCompensationPayType, hotelPriceDto));
            categoryHandlers.put(110, (dwbgOrder, hotelPriceDto) -> setPrice(dwbgOrder::setFudouDeduction, dwbgOrder::setFudouDeductionPayType, hotelPriceDto));
            categoryHandlers.put(111, (dwbgOrder, hotelPriceDto) -> setPrice(dwbgOrder::setFudouRefund, dwbgOrder::setFudouRefundPayType, hotelPriceDto));
        }

        // 通用处理方法
        private static void setPrice(Consumer<BigDecimal> setPrice, Consumer<String> setPayType, HotelPriceDto hotelPriceDto) {
            BigDecimal price = hotelPriceDto.getPrice() != null ? hotelPriceDto.getPrice() : BigDecimal.ZERO; // 处理getPrice为null的情况
            if (hotelPriceDto.getType() == 1) {
                setPrice.accept(price);
            } else {
                setPrice.accept(price.negate());
            }
            OrderPayType payType = OrderPayType.fromCode(hotelPriceDto.getPayType());
            setPayType.accept(payType.getDescription());
        }
    }

    /**
     * 火车价格转换工具
     */
    public static class TrainOrderPriceUtil {
        private static final Map<Integer, BiConsumer<TrainOrder, TrainPriceDto>> categoryHandlers = new HashMap<>();

        static {
            categoryHandlers.put(1, (dwbgOrder, trainPriceDto) -> setPrice(dwbgOrder::setBook, dwbgOrder::setBookPayType, trainPriceDto));
            categoryHandlers.put(2, (dwbgOrder, trainPriceDto) -> setPrice(dwbgOrder::setRefund, dwbgOrder::setRefundPayType, trainPriceDto));
            categoryHandlers.put(3, (dwbgOrder, trainPriceDto) -> setPrice(dwbgOrder::setChange, dwbgOrder::setChangePayType, trainPriceDto));
            categoryHandlers.put(4, (dwbgOrder, trainPriceDto) -> setPrice(dwbgOrder::setDiffRefund, dwbgOrder::setDiffRefundPayType, trainPriceDto));
            categoryHandlers.put(5, (dwbgOrder, trainPriceDto) -> setPrice(dwbgOrder::setChangeServiceFee, dwbgOrder::setChangeServiceFeePayType, trainPriceDto));
            categoryHandlers.put(6, (dwbgOrder, trainPriceDto) -> setPrice(dwbgOrder::setOfflineRefund, dwbgOrder::setOfflineRefundPayType, trainPriceDto));
            categoryHandlers.put(7, (dwbgOrder, trainPriceDto) -> setPrice(dwbgOrder::setServiceFee, dwbgOrder::setServiceFeePayType, trainPriceDto));
            categoryHandlers.put(8, (dwbgOrder, trainPriceDto) -> setPrice(dwbgOrder::setCompensation, dwbgOrder::setCompensationPayType, trainPriceDto));
            categoryHandlers.put(9, (dwbgOrder, trainPriceDto) -> setPrice(dwbgOrder::setChangeServiceFee, dwbgOrder::setChangeServiceFeePayType, trainPriceDto));
            categoryHandlers.put(10, (dwbgOrder, trainPriceDto) -> setPrice(dwbgOrder::setTicketFailRefund, dwbgOrder::setTicketFailRefundPayType, trainPriceDto));
            categoryHandlers.put(11, (dwbgOrder, trainPriceDto) -> setPrice(dwbgOrder::setServiceFeeRefund, dwbgOrder::setServiceFeeRefundPayType, trainPriceDto));
            categoryHandlers.put(99, (dwbgOrder, trainPriceDto) -> setPrice(dwbgOrder::setReversal, dwbgOrder::setReversalPayType, trainPriceDto));
        }

        // 通用处理方法
        private static void setPrice(Consumer<BigDecimal> setPrice, Consumer<String> setPayType, TrainPriceDto trainPriceDto) {
            BigDecimal price = trainPriceDto.getPrice() != null ? trainPriceDto.getPrice() : BigDecimal.ZERO; // 处理getPrice为null的情况
            if (trainPriceDto.getType() == 1) {
                setPrice.accept(price);
            } else {
                setPrice.accept(price.negate());
            }
            OrderPayType payType = OrderPayType.fromCode(trainPriceDto.getPayType());
            setPayType.accept(payType.getDescription());
        }
    }

    /**
     * 用车价格转换工具
     */
    public static class CarOrderPriceUtil {
        private static final Map<Integer, BiConsumer<CarOrder, CarPriceDto>> categoryHandlers = new HashMap<>();

        static {
            categoryHandlers.put(1, (dwbgOrder, carPriceDto) -> setPrice(dwbgOrder::setPriceInfo, dwbgOrder::setPrinceInfoPayType, carPriceDto));
            categoryHandlers.put(2, (dwbgOrder, carPriceDto) -> setPrice(dwbgOrder::setServicePriceInfo, dwbgOrder::setServicePriceInfoPayType, carPriceDto));
            categoryHandlers.put(3, (dwbgOrder, carPriceDto) -> setPrice(dwbgOrder::setCancelPriceInfo, dwbgOrder::setCancelPriceInfoPayType, carPriceDto));
            categoryHandlers.put(99, (dwbgOrder, carPriceDto) -> setPrice(dwbgOrder::setAdjustPriceInfo, dwbgOrder::setAdjustPriceInfoPayType, carPriceDto));
            categoryHandlers.put(101, (dwbgOrder, carPriceDto) -> setPrice(dwbgOrder::setRefundPriceInfo, dwbgOrder::setRefundPriceInfoPayType, carPriceDto));
            categoryHandlers.put(102, (dwbgOrder, carPriceDto) -> setPrice(dwbgOrder::setCompensationPriceInfo, dwbgOrder::setCompensationPriceInfoPayType, carPriceDto));
        }

        private static void setPrice(Consumer<BigDecimal> setPrice, Consumer<String> setPayType, CarPriceDto carPriceDto) {
            BigDecimal price = carPriceDto.getPrice() != null ? carPriceDto.getPrice() : BigDecimal.ZERO; // 处理getPrice为null的情况
            if (carPriceDto.getType() == 1) {
                setPrice.accept(price);
            } else {
                setPrice.accept(price.negate());
            }
            OrderPayType payType = OrderPayType.fromCode(carPriceDto.getPayType());
            setPayType.accept(payType.getDescription());
        }
    }

    /**
     * 订单支付类型
     */
    @Getter
    public enum OrderPayType {
        PERSONAL_PAY(1, "个人现付"),
        COMPANY_PAY(2, "企业现付"),
        COMPANY_MONTHLY(4, "企业月结"),
        COMPANY_PREPAID(8, "企业预存");

        private final int code;
        private final String description;

        OrderPayType(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public static OrderPayType fromCode(int code) {
            for (OrderPayType type : OrderPayType.values()) {
                if (type.getCode() == code) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid pay type code: " + code);
        }
    }

}
