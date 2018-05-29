package me.webapp.common.util.reflection;

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


import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author paranoidq
 * @since 0.1
 */
public class ReflectionUtil {


    /**
     *
     * 从指定的package中加载子类
     * <href>http://stackoverflow.com/questions/520328/can-you-find-all-classes-in-a-package-using-reflection</href>
     *
     * @update 修复问题：加载类的时候会一并加载abstract类，导致newInstance实例化失败
     *
     * @param packageName
     * @param superClass
     * @return
     * @throws IOException
     */
    public static List<Class<?>> getClassesBySuperClass(String packageName, Class<?> superClass) throws IOException {
        List<Class<?>> classes = Lists.newArrayList();
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            if (info.getName().startsWith(packageName)) {
                Class c = info.load();
                if (superClass.isAssignableFrom(c) && !superClass.equals(c)) {
                    classes.add(c);
                }
            }
        }
        return classes;
    }

    public static List<Class<?>> getClassesByAnnotation(String packageName, Class<? extends Annotation> annotationClass) throws IOException {
        List<Class<?>> classes = Lists.newArrayList();
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            if (info.getName().startsWith(packageName)) {
                Class c = info.load();
                if (c.isAnnotationPresent(annotationClass)) {
                    classes.add(c);
                }
            }
        }
        return classes;
    }


    /**
     * 从jar包中加载指定类的子类
     * @param path
     * @param superClass
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static List<Class<?>> getClassesFromJar(String path, Class<?> superClass) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = Lists.newArrayList();
        JarFile jarFile = new JarFile(path);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = {new URL("jar:file:" + path + "!/")};
        URLClassLoader cl = URLClassLoader.newInstance(urls);
        while (e.hasMoreElements()) {
            JarEntry jarEntry = e.nextElement();
            if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class")) {
                continue;
            }
            // -6, because of .class postfix
            String className = jarEntry.getName().substring(0, jarEntry.getName().length() - 6);
            className = className.replace('/', '.');
            Class c = cl.loadClass(className);
            if (superClass.isAssignableFrom(c)) {
                classes.add(c);
            }
        }
        return classes;
    }
    
    public static void setFieldAccessible(final Field field) {
		AccessController.doPrivileged(new PrivilegedAction<Object>() {
			public Object run() {
				field.setAccessible(true);
				return null;
			}
		});
	}
}
