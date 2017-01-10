/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaozhameng.mgmt.modules.cms.service;

import com.xiaozhameng.mgmt.modules.cms.entity.ArticleData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaozhameng.mgmt.common.service.CrudService;
import com.xiaozhameng.mgmt.modules.cms.dao.ArticleDataDao;

/**
 * 站点Service
 * @author ThinkGem
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = false)
public class ArticleDataService extends CrudService<ArticleDataDao, ArticleData> {

}
