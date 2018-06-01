package me.webapp.support.customPropertyPlaceholder;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public abstract class AbstractCustomPropertyPlaceholderProcessor {

    /**
     * 属性名称是否匹配，匹配时进行处理
     * @param propName
     * @return
     */
    protected abstract boolean match(String propName);

    /**
     * 处理属性值
     * @param propName
     * @param propValue
     * @return
     */
    protected abstract String doProcess(String propName, String propValue);


    String process(String propName, String propValue) {
        if (match(propName)) {
            return doProcess(propName, propValue);
        }
        return propValue;
    }

}
