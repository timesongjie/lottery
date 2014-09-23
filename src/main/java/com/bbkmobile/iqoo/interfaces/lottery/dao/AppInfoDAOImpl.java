/**
 * AppInfoDAOImpl.java
 * com.bbkmobile.iqoo.console.dao.appinfo
 *
 * Function：  
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2012-1-3 		dengkehai
 *
 * Copyright (c) 2012, TNT All Rights Reserved.
 */

package com.bbkmobile.iqoo.interfaces.lottery.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bbkmobile.iqoo.interfaces.lottery.vo.AppInfo;

/**
 * ClassName:AppInfoDAOImpl Function:
 * 
 * @author dengkehai
 * @version
 * @since Ver 1.1
 * @Date 2012-1-3 下午3:25:31
 * 
 */
@Repository("iAppInfoDAO")
public class AppInfoDAOImpl implements AppInfoDAO {

    /**
     * 根据id查询应用信息
     * 
     * (non-Javadoc)
     * 
     * @see com.bbkmobile.iqoo.console.dao.appinfo.AppInfoDAO#findAppById(java.lang.Long)
     */
    @Resource
    private SqlSession sqlSession;
    @Override
    public AppInfo findAppById(Long app_id) throws Exception {

        // return getHibernateTemplate().load(AppInfo.class, app_id);

        AppInfo appInfo = null;
        List<AppInfo> appInfos = sqlSession.selectList("appInfomapper.selectAppInfoByID", app_id);
        if (null != appInfos && appInfos.size() > 0) {
            return appInfo = (AppInfo) appInfos.get(0);
        }
        return appInfo;
    }
    @Override
    public void updateDownloadCountForAppInfo(AppInfo appInfo) throws Exception {
        sqlSession.update("appInfomapper.updateAppInfo",appInfo);
    }
}
