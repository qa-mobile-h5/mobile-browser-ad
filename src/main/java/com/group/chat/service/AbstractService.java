package com.group.chat.service;

import org.json.JSONObject;
import com.group.chat.entity.HttpRequestParam;
import java.util.Map;

public abstract class AbstractService {

    // 抽象方法：读取和解析参数
    public abstract HttpRequestParam readAndParseParams(Map<String, String> params);

    // 抽象方法：执行Service逻辑操作
    public abstract String executeService(HttpRequestParam requestParam);

    // 封装执行结果为JSON格式的字符串
    protected String createJsonResult(Object result, int errCode, String errMsg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", result);
        jsonObject.put("err_code", errCode);
        jsonObject.put("err_msg", errMsg);
        return jsonObject.toString();
    }
}
