package com.bbkmobile.iqoo.interfaces.lottery.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bbkmobile.iqoo.common.Constants;
import com.bbkmobile.iqoo.common.json.ResultObject;
import com.bbkmobile.iqoo.common.lottery.Lottery;
import com.bbkmobile.iqoo.common.lottery.UserCenterInfo;
import com.bbkmobile.iqoo.common.net.HttpsURLConnectionUtil;
import com.bbkmobile.iqoo.interfaces.lottery.business.LotteryService;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryDownloadRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryUserInfo;

@Controller
public class LotteryController {

    /**  
     *   
     */

//    @Resource
//    private AppInfoService iAppInfoService;
    @Resource
    private LotteryService lotteryServiceImpl;
    private String appstore = "http://appstore.vivo.com.cn/appinfo/downloadApkFile?app_version=550&id="; 
    @RequestMapping("/home")
    public String home(HttpServletRequest request) {
        LotteryUserInfo userInfo = (LotteryUserInfo) request.getSession()
                .getAttribute(Constants.LOGIN_TAG);
        if (userInfo != null) {
            return "index";
        }else{
            return "home";
        }
    }
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        LotteryUserInfo userInfo = (LotteryUserInfo) request.getSession()
                .getAttribute(Constants.LOGIN_TAG);
        if (userInfo != null) {
            return "index";
        }else{
            return "redirect:"+Constants.LOGIN_URL;
        }
    }
    @RequestMapping("/lottery")
    public LotteryRecord lottery(HttpServletRequest request,
            HttpServletResponse response) {
        // TODO 活动结束日期限制
        try {
            LotteryRecord record = new LotteryRecord();
            LotteryUserInfo userInfo = (LotteryUserInfo) request.getSession()
                    .getAttribute(Constants.LOGIN_TAG);
            if (userInfo != null) {
                Lottery lottery = null;
                // 次数判断
                int alreadyClick = lotteryServiceImpl
                        .countClickTimesByUserId(userInfo.getId());
                if (alreadyClick < Constants.totoal) {
                    // 抽奖
                    lottery = lotteryServiceImpl.lottery(userInfo);
                    if (lottery != null) {
                        // 设置奖品级别
                        record.setLottery_grade(Integer.valueOf(lottery
                                .getGrade()));
                        record.setSn(lottery.getCode());
                    } else {// 未中奖
                        record.setLottery_grade(0);
                    }
                    record.setUserId(userInfo.getId());

                    int lastCount = Constants.totoal - alreadyClick - 1;
                    record.setLastCount(lastCount > 0 ? lastCount : 0);
                    // 返回抽奖次数，并更新前台
                }else{
                    record.setLastCount(0);   
                }
            }
            return record;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/download")
    public String download(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            LotteryUserInfo userInfo = session
                    .getAttribute(Constants.LOGIN_TAG) != null ? (LotteryUserInfo) session
                    .getAttribute(Constants.LOGIN_TAG) : null;
//            if (userInfo != null) {
                String app_id = request.getParameter("id");
//
//                String appVersion = request.getParameter("appVersion");
//                boolean isFirst = true; // 是否为断点续传
//                String patch = request.getParameter("patch");

                // 添加下载记录
                LotteryDownloadRecord record = new LotteryDownloadRecord();
                record.setAppId(app_id);
                if(userInfo != null){
                    record.setUserId(userInfo.getId());
                }
                lotteryServiceImpl.addLotteryDownloadRecord(record);

//                String filePath = iAppInfoService.getApkFilePath(app_id,
//                        appVersion, isFirst, "local", patch);
                response.sendRedirect(appstore+app_id);
//            } 
//            else {
//                return "redirect:" + Constants.LOGIN_URL;
//            }
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
            String client_id = Constants.CLIENT_ID;
            String client_secret = Constants.CLIENT_SECRET;

            UserCenterInfo info = new UserCenterInfo(code, openid, openkey,
                    client_id, client_secret);
            // 获取token
            String token = getTonken(info);
            if (token != null) {
                // 获取用户信息,并保存到session
                UserCenterInfo uInfo = getUserInfo(info, request);
                if (uInfo != null) {
                    return "/index";
                }
            }
        } catch (Exception e) {
        }
        return "redirect:" + Constants.LOGIN_URL;
    }

    private UserCenterInfo getUserInfo(UserCenterInfo info,
            HttpServletRequest request) {
        if (info != null) {
            // String responseStr =
            // HttpsURLConnectionUtil.getInstance().responseHttps(
            // Constants.GETINFO_URL + "?access_token=" + info.getToken()
            // + "&openid=" + info.getOpenid() + "&client_id="
            // + info.getClient_id());
            String responseStr = HttpsURLConnectionUtil.getInstance()
                    .responseHttp(
                            Constants.GETINFO_URL + "?access_token="
                                    + info.getToken() + "&openid="
                                    + info.getOpenid() + "&client_id="
                                    + info.getClient_id());
            JSONObject object = JSONObject.fromObject(responseStr);
            if (object != null && "200".equals(object.getString("stat"))) {
                String id = object.getString("id");
                String email = object.getString("email");
                String name = object.getString("name");
                String phonenum = object.getString("phonenum");

                LotteryUserInfo userInfo = new LotteryUserInfo(id, name, email,
                        phonenum);
                HttpSession session = request.getSession();
                session.setAttribute(Constants.LOGIN_TAG, userInfo);
                return info;
            }
        }
        return null;
    }

    @RequestMapping("/clickTimes")
    public ResultObject<Integer> clickTimes(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            LotteryUserInfo userInfo = session
                    .getAttribute(Constants.LOGIN_TAG) != null ? (LotteryUserInfo) session
                    .getAttribute(Constants.LOGIN_TAG) : null;
            if (userInfo != null) {
                Integer times = lotteryServiceImpl
                        .countClickTimesByUserId(userInfo.getId());
                ResultObject<Integer> result = new ResultObject<Integer>();
                result.setResult(true);
                int lastCount = Constants.totoal - times;
                result.setValue(lastCount > 0 ? lastCount : 0);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/owns")
    public ResultObject<List<LotteryRecord>> getOwnRecords(
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        LotteryUserInfo userInfo = session.getAttribute(Constants.LOGIN_TAG) != null ? (LotteryUserInfo) session
                .getAttribute(Constants.LOGIN_TAG) : null;
        if (userInfo != null) {
            try {
                List<LotteryRecord> list = lotteryServiceImpl
                        .getOwnsRecords(userInfo);
                ResultObject<List<LotteryRecord>> result = new ResultObject<List<LotteryRecord>>();
                result.setResult(true);
                result.setValue(list);
                return result;
            } catch (Exception e) {
            }
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

    private String getTonken(UserCenterInfo info) {
        // String responseStr =
        // HttpsURLConnectionUtil.getInstance().responseHttps(
        // Constants.GETACCESSTOKEN_URL + "?code=" + info.getCode() + "&openid="
        // + info.getOpenid() + "&openkey=" + info.getOpenkey()
        // + "&client_id=" + info.getClient_id()
        // + "&client_secret=" + info.getClient_secret());
        String responseStr = HttpsURLConnectionUtil.getInstance().responseHttp(
                Constants.GETACCESSTOKEN_URL + "?code=" + info.getCode()
                        + "&openid=" + info.getOpenid() + "&openkey="
                        + info.getOpenkey() + "&client_id="
                        + info.getClient_id() + "&client_secret="
                        + info.getClient_secret());
        JSONObject object = JSONObject.fromObject(responseStr);
        if (object != null && "200".equals(object.getString("stat"))) {
            String token = object.getString("accesstoken");
            info.setToken(token);
            return token;
        }
        return null;
    }
}
