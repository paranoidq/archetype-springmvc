package me.webapp.domain;

/*-
 * ========================LICENSE_START=================================
 * springmvc
 * %%
 * Copyright (C) 2018 Wei Qian
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =========================LICENSE_END==================================
 */

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
