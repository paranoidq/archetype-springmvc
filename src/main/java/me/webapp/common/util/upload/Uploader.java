package me.webapp.common.util.upload;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public interface Uploader {

    boolean upload(String name, byte[] content, String path);
}
