package me.webapp.common.util.object;

import java.io.*;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public final class ObjectUtils {


    /**
     * 对象深拷贝
     *
     * 内部实现采用对象序列化和反序列化的方式支持深拷贝
     *
     *
     * @param obj
     * @param <T> 序列化对象必须实现Serializable接口
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     *
     */
    public static <T extends Serializable> T deepClone(T obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);

        byte[] objectByteArray = byteArrayOutputStream.toByteArray();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(objectByteArray);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return (T) objectInputStream.readObject();
    }

}
