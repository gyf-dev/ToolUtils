package com.gyf.toolutils4a;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * The type L.
 */
public class L {
    /**
     * The constant TAG.
     */
    private static String TAG = "ghost";
    /**
     * The constant LOG_DEBUG.
     */
    private static boolean LOG_DEBUG = true;
    /**
     * The constant LINE_SEPARATOR.
     */
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    /**
     * The constant VERBOSE.
     */
    private static final int VERBOSE = 2;
    /**
     * The constant DEBUG.
     */
    private static final int DEBUG = 3;
    /**
     * The constant INFO.
     */
    private static final int INFO = 4;
    /**
     * The constant WARN.
     */
    private static final int WARN = 5;
    /**
     * The constant ERROR.
     */
    private static final int ERROR = 6;
    /**
     * The constant ASSERT.
     */
    private static final int ASSERT = 7;
    /**
     * The constant JSON.
     */
    private static final int JSON = 8;
    /**
     * The constant XML.
     */
    private static final int XML = 9;

    /**
     * The constant JSON_INDENT.
     */
    private static final int JSON_INDENT = 4;

    /**
     * init 初始化日志开关和TAG(默认日志为开,TAG为"ghost")
     *
     * @param isDebug the is debug
     * @param tag     the tag
     */
    public static void init(boolean isDebug, String tag) {
        TAG = tag;
        LOG_DEBUG = isDebug;
    }

    /**
     * V.
     *
     * @param msg the msg
     */
    public static void v(String msg) {
        log(VERBOSE, null, msg);
    }

    /**
     * V.
     *
     * @param tag the tag
     * @param msg the msg
     */
    public static void v(String tag, String msg) {
        log(VERBOSE, tag, msg);
    }

    /**
     * D.
     *
     * @param msg the msg
     */
    public static void d(String msg) {
        log(DEBUG, null, msg);
    }

    /**
     * D.
     *
     * @param tag the tag
     * @param msg the msg
     */
    public static void d(String tag, String msg) {
        log(DEBUG, tag, msg);
    }

    /**
     * .
     *
     * @param msg the msg
     */
    public static void i(Object... msg) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : msg) {
            sb.append(obj);
            sb.append(",");
        }
        log(INFO, null, String.valueOf(sb));
    }

    /**
     * W.
     *
     * @param msg the msg
     */
    public static void w(String msg) {
        log(WARN, null, msg);
    }

    /**
     * W.
     *
     * @param tag the tag
     * @param msg the msg
     */
    public static void w(String tag, String msg) {
        log(WARN, tag, msg);
    }

    /**
     * E.
     *
     * @param msg the msg
     */
    public static void e(String msg) {
        log(ERROR, null, msg);
    }

    /**
     * E.
     *
     * @param tag the tag
     * @param msg the msg
     */
    public static void e(String tag, String msg) {
        log(ERROR, tag, msg);
    }

    /**
     * A.
     *
     * @param msg the msg
     */
    public static void a(String msg) {
        log(ASSERT, null, msg);
    }

    /**
     * A.
     *
     * @param tag the tag
     * @param msg the msg
     */
    public static void a(String tag, String msg) {
        log(ASSERT, tag, msg);
    }

    /**
     * Json.
     *
     * @param json the json
     */
    public static void json(String json) {
        log(JSON, null, json);
    }

    /**
     * 输出json
     *
     * @param tag  the tag
     * @param json the json
     */
    public static void json(String tag, String json) {
        log(JSON, tag, json);
    }

    /**
     * 输出xml
     *
     * @param xml the xml
     */
    public static void xml(String xml) {
        log(XML, null, xml);
    }

    /**
     * Xml.
     *
     * @param tag the tag
     * @param xml the xml
     */
    public static void xml(String tag, String xml) {
        log(XML, tag, xml);
    }

    private static void log(int logType, String tagStr, Object objects) {
        String[] contents = wrapperContent(tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];
        if (LOG_DEBUG) {
            switch (logType) {
                case VERBOSE:
                case DEBUG:
                case INFO:
                case WARN:
                case ERROR:
                case ASSERT:
                    printDefault(logType, tag, headString + msg);
                    break;
                case JSON:
                    printJson(tag, msg, headString);
                    break;
                case XML:
                    printXml(tag, msg, headString);
                    break;
                default:
                    break;
            }
        }
    }

    private static void printDefault(int type, String tag, String msg) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        int index = 0;
        int maxLength = 4000;
        int countOfSub = msg.length() / maxLength;

        if (countOfSub > 0) {  // The log is so long
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + maxLength);
                printSub(type, tag, sub);
                index += maxLength;
            }
            //printSub(type, msg.substring(index, msg.length()));
        } else {
            printSub(type, tag, msg);
        }

    }

    private static void printSub(int type, String tag, String sub) {
        if (tag == null) {
            tag = TAG;
        }
        printLine(tag, true);
        switch (type) {
            case VERBOSE:
                Log.v(tag, sub);
                break;
            case DEBUG:
                Log.d(tag, sub);
                break;
            case INFO:
                Log.i(tag, sub);
                break;
            case WARN:
                Log.w(tag, sub);
                break;
            case ERROR:
                Log.e(tag, sub);
                break;
            case ASSERT:
                Log.wtf(tag, sub);
                break;
        }
        printLine(tag, false);
    }

    private static void printJson(String tag, String json, String headString) {
        if (TextUtils.isEmpty(json)) {
            d("Empty/Null json content");
            return;
        }
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        String message;

        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                message = jsonObject.toString(JSON_INDENT);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                message = jsonArray.toString(JSON_INDENT);
            } else {
                message = json;
            }
        } catch (JSONException e) {
            message = json;
        }

        printLine(tag, true);
        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "|" + line);
        }
        printLine(tag, false);
    }

    private static void printXml(String tag, String xml, String headString) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        if (xml != null) {
            try {
                Source xmlInput = new StreamSource(new StringReader(xml));
                StreamResult xmlOutput = new StreamResult(new StringWriter());
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.transform(xmlInput, xmlOutput);
                xml = xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
            xml = headString + "\n" + xml;
        } else {
            xml = headString + "Log with null object";
        }

        printLine(tag, true);
        String[] lines = xml.split(LINE_SEPARATOR);
        for (String line : lines) {
            if (!TextUtils.isEmpty(line)) {
                Log.d(tag, "|" + line);
            }
        }
        printLine(tag, false);
    }

    private static String[] wrapperContent(String tag, Object... objects) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement targetElement = stackTrace[5];
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + ".java";
        }
        String methodName = targetElement.getMethodName();
        int lineNumber = targetElement.getLineNumber();
        if (lineNumber < 0) {
            lineNumber = 0;
        }
        String methodNameShort = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        String msg = (objects == null) ? "Log with null object" : getObjectsString(objects);
        String headString = "[(" + className + ":" + lineNumber + ")#" + methodNameShort + " ] ";
        return new String[]{tag, msg, headString};
    }

    private static String getObjectsString(Object... objects) {

        if (objects.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object == null) {
                    stringBuilder.append("param").append("[").append(i).append("]").append(" = ").append("null").append("\n");
                } else {
                    stringBuilder.append("param").append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
                }
            }
            return stringBuilder.toString();
        } else {
            Object object = objects[0];
            return object == null ? "null" : object.toString();
        }
    }

    private static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }
}
