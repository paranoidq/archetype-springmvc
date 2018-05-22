/**
 * @author paranoidq
 * @since 1.0.0
 *
 * 1. 指定{@link me.webapp.common.util.customs.ApiVersion}，并自动构建到url中
 * 2. 指定{@link org.springframework.web.bind.annotation.RequestMapping}的继承关系，通过继承关系构建URL
 * 3. 构建所有的URL之后，集中输出到日志，并文档化，便于以API文档的形式呈现给API使用者
 *
 */
package me.webapp.common.util.customs;