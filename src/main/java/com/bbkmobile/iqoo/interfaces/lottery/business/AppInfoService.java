/**
 * AppInfoService.java
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

/**
 * ClassName:AppInfoService Function: TODO ADD FUNCTION
 * 
 * @author dengkehai
 * @version
 * @since Ver 1.1
 * @Date 2012-1-3 下午3:23:42
 * 
 */
public interface AppInfoService {


    String getApkFilePath(String id, String appVersion, boolean isFirst,
            String target, String patch, String model) throws Exception;
}
