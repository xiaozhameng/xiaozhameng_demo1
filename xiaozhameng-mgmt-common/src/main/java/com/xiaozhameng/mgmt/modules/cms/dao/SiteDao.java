/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaozhameng.mgmt.modules.cms.dao;

import com.xiaozhameng.mgmt.common.persistence.CrudDao;
import com.xiaozhameng.mgmt.common.persistence.annotation.MyBatisDao;
import com.xiaozhameng.mgmt.modules.cms.entity.Site;

/**
 * 站点DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@MyBatisDao
public interface SiteDao extends CrudDao<Site> {

}
