package com.gyf.toolutils4a;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Msg: JSON处理类 1、Map转为JSONObject 2、集合转换为JSONArray
 * Created by gyf on 2016/11/15.
 */

public class JsonUtils {
    /**
     * Map转为JSON对象
     *
     * @param data Map数据
     * @return
     */
    public static JSONObject mapToJson(Map<?, ?> data) {
        JSONObject object = new JSONObject();

        for (Map.Entry<?, ?> entry : data.entrySet()) {
            String key = (String) entry.getKey();
            if (key == null) {
                throw new NullPointerException("key == null");
            }
            try {
                object.put(key, wrap(entry.getValue()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return object;
    }

    /**
     * 集合转换为JSONArray
     *
     * @param data 集合对象
     * @return
     */
    public static JSONArray collectionToJson(Collection<?> data) {
        JSONArray jsonArray = new JSONArray();
        if (data != null) {
            for (Object aData : data) {
                jsonArray.put(wrap(aData));
            }
        }
        return jsonArray;
    }

    /**
     * Object对象转换为JSONArray
     *
     * @param data Object对象
     * @return
     * @throws JSONException Object非数组时，抛出异常
     */
    public static JSONArray arrayToJson(Object data) throws JSONException {
        if (!data.getClass().isArray()) {
            throw new JSONException("Not a primitive data: " + data.getClass());
        }
        final int length = Array.getLength(data);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < length; ++i) {
            jsonArray.put(wrap(Array.get(data, i)));
        }

        return jsonArray;
    }

    private static Object wrap(Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof JSONArray || o instanceof JSONObject) {
            return o;
        }
        try {
            if (o instanceof Collection) {
                return collectionToJson((Collection<?>) o);
            } else if (o.getClass().isArray()) {
                return arrayToJson(o);
            }
            if (o instanceof Map) {
                return mapToJson((Map<?, ?>) o);
            }

            if (o instanceof Boolean || o instanceof Byte || o instanceof Character || o instanceof Double || o instanceof Float || o instanceof Integer || o instanceof Long
                    || o instanceof Short || o instanceof String) {
                return o;
            }
            if (o.getClass().getPackage().getName().startsWith("java.")) {
                return o.toString();
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * 根据json字符串生成JSONObject对象
     *
     * @param json json字符串
     * @return 返回JSONObject对象或者空（null）
     */
    public static JSONObject initJSONObject(String json) {

        JSONObject jsonObject = null;
        try {
            JSONTokener jsonParser = new JSONTokener(json);
            jsonObject = (JSONObject) jsonParser.nextValue();
        } catch (Exception e) {
        } finally {
            return jsonObject;
        }

    }

    /**
     * 根据Key值相对应的Value值
     *
     * @param jsonObject Json对象
     * @param key        健值
     * @return 返回value值或空串（""）
     */
    public static String getString(JSONObject jsonObject, String key) {
        String value = "";
        try {
            value = jsonObject.getString(key);
        } catch (Exception e) {
        }

        return value;
    }

    /**
     * 根据Key值相对应的Value值
     *
     * @param jsonObject Json对象
     * @param key        健值
     * @return 返回value值或-1
     */
    public static int getInt(JSONObject jsonObject, String key) {
        int value = -1;
        try {
            value = jsonObject.getInt(key);
        } catch (Exception e) {
        }

        return value;
    }

    /**
     * 根据Key值相对应的Value值
     *
     * @param jsonObject Json对象
     * @param key        健值
     * @return 返回value值或-1
     */
    public static long getLong(JSONObject jsonObject, String key) {
        long value = -1;
        try {
            value = jsonObject.getLong(key);
        } catch (Exception e) {
        }

        return value;
    }

    /**
     * 根据Key值相对应的Value值
     *
     * @param jsonObject Json对象
     * @param key        健值
     * @return 返回value值或null
     */
    public static JSONArray getJSONArray(JSONObject jsonObject, String key) {
        JSONArray arrays = null;
        try {
            arrays = jsonObject.getJSONArray(key);
        } catch (Exception e) {
        }

        return arrays;
    }

    /**
     * 根据Key值相对应的Value值
     *
     * @param jsonArray JsonArray对象
     * @param index     索引
     * @return 返回value值或null
     */
    public static JSONObject getJSONObject(JSONArray jsonArray, int index) {
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(index);
        } catch (Exception e) {
        }
        return jsonObject;
    }
}
