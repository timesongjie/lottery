package com.bbkmobile.iqoo.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbkmobile.iqoo.common.Constants;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryUserInfo;

/**
 * @author wangbo
 * @version 1.0.0.0/2011-7-18
 */
public class AuthFilter implements Filter {

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        if (hasLogined(httpReq) || isAllowedURL(httpReq)) {
                chain.doFilter(req, resp);
        } else {
            // 转发到登陆页面
            ((HttpServletResponse) resp).sendRedirect(getLoginURI(httpReq));
        }

    }
    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub

    }
    // 是否登陆
    private Boolean hasLogined(HttpServletRequest req) throws IOException,
            ServletException {
        LotteryUserInfo info = (LotteryUserInfo)req.getSession().getAttribute(Constants.LOGIN_TAG);
        if(info != null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    private Boolean isAllowedURL(HttpServletRequest req) {

        String reqURL = req.getRequestURI();
        if (reqURL.endsWith(".css") || reqURL.endsWith(".js")
                || reqURL.endsWith(".jpg")
                || reqURL.endsWith(".png")
                || reqURL.endsWith("/doLogin")
                || reqURL.endsWith("/index")
                ) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private String getLoginURI(HttpServletRequest req) {
        return Constants.LOGIN_URL;
    }

}
