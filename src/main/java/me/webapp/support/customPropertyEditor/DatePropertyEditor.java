package me.webapp.support.customPropertyEditor;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author paranoidq
 * @since 1.0.0
 */
@EditorTargetType(Date.class)
public class DatePropertyEditor extends PropertyEditorSupport {

    private enum FORMAT {
        F1("yyyy-MM-dd"),
        F2("yyyy-MM-dd hh:mm:ss"),
        F3("yyyy年MM月dd日"),
        F4("yyyy年MM月dd日 hh时mm分ss秒")

        ;

        private String pattern;

        FORMAT(String pattern) {
            this.pattern = pattern;
        }

        public String pattern() {
            return this.pattern;
        }
    }

    private DateFormat formatter1 = new SimpleDateFormat(FORMAT.F1.pattern());
    private DateFormat formatter2 = new SimpleDateFormat(FORMAT.F2.pattern());
    private DateFormat formatter3 = new SimpleDateFormat(FORMAT.F3.pattern());
    private DateFormat formatter4 = new SimpleDateFormat(FORMAT.F4.pattern());


    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // 根据字符串自动确定formatter
        DateFormat formatter = formatter1;
        if (text.contains(":")) {
            formatter = formatter2;
        } else if (text.contains("时")) {
            formatter = formatter4;
        } else if (text.contains("日")) {
            formatter = formatter3;
        }

        // 格式化
        try {
            Date date = formatter.parse(text);
            System.out.println("Date字符串: " + text);
            setValue(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("应用DatePropertyEditor属性编辑器解析Date字符串");

    }
}
