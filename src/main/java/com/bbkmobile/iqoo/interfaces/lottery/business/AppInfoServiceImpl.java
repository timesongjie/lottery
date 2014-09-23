/**
 * AppInfoServiceImpl.java
 * com.bbkmobile.iqoo.console.business.appinfo
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2012-1-3 		dengkehai
 *
 * Copyright (c) 2012, TNT All Rights Reserved.
 */

package com.bbkmobile.iqoo.interfaces.lottery.business;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.bbkmobile.iqoo.common.util.cfgfile.PropertiesFileManager;
//import com.bbkmobile.iqoo.common.util.RequestUtil;
import com.bbkmobile.iqoo.interfaces.lottery.dao.AppInfoDAO;
import com.bbkmobile.iqoo.interfaces.lottery.vo.AppInfo;

//import com.bbkmobile.iqoo.platform.auth.User;
/**
 * ClassName:AppInfoServiceImpl Function: TODO ADD FUNCTION
 * 
 * @author dengkehai
 * @version
 * @since Ver 1.1
 * @Date 2012-1-3 下午3:24:12
 * 
 */
@Service("iAppInfoService")
public class AppInfoServiceImpl implements AppInfoService {
    @Resource
    private AppInfoDAO iAppInfoDAO;

    @Override
    public String getApkFilePath(String id, String appVersion, boolean isFirst,
            String target, String patch, String model) throws Exception {

        if (null != appVersion) {
            if (appVersion.matches(".*\\..*")) {
                appVersion = appVersion.replaceAll("\\..*", "");
            }
        } else {
            appVersion = "0";
        }
        String filePath = "";
        String path = "";
        try {

            Long app_id = Long.parseLong(id);
            if (null != target && "baidu".equals(target)) {
                // 给baidu下载地址 直接百度下载暂时不走这个流程
            } else {
                if (app_id > 0) {
                    AppInfo appInfo = iAppInfoDAO.findAppById(app_id);
                    if (isFirst) {
                        iAppInfoDAO.updateDownloadCountForAppInfo(appInfo); // 下载量增加1
                    }

                    path = appInfo.getAppApk();
                    if (null != patch && !"".equals(patch)) {
                        String patchs = appInfo.getPatchs();
                        if (patchs.contains(patch)) {
                            path = path.substring(0, path.lastIndexOf("."))
                                    + "_" + patch + ".patch"; // 补丁路径
                        }
                    }
                    filePath = getDownloadApkHttpURL(path);
                }
            }
            /*
             * String userDir = System.getProperty("user.dir"); filePath =
             * userDir+path; filePath = UtilTool.getDownloadPath(path);
             */

        } catch (Exception e) {
            throw e;
            // Lg.error(LgType.STDOUT, "手机下载apk文件时出错:app_id="+id+",error=" +
            // e.getMessage());
        }
        return filePath;
    }
    public static String getDownloadApkHttpURL(String uri) throws Exception{
        if(null!=uri){
            return PropertiesFileManager.getInstance().getValueFromPropFile("download.apk.url").trim()
            + uri.trim();
        }else{
            return null;
        }
        
    }
}
