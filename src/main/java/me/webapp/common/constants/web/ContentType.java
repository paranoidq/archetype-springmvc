package me.webapp.common.constants.web;

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

import me.webapp.common.constants.Encoding;

import java.io.Serializable;
import java.nio.charset.Charset;

/**
 * 工具类：提供各种http content-type类型
 *
 * @author paranoidq
 * @since 1.0.0
 */
public final class ContentType implements Serializable {

    private static final long serialVersionUID = -7768694718232371896L;

    // constants
    public static final ContentType APPLICATION_ATOM_XML = create(
        "application/atom+xml", Encoding.ISO_8859_1);
    public static final ContentType APPLICATION_FORM_URLENCODED = create(
        "application/x-www-form-urlencoded", Encoding.ISO_8859_1);
    public static final ContentType APPLICATION_JSON = create(
        "application/json", Encoding.UTF_8);
    public static final ContentType APPLICATION_OCTET_STREAM = create(
        "application/octet-stream", (Charset) null);
    public static final ContentType APPLICATION_SVG_XML = create(
        "application/svg+xml", Encoding.ISO_8859_1);
    public static final ContentType APPLICATION_XHTML_XML = create(
        "application/xhtml+xml", Encoding.ISO_8859_1);
    public static final ContentType APPLICATION_XML = create(
        "application/xml", Encoding.ISO_8859_1);
    public static final ContentType MULTIPART_FORM_DATA = create(
        "multipart/form-data", Encoding.ISO_8859_1);
    public static final ContentType TEXT_HTML = create(
        "text/html", Encoding.ISO_8859_1);
    public static final ContentType TEXT_PLAIN = create(
        "text/plain", Encoding.ISO_8859_1);
    public static final ContentType TEXT_XML = create(
        "text/xml", Encoding.ISO_8859_1);
    public static final ContentType WILDCARD = create(
        "*/*", (Charset) null);

    // defaults
    public static final ContentType DEFAULT_TEXT = TEXT_PLAIN;
    public static final ContentType DEFAULT_BINARY = APPLICATION_OCTET_STREAM;

    private final String mimeType;
    private final Charset charset;


    ContentType(
        final String mimeType,
        final Charset charset) {
        this.mimeType = mimeType;
        this.charset = charset;
    }

    /**
     * 输出content-type
     *
     * eg. application/json; utf-8
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(mimeType);
        if (this.charset != null) {
            sb.append("; charset=");
            sb.append(this.charset.name());
        }
        return sb.toString();
    }

    /**
     * Creates a new instance of {@link org.apache.http.entity.ContentType}.
     *
     * @param mimeType MIME type. It may not be {@code null} or empty. It may not contain
     *        characters {@code <">, <;>, <,>} reserved by the HTTP specification.
     * @param charset charset.
     * @return content type
     */
    public static ContentType create(final String mimeType, final Charset charset) {
        if (mimeType == null || mimeType.equals("")) {
            throw new IllegalArgumentException("mineType must not be empty");
        }
        return new ContentType(mimeType, charset);
    }


    public String getMimeType() {
        return mimeType;
    }

    public Charset getCharset() {
        return charset;
    }
}