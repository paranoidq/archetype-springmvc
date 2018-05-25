package me.webapp.sample.di.methodinjection;

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

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Component
@Scope("singleton")
public abstract class CommanderManager {


    public Object process(Object input) {
        Command command = createCommand();
        // ...
        return null;
    }


    /**
     * 定义一个abstract的函数，成为Look-up method
     *
     * 配置方式：
     *  - xml指定look-up标签，并指定name为抽象函数名，value为返回对象的bean的id
     *  - {@link Lookup}注解，指定value为bean的id
     *
     * 每次调用这个函数时，都会返回一个新的bean实例
     * 这个函数由Spring通过字节码技术生成实现代码
     *
     * 这样，虽然CommandManager是singleton，但Command的prototype特性依然可以得到保证
     * 相反，如果直接进行依赖注入，那在CommandManager被创建的时候，就只能唯一绑定一个Command实例，
     * 无法做到每次调用process都返回新的Command实例
     *
     *
     * @return
     */
    @Lookup("concreteCommand")
    public abstract Command createCommand();


}
