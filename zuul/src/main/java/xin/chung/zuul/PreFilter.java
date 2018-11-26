package xin.chung.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @Author Chung
 * @Date 2018/11/25 18:41
 */
@Component
public class PreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        // 鉴权
        if ("/provider/hello/send".equals(request.getRequestURI())) {
            if (false) {
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            }
        }

        // 参数校验
        if (StringUtils.isEmpty(request.getParameter("token"))) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return null;
    }
}
