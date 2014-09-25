package com.bbkmobile.iqoo.interfaces.lottery.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbkmobile.iqoo.common.Constants;
import com.bbkmobile.iqoo.common.UUIDGenerator;
import com.bbkmobile.iqoo.common.json.ResultObject;
import com.bbkmobile.iqoo.common.lottery.Lottery;
import com.bbkmobile.iqoo.common.lottery.UserCenterInfo;
import com.bbkmobile.iqoo.common.net.HttpsURLConnectionUtil;
import com.bbkmobile.iqoo.interfaces.lottery.business.AppInfoService;
import com.bbkmobile.iqoo.interfaces.lottery.business.LotteryService;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryDownloadRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryUserInfo;

@Controller
public class LotteryController {

    /**  
     *   
     */

    @Resource
    private AppInfoService iAppInfoService;
    @Resource
    private LotteryService lotteryServiceImpl;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/lottery")
    public LotteryRecord lottery(HttpServletRequest request,
            HttpServletResponse response) {
        // 抽奖
        try {
            LotteryRecord record = null;
            // if (userInfo != null) {
            Lottery lottery = null;
            lottery = lotteryServiceImpl.lottery(1);
            if (lottery != Lottery.NONLOTTERY) {
                // 生成唯一SN码
                lottery.setSn(UUIDGenerator.getUUID());
            }
            // 生成中奖记录
            record = new LotteryRecord();
            // record.setUserId(userInfo.getName());
            record.setLottery_grade(lottery.getGrand());
            record.setSn(lottery.getSn());
            record.setLottery_date(new Date());
            // }
            return record;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 保存中奖信息
        return null;
    }

    @RequestMapping("/downloadApkFile")
    public String download(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            LotteryUserInfo userInfo = session.getAttribute(Constants.LOGIN_TAG) != null ? (LotteryUserInfo) session
                    .getAttribute(Constants.LOGIN_TAG) : null;
            if (userInfo != null) {
                String app_id = request.getParameter("id");

                String model = request.getParameter("model");
                String appVersion = request.getParameter("appVersion");
                boolean isFirst = true; // 是否为断点续传
                String patch = request.getParameter("patch");

                // 添加下载记录
                LotteryDownloadRecord record = new LotteryDownloadRecord();
                record.setAppId(app_id);
                record.setUserId(getUserName(userInfo));
                lotteryServiceImpl.addLotteryDownloadRecord(record);

                String filePath = iAppInfoService.getApkFilePath(app_id,
                        appVersion, isFirst, "local", patch, model);
                response.sendRedirect(filePath);
            } else {
                return "redirect:"+Constants.LOGIN_URL;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/records")
    public ResultObject<List<LotteryRecord>> lotteryRecords() {
        try {
            List<LotteryRecord> list = lotteryServiceImpl.getTop10Records();
            ResultObject<List<LotteryRecord>> result = new ResultObject<List<LotteryRecord>>();
            result.setResult(true);
            result.setValue(list);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String code = request.getParameter("code");
            String openid = request.getParameter("openid");
            String openkey = request.getParameter("openkey");
            String client_id = "4";
            String client_secret = "123456";

            UserCenterInfo info = new UserCenterInfo(code, openid, openkey,
                    client_id, client_secret);
            // 获取token
            String token = getTonken(info);
            if(token != null){
                // 获取用户信息,并保存到session
                UserCenterInfo uInfo = getUserInfo(info, request);
                if(uInfo != null){
                    return "/index";
                }
            }
        } catch (Exception e) {
        }
        return "redirect:"+Constants.LOGIN_URL;
    }
    private String getUserName(LotteryUserInfo userInfo) {
        String name = null;
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
        return name;
    }

    private UserCenterInfo getUserInfo(UserCenterInfo info, HttpServletRequest request) {
        if (info != null) {
//            String responseStr = HttpsURLConnectionUtil.getInstance().responseHttps(
//                    Constants.GETINFO_URL + "?access_token=" + info.getToken()
//                            + "&openid=" + info.getOpenid() + "&client_id="
//                            + info.getClient_id());
            String responseStr = HttpsURLConnectionUtil.getInstance().responseHttp(
                    Constants.GETINFO_URL + "?access_token=" + info.getToken()
                            + "&openid=" + info.getOpenid() + "&client_id="
                            + info.getClient_id());
            JSONObject object = JSONObject.fromObject(responseStr);
            if (object != null && "200".equals(object.getString("stat"))) {
                String id = object.getString("id");
                String email = object.getString("email");
                String name = object.getString("name");
                String phonenum = object.getString("phonenum");
                
                LotteryUserInfo userInfo = new LotteryUserInfo(Integer.valueOf(id),name,email,phonenum);
                HttpSession session = request.getSession();
                session.setAttribute(Constants.LOGIN_TAG, userInfo);
                return info;
            }
        }
        return null;
    }

    private String getTonken(UserCenterInfo info) {
//        String responseStr = HttpsURLConnectionUtil.getInstance().responseHttps(
//                Constants.GETACCESSTOKEN_URL + "?code=" + info.getCode() + "&openid="
//                        + info.getOpenid() + "&openkey=" + info.getOpenkey()
//                        + "&client_id=" + info.getClient_id()
//                        + "&client_secret=" + info.getClient_secret());
        String responseStr = HttpsURLConnectionUtil.getInstance().responseHttp(
                Constants.GETACCESSTOKEN_URL + "?code=" + info.getCode() + "&openid="
                        + info.getOpenid() + "&openkey=" + info.getOpenkey()
                        + "&client_id=" + info.getClient_id()
                        + "&client_secret=" + info.getClient_secret());
        JSONObject object = JSONObject.fromObject(responseStr);
        if (object != null && "200".equals(object.getString("stat"))) {
            String token = object.getString("accesstoken");
            info.setToken(token);
            return token;
        }
        return null;
    }
}
