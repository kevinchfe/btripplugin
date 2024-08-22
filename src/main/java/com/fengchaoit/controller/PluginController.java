package com.fengchaoit.controller;

import com.alibaba.fastjson2.JSONObject;
import com.fengchaoit.Constant;
import com.fengchaoit.component.feishu.datasync.model.Field;
import com.fengchaoit.component.feishu.datasync.model.Result;
import com.fengchaoit.component.feishu.datasync.model.TableData;
import com.fengchaoit.component.feishu.datasync.model.TableMeta;
import com.fengchaoit.entity.Data;
import com.fengchaoit.entity.Order;
import com.fengchaoit.entity.PrimaryOrder;
import com.fengchaoit.model.Meta;
import com.fengchaoit.param.TableMetaParam;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;
import java.util.logging.Handler;

/**
 * 飞书插件控制器
 *
 * @author wangchuan
 * @version 1.0
 * @since Created in 17:47 2024/8/19
 */
@RestController
@RequestMapping("/plugin/connector")
public class PluginController {

    private final StringRedisTemplate stringRedisTemplate;

    public PluginController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 获取元数据
     *
     * @return 元数据
     */
    @GetMapping("/meta.json")
    public Meta meta() {
        return Meta.create()
                .extraData(builder -> {
                    builder.dataSourceConfigUiUri("https://ext.baseopendev.com/ext/data-sync-fe-demo/c70fa2864a002386423f26411f21a3c674bc2f9c/index.html");
                    builder.initHeight(300);
                    builder.initWeight(520);
                    builder.syncType("trigger");
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
     * @param body 请求对象
     * @return 数据
     */
    @PostMapping("/table_meta")
    public Result tableMeta(@RequestBody String body) {
//        processRequestBody(body);

        // 解析参数
        TableMetaParam param = JSONObject.parseObject(body, TableMetaParam.class);
        List<Field> fields = List.of(
                Field.of("orderNo", "订单号", 1, true, "订单号"),
                Field.of("orderName", "订单名称", 1, false, "订单名称"),
                Field.of("price", "订单价格", 1, false, "订单价格")
        );
        TableMeta tableMeta = TableMeta.of("测试订单", fields);
        return Result.success().data(tableMeta).build();
    }

    /**
     * 获取表记录
     *
     * @return 数据
     */
    @PostMapping("/records")
    public Result records() {
        TableData.Builder tableDataBuilder = TableData.create().hasMore(false).nextPageToken(String.valueOf(1));
        // 构造返回值
        List<PrimaryOrder> primaryOrders = List.of(
                PrimaryOrder.of("1", Order.of("1", "订单1", String.valueOf(100))),
                PrimaryOrder.of("2", Order.of("2", "订单2", String.valueOf(200))),
                PrimaryOrder.of("3", Order.of("3", "订单3", String.valueOf(300)))
        );
        Data data = new Data(String.valueOf(2), false, primaryOrders);
        return Result.success().data(data).build();
    }

    /**
     * 订阅或退订 数据源变更事件
     *
     * @param body 参数
     * @return 数据
     */
    @PostMapping("/subscribeOrNot")
    public Result subscribeOrNot(HttpServletRequest request, @RequestBody String body) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            System.out.println(name + ":" + request.getHeader(name));
        }
        System.out.println("=======================================");
        System.out.println(body);
        String params = JSONObject.parseObject(body).getString("params");
        String context = JSONObject.parseObject(body).getString("context");
        String tenantKey = JSONObject.parseObject(context).getString("tenantKey");
        String subscribeKey = JSONObject.parseObject(params).getString("subscribeKey");

//        String cacheTenantKey = tenantKey;
//        String cacheKey = SUBSCRIBE_Prefix + tenantKey;

        stringRedisTemplate.opsForValue().set(Constant.CACHE_FEISHU_SUBSCRIBE_KEY, subscribeKey);
        return Result.success().build();
    }

    /**
     * 基于primaryKey获取record
     *
     * @param body 请求对象
     * @return 数据
     */
    @PostMapping("/getRecordByPrimaryKeys")
    public Result getRecordByPrimaryKeys(@RequestBody String body) {
        // 解析primaryKeys
        String json = JSONObject.parseObject(body).getString("params");

        List<PrimaryOrder> primaryOrders = List.of(
                PrimaryOrder.of("1", Order.of("1", "订单1", String.valueOf(400))),
                PrimaryOrder.of("2", Order.of("2", "订单2", String.valueOf(500))),
                PrimaryOrder.of("3", Order.of("3", "订单3", String.valueOf(600)))
        );
        Data data = new Data(String.valueOf(2), false, primaryOrders);
        return Result.success().data(data).build();
    }

    /**
     * 处理参数
     *
     * @param param   参数
     * @param handler 处理器
     * @return 结果集
     */
    private void process(TableMetaParam param, Handler handler) {
        String requestParam = param.getParams();
        JSONObject jsonObject = JSONObject.parseObject(requestParam);
        String paramKv = jsonObject.getString("datasourceConfig");
        JSONObject paramJson = JSONObject.parseObject(paramKv);
        String pageToken = jsonObject.getString("pageToken");
        int pageSize = jsonObject.getIntValue("maxPageSize", 200);
        pageSize = Math.min(200, pageSize);

        String channel = paramJson.getString("channel");
        int type = paramJson.getIntValue("type");
        return;
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


    @PostMapping("/test")
    public void testReq(@RequestBody String body, HttpServletRequest request) {

        System.out.println("body: " + body);
        System.out.println("=======================================");


        // 获取请求头
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            System.out.println(name + ":" + request.getHeader(name));
        }

        // 获取请求体
        System.out.println(body);
    }

}
