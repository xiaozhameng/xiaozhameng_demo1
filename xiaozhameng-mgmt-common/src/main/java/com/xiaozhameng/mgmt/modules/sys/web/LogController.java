/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaozhameng.mgmt.modules.sys.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaozhameng.mgmt.modules.sys.entity.Log;
import com.xiaozhameng.mgmt.modules.sys.utils.LogUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaozhameng.common.entity.Page;
import com.xiaozhameng.mgmt.common.web.BaseController;
import com.xiaozhameng.mgmt.modules.sys.service.LogService;

/**
 * 日志Controller
 * @author ThinkGem
 * @version 2013-6-2
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;
	
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = {"list", ""})
	public String list(Log log, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Log> page = logService.findPage(new Page<Log>(request, response), log); 
        Map<String,String> map = LogUtils.getLogMenu();
        model.addAttribute("menuMap", map);
        model.addAttribute("page", page);
		return "modules/sys/logList";
	}

}
