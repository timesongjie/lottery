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

import com.bbkmobile.iqoo.common.UUIDGenerator;
import com.bbkmobile.iqoo.common.json.JsonParser;
import com.bbkmobile.iqoo.common.json.ResultObject;
import com.bbkmobile.iqoo.common.lottery.Lottery;
import com.bbkmobile.iqoo.common.net.HttpsURLConnectionUtil;
import com.bbkmobile.iqoo.interfaces.lottery.business.AppInfoService;
import com.bbkmobile.iqoo.interfaces.lottery.business.LotteryService;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LoginResult;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryDownloadRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryRecord;
import com.bbkmobile.iqoo.interfaces.lottery.vo.LotteryUserInfo;

@Controller
public class LotteryController {

    /**  
     *   
     */
    private final static String LOGIN_URL = "https://usrsys.inner.bbk.com/v2/login?locale=zh_CN";
    @Resource
    private AppInfoService iAppInfoService;
    @Resource
    private LotteryService lotteryServiceImpl;
    
    
    private final String SessionTag = "loginUser";

    @RequestMapping("/login")
    public LoginResult login(HttpServletRequest request, HttpServletResponse response) {
        String name = null;
        LoginResult result = new LoginResult();
        try {
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            String responseStr = HttpsURLConnectionUtil.getInstance().response(
                    LOGIN_URL + "&account=" + account + "&pwd=" + password);

            JSONObject object = JSONObject.fromObject(responseStr);
           
            if ("200".equals(object.getString("stat"))) {
                HttpSession session = request.getSession();
                LotteryUserInfo userInfo = JsonParser.readJSON2Bean(
                        responseStr, LotteryUserInfo.class);
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
                result.setStat("200");
                result.setMessage("登录成功");
                result.setUserName(name);
            } else {
                result.setStat("500");
                result.setMessage("登录失败");
            }
        } catch (Exception e) {
            result.setStat("500");
            result.setMessage("登录失败");
        }
        return result;
    }
    @RequestMapping("/lottery")
    public LotteryRecord lottery(HttpServletRequest request, HttpServletResponse response) {
        // 抽奖
        try {
            HttpSession session = request.getSession();
            LotteryUserInfo userInfo = session.getAttribute(SessionTag) != null ? (LotteryUserInfo) session
                    .getAttribute(SessionTag) : null;
            LotteryRecord record = null;
            //if (userInfo != null) {
                Lottery lottery = null;
                lottery = lotteryServiceImpl.lottery(1);
                if(lottery != Lottery.NONLOTTERY){
                    //生成唯一SN码
                    lottery.setSn(UUIDGenerator.getUUID());
                }
                //生成记录
                record =  new LotteryRecord();
                //record.setUserId(String.valueOf(userInfo.getId()));
                record.setLottery_grade(lottery.getGrand());
                record.setSn(lottery.getSn());
                record.setLottery_date(new Date());
            //}
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
            LotteryUserInfo userInfo = session.getAttribute(SessionTag) != null ? (LotteryUserInfo) session
                    .getAttribute(SessionTag) : null;
            if (userInfo != null) {
                String app_id = request.getParameter("id");

                String model = request.getParameter("model");
                String appVersion = request.getParameter("appVersion");
                boolean isFirst = true; // 是否为断点续传
                String patch = request.getParameter("patch");
                

                //添加下载记录
                LotteryDownloadRecord record = new LotteryDownloadRecord();
                record.setAppId(app_id);
                record.setUserId(String.valueOf(userInfo.getId()));
                lotteryServiceImpl.addLotteryDownloadRecord(record);
                
                String filePath = iAppInfoService.getApkFilePath(app_id,
                        appVersion, isFirst, "local", patch, model);
                response.sendRedirect(filePath);
            } else {
                // TODO 跳转到首页
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/records")
    public ResultObject<List<LotteryRecord>> lotteryRecords(){
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
}
