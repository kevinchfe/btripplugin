package com.fengchaoit.controller;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fengchaoit.Constant;
import com.fengchaoit.component.alibtrip.model.bill.BillSettlement;
import com.fengchaoit.component.alibtrip.model.bill.FlightBillSettlementRecord;
import com.fengchaoit.component.alibtrip.model.bill.HotelBillSettlementRecord;
import com.fengchaoit.component.alibtrip.model.bill.SettlementRecord;
import com.fengchaoit.component.alibtrip.param.BillSettlementParam;
import com.fengchaoit.component.feishu.datasync.annotation.TableField;
import com.fengchaoit.component.feishu.datasync.model.Field;
import com.fengchaoit.component.feishu.datasync.model.PrimaryKey;
import com.fengchaoit.component.feishu.datasync.model.TableData;
import com.fengchaoit.component.feishu.datasync.model.TableMeta;
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
import com.fengchaoit.webclient.btrip.AliBtripApi;
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
                    builder.dataSourceConfigUiUri("https://ext.baseopendev.com/ext/data-sync-fe-demo/c70fa2864a002386423f26411f21a3c674bc2f9c/index.html");
                    builder.initHeight(300);
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
    public Result tableMeta() {
//        List<Field> fields = List.of(
////                Field.of("orderNo", "订单号", 1, true, "订单号"),
////                Field.of("orderName", "订单名称", 1, false, "订单名称"),
//                Field.of("price", "订单价格", 1, true, "订单价格")
//        );

//        List<Field> fields1 = List.of(
//                Field.of("orderNo", "订单号", 1, true, "订单号")
//        );
//
//        TableMeta tableMeta = TableMeta.of("测试订单", fields1);
//        System.out.println("Generated TableMeta: " + tableMeta.toString());
//        return tableMeta;

        return Result.success().build();
//        return process(param, (RecordHandler) (category, type, pageToken, pageSize) -> {
//            category = 1;
//            type = 2;
//            String categoryStr = "";
//            if (category == 1) {
//                categoryStr = "账单";
//            } else if (category == 2) {
//                categoryStr = "订单";
//            }
//
//            return switch (type) {
//                case 1 -> {
//                    List<Field> fields = processTableMeta(FlightBillSettlementRecord.class);
//                    TableMeta tableMeta = TableMeta.of("机票" + categoryStr, fields);
//                    yield Result.success().data(tableMeta).build();
//                }
//                case 2 -> {
//                    List<Field> fields = processTableMeta(HotelBillSettlementRecord.class);
//                    TableMeta tableMeta = TableMeta.of("酒店" + categoryStr, fields);
//                    yield Result.success().data(tableMeta).build();
//                }
//                case 3 -> {
//                    List<Field> fields = processTableMeta(FlightBillSettlementRecord.class);
//                    TableMeta tableMeta = TableMeta.of("火车" + categoryStr, fields);
//                    yield Result.success().data(tableMeta).build();
//                }
//                case 4 -> {
//                    List<Field> fields = processTableMeta(FlightBillSettlementRecord.class);
//                    TableMeta tableMeta = TableMeta.of("用车" + categoryStr, fields);
//                    yield Result.success().data(tableMeta).build();
//                }
//                default -> Result.fail("未找到对应类型").build();
//            };
//        });
    }

    /**
     * 获取表记录
     *
     * @return 数据
     */
    @PostMapping("/records")
    public Result records(@RequestBody TableMetaParam param) {
//        System.out.println("records :" + body);
//        TableData.Builder tableDataBuilder = TableData.create().hasMore(false).nextPageToken(String.valueOf(1));
//        // 构造返回值
//        List<PrimaryOrder> primaryOrders = List.of(
//                PrimaryOrder.of("1", Order.of("1", "订单1", String.valueOf(100))),
//                PrimaryOrder.of("2", Order.of("2", "订单2", String.valueOf(200))),
//                PrimaryOrder.of("3", Order.of("3", "订单3", String.valueOf(300)))
//        );
//        return new Data(String.valueOf(2), false, primaryOrders);

        return process(param, (RecordHandler) (category, type, pageToken, pageSize) -> {
            int page = NumberUtils.toInt(pageToken, 1);
            pageSize = 2;
            category = 1;
            type = 1;

            // 当月第一天
            LocalDateTime startTime = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            // 加一天
            LocalDateTime endTime = startTime.plusDays(1);


            // api获取数据
            if (category == Constant.FEISHU_BILL) { // 账单
                BillSettlementParam billParam = BillSettlementParam.builder()
                        .periodStart(DateTimeFormatter.dateTimeToString(startTime))
                        .periodEnd(DateTimeFormatter.dateTimeToString(endTime))
                        .pageNo(page)
                        .pageSize(pageSize)
                        .build();
                TableData.Builder tableDataBuilder = TableData.create().hasMore(false);
                switch (type) {



                    case 1 -> {
                        BillSettlement<FlightBillSettlementRecord> billSettlement = billSettlementQuery(aliBtripApi::flightBillSettlement,
                                startTime, endTime, page, pageSize, "机票");

                        // 判断是否有下一页
                        boolean hasMore = billSettlement.getTotalNum() > (long) pageSize * page;
                        if (hasMore) {
                            tableDataBuilder.hasMore(true).nextPageToken(String.valueOf(page + 1));
                        }
                        List<PrimaryKey> fields = billSettlement.getRecords().stream().map(it -> (PrimaryKey) it).toList();
                        TableData tableData = processRecordToData(tableDataBuilder, processTableMeta(FlightBillSettlementRecord.class), fields, type);
                        return Result.success().data(tableData).build();
                    }
                    case 2 -> {
                        BillSettlement<HotelBillSettlementRecord> billSettlement = billSettlementQuery(aliBtripApi::hotelBillSettlement,
                                startTime, endTime, page,pageSize, "酒店");
                        return Result.success().data(billSettlement).build();
                    }
                }


//                com.fengchaoit.webclient.btrip.model.Result<BillSettlement<FlightBillSettlementRecord>> result = aliBtripApi.flightBillSettlement(billParam);


            } else { // 订单

            }


//            Page<? extends AliBtripStoreOrder> orders = orderService.getOrders(channel, type, page, pageSize);
//            List<? extends AliBtripStoreOrder> list = orders.getContent();
//            boolean hasMore = !orders.isLast();
//            TableData.Builder tableDataBuilder = TableData.create().hasMore(hasMore).nextPageToken(String.valueOf(page + 1));
//            if (list.isEmpty()) {
//                return Result.success().data(tableDataBuilder.build()).build();
//            }
//
//            // 对象转换
//            List<OrderDto> orderDtos = list.stream()
//                    .map(order -> convertToDto(order, type))
//                    .collect(Collectors.toList());
//            List<Field> fields = processTableMeta(orderDtos.get(0).getClass());
//            List<PrimaryKey> items = orderDtos.stream().map(it -> (PrimaryKey) it).collect(Collectors.toList());
//            TableData tableData = processRecordToData(tableDataBuilder, fields, items, type);
//            return Result.success().data(tableData).build();
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
        int pageSize = jsonObject.getIntValue("maxPageSize", 200);
        pageSize = Math.min(200, pageSize);

        int category = paramJson.getIntValue("category");
        int type = paramJson.getIntValue("type");
        if (handler instanceof RecordHandler recordHandler) {
            return recordHandler.handle(category, type, pageToken, pageSize);
        } else {
            return handler.MetaHandle(type);
        }
    }

    /**
     * 处理记录项未数据
     *
     * @param tableDataBuilder 数据构建器
     * @param fields           字段信息
     * @param items            数据信息
     * @return 数据
     */
//    private TableData processRecordToData(TableData.Builder tableDataBuilder, List<Field> fields, List<PrimaryKey> items, int type) {
//        for (PrimaryKey item : items) {
//            tableDataBuilder.record(item.primary(), record -> {
//                for (Field field : fields) {
//                    java.lang.reflect.Field f = field.getField();
//                    ReflectionUtils.makeAccessible(f);
//                    Object value = ReflectionUtils.getField(f, item);
//
//                    Object abc = getItem(type, f, item, value);
//                    if (abc != null) {
//                        value = abc;
//                    }
//                    record.append(field.getId(), value);
//                }
//            });
//        }
//        return tableDataBuilder.build();
//    }

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
     * 处理记录项未数据
     *
     * @param tableDataBuilder 数据构建器
     * @param fields           字段信息
     * @param items            数据信息
     * @return 数据
     */
    private TableData processRecordToData(TableData.Builder tableDataBuilder, List<Field> fields, List<PrimaryKey> items, int type) {
        for (PrimaryKey item : items) {
            tableDataBuilder.record(item.primary(), record -> {
                for (Field field : fields) {
                    java.lang.reflect.Field f = field.getField();
                    ReflectionUtils.makeAccessible(f);
                    Object value = ReflectionUtils.getField(f, item);

                    record.append(field.getId(), value);
                }
            });
        }
        return tableDataBuilder.build();
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
         * @param pageToken 分页参数
         * @param pageSize  每页显示条数
         * @return 结果集
         */
        Result handle(int category, int type, String pageToken, int pageSize);
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
    private <T extends SettlementRecord> BillSettlement<T> billSettlementQuery(Function<BillSettlementParam, com.fengchaoit.webclient.Result<BillSettlement<T>>> func,
                                                                               LocalDateTime startTime,
                                                                               LocalDateTime endTime,
                                                                               int pageNo,
                                                                               int pageSize,
                                                                               String desc) {
        System.out.println("开始时间：" + startTime);
        BillSettlementParam billSettlementParam = BillSettlementParam.builder()
                .periodStart(DateTimeFormatter.dateTimeToString(startTime))
                .periodEnd(DateTimeFormatter.dateTimeToString(endTime))
                .pageNo(Math.max(1, pageNo))
                .pageSize(pageSize)
                .build();
        com.fengchaoit.webclient.Result<BillSettlement<T>> result = func.apply(billSettlementParam);
        if (result.isSuccess()) {
            return result.getModule();
        }
        log.error("拉取{}账单失败，原因：{}", desc, result.getMessage());
        throw new BusinessException("获取" + desc + "账单异常， 响应编码为：" + result.getCode() + "，响应消息为：" + result.getMessage());
    }


}
