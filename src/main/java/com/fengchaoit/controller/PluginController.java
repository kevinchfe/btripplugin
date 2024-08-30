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
import com.fengchaoit.config.props.AliBtripProperties;
import com.fengchaoit.convert.*;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private final AliBtripProperties aliBtripProperties;
    private final StringRedisTemplate stringRedisTemplate;

    public PluginController(AliBtripApi aliBtripApi, AliBtripProperties aliBtripProperties, StringRedisTemplate stringRedisTemplate) {
        this.aliBtripApi = aliBtripApi;
        this.aliBtripProperties = aliBtripProperties;
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

            // api获取数据
            if (category == Constant.FEISHU_BILL) { // 账单
                LocalDateTime endTime = startTime.plusDays(1);
                com.fengchaoit.webclient.btrip.model.bill.BillSettlement<?> billSettlement;
                TableData.Builder tableDataBuilder = TableData.create().hasMore(false);
                switch (type) {
                    case 1 -> {
                        billSettlement = billSettlementQuery(aliBtripApi::flightBillSettlement,
                                startTime, endTime, page, pageSize, "机票");
                        boolean hasMore = billSettlement.getTotalNum() > (long) pageSize * page;
                        if (hasMore) {
                            tableDataBuilder.hasMore(true).nextPageToken(String.valueOf(page + 1));
                        }
                        List<com.fengchaoit.webclient.btrip.model.bill.FlightBillSettlementRecord> records = (List<com.fengchaoit.webclient.btrip.model.bill.FlightBillSettlementRecord>) billSettlement.getRecords();
                        List<FlightBillSettlement> dwbgBills = records.stream().map(FlightBillConvert.INSTANCE::convertBtripBillToDwbgBill).toList();
                        List<Field> fields = processTableMeta(FlightBillSettlement.class);
                        List<PrimaryKey> items = dwbgBills.stream().map(it -> (PrimaryKey) it).toList();
                        TableData tableData = processRecordToData(tableDataBuilder, fields, items, category, type);
                        return Result.success().data(tableData).build();
                    }
                    case 2 -> {
                        billSettlement = billSettlementQuery(aliBtripApi::hotelBillSettlement,
                                startTime, endTime, page, pageSize, "酒店");
                        boolean hasMore = billSettlement.getTotalNum() > (long) pageSize * page;
                        if (hasMore) {
                            tableDataBuilder.hasMore(true).nextPageToken(String.valueOf(page + 1));
                        }
                        List<com.fengchaoit.webclient.btrip.model.bill.HotelBillSettlementRecord> records = (List<com.fengchaoit.webclient.btrip.model.bill.HotelBillSettlementRecord>) billSettlement.getRecords();
                        List<HotelBillSettlement> dwbgBills = records.stream().map(HotelBillConvert.INSTANCE::convertBtripBillToDwbgBill).toList();
                        List<Field> fields = processTableMeta(HotelBillSettlement.class);
                        List<PrimaryKey> items = dwbgBills.stream().map(it -> (PrimaryKey) it).toList();
                        TableData tableData = processRecordToData(tableDataBuilder, fields, items, category, type);
                        return Result.success().data(tableData).build();
                    }
                    case 3 -> {
                        billSettlement = billSettlementQuery(aliBtripApi::trainBillSettlement,
                                startTime, endTime, page, pageSize, "火车");
                        boolean hasMore = billSettlement.getTotalNum() > (long) pageSize * page;
                        if (hasMore) {
                            tableDataBuilder.hasMore(true).nextPageToken(String.valueOf(page + 1));
                        }
                        List<com.fengchaoit.webclient.btrip.model.bill.TrainBillSettlementRecord> records = (List<com.fengchaoit.webclient.btrip.model.bill.TrainBillSettlementRecord>) billSettlement.getRecords();
                        List<TrainBillSettlement> dwbgBills = records.stream().map(TrainBillConvert.INSTANCE::convertBtripBillToDwbgBill).toList();
                        List<Field> fields = processTableMeta(TrainBillSettlement.class);
                        List<PrimaryKey> items = dwbgBills.stream().map(it -> (PrimaryKey) it).toList();
                        TableData tableData = processRecordToData(tableDataBuilder, fields, items, category, type);
                        return Result.success().data(tableData).build();
                    }
                    case 4 -> {
                        billSettlement = billSettlementQuery(aliBtripApi::carBillSettlement,
                                startTime, endTime, page, pageSize, "用车");
                        boolean hasMore = billSettlement.getTotalNum() > (long) pageSize * page;
                        if (hasMore) {
                            tableDataBuilder.hasMore(true).nextPageToken(String.valueOf(page + 1));
                        }
                        List<com.fengchaoit.webclient.btrip.model.bill.CarBillSettlementRecord> records = (List<com.fengchaoit.webclient.btrip.model.bill.CarBillSettlementRecord>) billSettlement.getRecords();
                        List<CarBillSettlement> dwbgBills = records.stream().map(CarBillConvert.INSTANCE::convertBtripBillToDwbgBill).toList();
                        List<Field> fields = processTableMeta(CarBillSettlement.class);
                        List<PrimaryKey> items = dwbgBills.stream().map(it -> (PrimaryKey) it).toList();
                        TableData tableData = processRecordToData(tableDataBuilder, fields, items, category, type);
                        return Result.success().data(tableData).build();
                    }
                    default -> {
                        return Result.fail("未找到对应类型").build();
                    }
                }
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

                        // 列表类型转换
                        List<FlightOrder> dwbgOrders = records.stream().map(FlightOrderConvert.INSTANCE::convertBtripOrderToDwbgOrder).toList();
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

                        // 列表类型转换
                        List<HotelOrder> dwbgOrders = records.stream().map(HotelOrderConvert.INSTANCE::convertBtripOrderToDwbgOrder).toList();
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

                        // 列表类型转换
                        List<TrainOrder> dwbgOrders = records.stream().map(TrainOrderConvert.INSTANCE::convertBtripOrderToDwbgOrder).toList();
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

                        // 列表类型转换
                        List<CarOrder> dwbgOrders = records.stream().map(CarOrderConvert.INSTANCE::convertBtripOrderToDwbgOrder).toList();
                        List<Field> fields = processTableMeta(dwbgOrders.get(0).getClass());
                        List<PrimaryKey> items = dwbgOrders.stream().filter(it -> it.getId() != null).map(it -> (PrimaryKey) it).toList();
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

                    Object enumValue = getItem(category, type, f, item, value);
                    if (value != null) {
                        value = enumValue;
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
                return com.fengchaoit.component.alibtrip.model.order.Order.EnumUtils.getDescription(com.fengchaoit.component.alibtrip.model.order.Order.CarLevelEnum.class, (int) value);
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
     * 预数据处理
     */
    public interface Handler {
        /**
         * 处理器
         *
         * @param type 1-机票 2-酒店 3-火车 4-用车
         * @return
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


}
