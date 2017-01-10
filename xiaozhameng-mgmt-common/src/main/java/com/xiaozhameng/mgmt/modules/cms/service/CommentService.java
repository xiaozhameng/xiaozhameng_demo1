/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaozhameng.mgmt.modules.cms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaozhameng.common.entity.Page;
import com.xiaozhameng.mgmt.common.service.CrudService;
import com.xiaozhameng.mgmt.modules.cms.dao.CommentDao;
import com.xiaozhameng.mgmt.modules.cms.entity.Comment;

/**
 * 评论Service
 * @author ThinkGem
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = false)
public class CommentService extends CrudService<CommentDao, Comment> {

	public Page<Comment> findPage(Page<Comment> page, Comment comment) {
//		DetachedCriteria dc = commentDao.createDetachedCriteria();
//		if (StringUtils.isNotBlank(comment.getContentId())){
//			dc.add(Restrictions.eq("contentId", comment.getContentId()));
//		}
//		if (StringUtils.isNotEmpty(comment.getTitle())){
//			dc.add(Restrictions.like("title", "%"+comment.getTitle()+"%"));
//		}
//		dc.add(Restrictions.eq(Comment.FIELD_DEL_FLAG, comment.getDelFlag()));
//		dc.addOrder(Order.desc("id"));
//		return commentDao.find(page, dc);
		comment.getSqlMap().put("dsf", dataScopeFilter(comment.getCurrentUser(), "o", "u"));
		
		return super.findPage(page, comment);
	}
	
	public void delete(Comment entity, Boolean isRe) {
		super.delete(entity);
	}
}
