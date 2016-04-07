package com.rui.pro1.modules.sys.web;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rui.pro1.common.bean.ResultBean;
import com.rui.pro1.common.bean.page.QueryResult;
import com.rui.pro1.common.exception.ErrorCode;
import com.rui.pro1.modules.sys.entity.Department;
import com.rui.pro1.modules.sys.service.IDepartmentService;
import com.rui.pro1.modules.sys.vo.DepartmentVo;

/**
 * 用户管理
 * 
 * @author ruiliang
 * @date 2016/04/05
 *
 */
@Controller
@RequestMapping("sys/department")
public class DepartmentController extends SysBaseControoler {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IDepartmentService departmentService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean getList(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "page", defaultValue = "20") Integer pagesize,
			DepartmentVo departmentVo) {
		ResultBean rb = new ResultBean();
		try {
			QueryResult<Department> result = departmentService
					.getDepartmentList(page, pagesize, departmentVo);
			rb.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;

	}

	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean get(HttpRequest request, HttpResponse response,
			DepartmentVo departmentVo) {
		ResultBean rb = new ResultBean();
		try {
			Department department = departmentService.get(departmentVo.getId());
			rb.setData(department);
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean del(HttpRequest request, HttpResponse response,
			@RequestParam(required = false, value = "id") Integer id) {
		ResultBean rb = new ResultBean();
		try {
			int count = departmentService.del(id);
			if (count <= 0) {
				rb = new ResultBean(false, ErrorCode.SYS_FAILURE, "操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean add(HttpRequest request, HttpResponse response,
			Department department) {
		ResultBean rb = new ResultBean();
		try {
			int count = departmentService.add(department);
			if (count <= 0) {
				rb = new ResultBean(false, ErrorCode.SYS_FAILURE, "操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean update(HttpRequest request, HttpResponse response,
			Department department) {
		ResultBean rb = new ResultBean();
		try {
			int count = departmentService.update(department);
			if (count <= 0) {
				rb = new ResultBean(false, ErrorCode.SYS_FAILURE, "操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rb = new ResultBean(false, ErrorCode.SYS_ERROR, "异统异常");
		}
		return rb;
	}

}
