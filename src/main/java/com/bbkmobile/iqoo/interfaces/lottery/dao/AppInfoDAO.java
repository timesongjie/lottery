/**
 * AppInfoDAO.java
 * com.bbkmobile.iqoo.console.dao.appinfo
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2012-1-3 		dengkehai
 *
 * Copyright (c) 2012, TNT All Rights Reserved.
 */

package com.bbkmobile.iqoo.interfaces.lottery.dao;

import com.bbkmobile.iqoo.interfaces.lottery.vo.AppInfo;

/**
 * ClassName:AppInfoDAO Function: TODO ADD FUNCTION
 * 
 * @author dengkehai
 * @version
 * @since Ver 1.1
 * @Date 2012-1-3 下午3:24:59
 * 
 */
public interface AppInfoDAO {


    AppInfo findAppById(Long app_id) throws Exception;

    void updateDownloadCountForAppInfo(AppInfo appInfo) throws Exception;

}
