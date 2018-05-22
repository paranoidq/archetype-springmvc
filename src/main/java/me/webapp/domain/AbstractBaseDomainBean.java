package me.webapp.domain;

import me.webapp.common.util.object.ObjectUtils;
import me.webapp.exception.DomainException;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 *
 * 基础domain bean
 *
 * 该抽象domain bean提供了以下通用的功能或约定：
 *      - 声明了Serializable接口，满足domain序列化的条件
 *      - 实现了通用的clone方法和toString方法
 *
 * 所有的domain bean应该继承该类，
 *
 * @author paranoidq
 * @since 1.0.0
 */
public abstract class AbstractBaseDomainBean implements Serializable {

    /**
     * domain bean的通用的clone方法
     *
     * 内部实现采用了对象序列化的方式进行深拷贝
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            return ObjectUtils.deepClone(this);
        } catch (IOException e) {
            throw new DomainException("域对象clone失败", e);
        } catch (ClassNotFoundException e) {
            throw new DomainException("域对象clone失败", e);
        }
    }

    /**
     * domain bean的通用的toString方法
     *
     * 内部实现通过反射获取非static的实例域，并获取域名和值进行拼接
     * @return
     */
    @Override
    public String toString() {
        Field[] dFields = this.getClass().getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        try {
            for (Field f : dFields) {
                f.setAccessible(true);
                int modifiers = f.getModifiers();
                if (!Modifier.isStatic(modifiers)) {
                        sb.append(",").append(f.getName()).append("=[").append(f.get(this)).append("]");
                }
            }
        } catch (IllegalAccessException e) {
            throw new DomainException("无法通过反射生成toString字符串", e);
        }
        return sb.substring(1).toString();
    }
}
