package com.bbkmobile.iqoo.interfaces.lottery.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbkmobile.iqoo.common.json.JsonParser;
import com.bbkmobile.iqoo.common.net.HttpsURLConnectionUtil;
import com.bbkmobile.iqoo.interfaces.lottery.business.AppInfoService;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryUserInfo;

@Controller
public class LotteryController {

    /**  
     *   
     */
    private final static String LOGIN_URL = "https://usrsys.inner.bbk.com/v2/login?locale=zh_CN";
    @Resource
    private AppInfoService iAppInfoService;
    private final String SessionTag = "loginUser";

    @RequestMapping("/login")
    public void login(HttpServletRequest request,HttpServletResponse response) {
        try {
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            String responseStr = HttpsURLConnectionUtil.getInstance().response(
                    LOGIN_URL + "&account=" + account + "&pwd=" + password);

            JSONObject object = JSONObject.fromObject(responseStr);
            String name = null;
            String temp = null;
            if ("200".equals(object.getString("stat"))) {
                HttpSession session = request.getSession();
                LotteryUserInfo userInfo = JsonParser.readJSON2Bean(responseStr,
                        LotteryUserInfo.class);
                session.setAttribute(SessionTag, userInfo);
                if (userInfo != null) {
                    if (userInfo.getName() != null
                            && !"".equals(userInfo.getName().trim())) {
                        name = userInfo.getName();
                    } else if (userInfo.getEmail() != null
                            && !"".equals(userInfo.getEmail().trim())) {
                        name = userInfo.getEmail();
                    } else if (userInfo.getPhonenum() != null
                            && !"".equals(userInfo.getPhonenum().trim())) {
                        name = userInfo.getPhonenum();
                    }
                }
                temp = "{\"stat\":\"200\", \"msg\":\"登录成功\", \"name\": \""
                        + name + "\"}";
            } else {
                temp = "{\"stat\":\"500\", \"msg\":\"登录失败\"}";
            }
            outwrite(temp, "text/json",response);
        } catch (Exception e) {
            String temp = "{\"stat\":\"500\", \"msg\":\"登录失败\"}";
            try {
                outwrite(temp, "text/json",response);
            } catch (Exception e1) {
            }
        }
    }

    public String lottery() {
        // 1.过滤
        // 2.抽奖
        // 3.返回
        // 4.保存中奖信息
        return null;
    }

    @RequestMapping("/downloadApkFile")
    public String download(HttpServletRequest request,HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            LotteryUserInfo userInfo = session.getAttribute(SessionTag) != null ? (LotteryUserInfo) session
                    .getAttribute(SessionTag) : null;
            //if (userInfo != null) {
                String app_id = request.getParameter("id");

                String model = request.getParameter("model");
                String appVersion = request.getParameter("appVersion");
                boolean isFirst = true; // 是否为断点续传
                String patch = request.getParameter("patch");

                // TODO 添加下载记录
                String filePath = iAppInfoService.getApkFilePath(app_id,
                        appVersion, isFirst, "local", patch, model);
                response.sendRedirect(filePath);
         /*   } else {
                // TODO 跳转到首页
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void outwrite(String content, String contentType,HttpServletResponse response) throws Exception {

        ServletOutputStream out = null;
        try {
            response.setContentType(contentType);
            byte[] contentBytes = content.getBytes("utf-8");
            response.setContentLength(contentBytes.length);
            // response.setHeader("Cache-Control", "no-cache");

            out = response.getOutputStream();
            out.write(contentBytes);

            out.flush();
            out.close();

        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }
    public static void main(String[] args) {
        String temp = "{\"stat\":\"200\", \"msg\":\"登录成功\", \"name\": \"\", \"email\": \"\", \"phonenum\": \"13172481328\"}";
        System.out.println(temp);
    }
}
