package me.webapp.web.support.limiter;

import com.google.common.base.Objects;
import me.webapp.web.common.WebConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 限流器限流依据
 *
 * 根据请求对象收集信息，用于限流器进行限流
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class RequestLimitInfo {

    /**
     * 请求URL
     */
    private String path;


    /**
     * 唯一token，可以是用户名、用户ID、AccessToken等，用于确定用户
     */
    private String openToken;


    /**
     * 请求时间戳
     */
    private long requestMillis;


    private RequestLimitInfo(String path, String openToken, long requestMillis) {
        this.path = path;
        this.openToken = openToken;
        this.requestMillis = requestMillis;
    }


    public static RequestLimitInfo create(HttpServletRequest request) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        String path = urlPathHelper.getLookupPathForRequest(request);
        String openToken = getOpenToken(request);
        return new RequestLimitInfo(path, openToken, System.currentTimeMillis());
    }


    /**
     * 从request中取openToken
     *
     * @param request
     * @return
     */
    private static String getOpenToken(HttpServletRequest request) {
        String key = WebConstants.LIMITER_OPEN_TOKEN_FETCH_KEY;

        String fetchOpenToken;
        // from parameter
        fetchOpenToken = request.getParameter(key);
        if (!StringUtils.isEmpty(fetchOpenToken)) {
            return fetchOpenToken;
        }
        // from header
        fetchOpenToken = request.getHeader(key);
        if (!StringUtils.isEmpty(fetchOpenToken)) {
            return fetchOpenToken;
        }
        // from cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    fetchOpenToken = cookie.getValue();
                    return fetchOpenToken;
                }
            }
        }
        return WebConstants.LIMITER_OPEN_TOKEN_GLOBAL_DEFAULT;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RequestLimitInfo that = (RequestLimitInfo) object;
        return
            Objects.equal(path, that.path) &&
            Objects.equal(openToken, that.openToken);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(path, openToken);
    }
}
