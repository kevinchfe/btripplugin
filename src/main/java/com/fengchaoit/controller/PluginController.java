package com.fengchaoit.controller;

import com.fengchaoit.model.Meta;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/meta.json")
    public Meta meta(@RequestBody String body) {
        System.out.println("meta data: " + body);
        return Meta.create()
                .extraData(builder -> {
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

    @GetMapping("/table_meta")
    public String tableMeta(@RequestBody String body) {
        return "";
    }

    @GetMapping("/records")
    public String records() {
        return "";
    }

    /**
     * 订阅或退订 数据源变更事件
     *
     * @param body 参数
     * @return 数据
     */
    @PostMapping("/subscribeOrNot")
    public String subscribeOrNot(@RequestBody String body) {
        return "";
    }

    /**
     * 基于primaryKey获取record
     *
     * @param body 请求对象
     * @return 数据
     */
    @PostMapping("/getRecordByPrimaryKeys")
    public String getRecordByPrimaryKeys(@RequestBody String body) {
        return "";
    }

}
