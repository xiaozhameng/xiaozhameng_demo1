/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaozhameng.mgmt.modules.sys.dao;

import com.xiaozhameng.mgmt.common.persistence.TreeDao;
import com.xiaozhameng.mgmt.common.persistence.annotation.MyBatisDao;
import com.xiaozhameng.mgmt.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
