package dev.saladinobelisario.springboot.app.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostElapsedTimeFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(PreElapsedTimeFilter.class);

    @Override
    public String filterType() {
        return "Post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        log.info("Calculating elapsed time...");

        Long startTime = (Long) request.getAttribute("startTime");
        Long finalTime = System.currentTimeMillis();
        Long elapsedTime = finalTime - startTime;

        log.info(String.format("Elapsed time: %s ms", elapsedTime));
        return null;
    }
}
