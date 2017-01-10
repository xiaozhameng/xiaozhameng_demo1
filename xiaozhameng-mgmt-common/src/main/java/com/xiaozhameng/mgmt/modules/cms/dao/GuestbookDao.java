/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaozhameng.mgmt.modules.cms.dao;

import com.xiaozhameng.mgmt.common.persistence.CrudDao;
import com.xiaozhameng.mgmt.common.persistence.annotation.MyBatisDao;
import com.xiaozhameng.mgmt.modules.cms.entity.Guestbook;

/**
 * 留言DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@MyBatisDao
public interface GuestbookDao extends CrudDao<Guestbook> {

}
