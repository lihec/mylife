package org.tools.life.web.base;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

public class BaseController {

	/**
	 * 系统日志配置
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * The action execution was a failure.
	 */
	public static final String ERROR = "error/error";

	/** 转到error/error页面时,model里面存储的错位信息的键 */
	public static final String ERRORKEY = "errorMsg";
	/** 未知异常，提示请求失败 */
	public static final String UNKNOWNEXCEPTION = "请求失败";
	public static final String PARAMSVALIDFAIL = "参数错误！";
	public static final String UPDATE_FAIL = "数据更新失败";
	/**
	 * 成功的Status Code
	 */
	private static final int RESCODE_OK = 200;
	/**
	 * 失败的Status Code
	 */
	private static final int RESCODE_FAIL = 201;

	/**
	 * 描述：获取返回结果 创建人：fengsen 创建时间：2012-8-22 备注：
	 * 
	 * @param isOk
	 * @param resCode
	 * @param errorMsg
	 * @param dataMap
	 * @return
	 */
	protected Map<String, Object> getResult(boolean isOk, int resCode,
			String errorMsg, Object obj) {
		return createJson(isOk, resCode, errorMsg, obj);
	}

	/**
	 * 描述：获取成功结果 创建人：fengsen 创建时间：2012-8-22 备注：
	 * 
	 * @param dataMap
	 * @return
	 */
	protected Map<String, Object> getSuccessResult(Object obj) {
		return getResult(true, RESCODE_OK, "操作成功", obj);
	}

	/**
	 * 获取成功信息
	 * 
	 * @author 高金[of706]
	 * @date 2013年12月4日 上午10:41:25
	 * @param msg
	 * @return
	 */
	protected Map<String, Object> getSuccessResult(String msg) {
		return getResult(true, RESCODE_OK, msg, Collections.EMPTY_MAP);
	}

	/**
	 * 获取默认ajax成功信息
	 * 
	 * @author 高金[of706]
	 * @date 2013年12月4日 上午10:41:52
	 * @return
	 */
	protected Map<String, Object> getSuccessResult() {
		return getResult(true, RESCODE_OK, "操作成功！", Collections.EMPTY_MAP);
	}

	/**
	 * 描述：获取失败结果 创建人：fengsen 创建时间：2012-8-22 备注：
	 * 
	 * @param msg
	 * @return
	 */
	protected Map<String, Object> getFailResult(String msg) {
		return getResult(false, RESCODE_FAIL, msg, Collections.EMPTY_MAP);
	}

	/**
	 * 描述：组装json格式返回结果 创建人：fengsen 创建时间：2012-8-22 备注：
	 * 
	 * @param isOk
	 * @param resCode
	 * @param errorMsg
	 * @param obj
	 * @return
	 */
	protected Map<String, Object> createJson(boolean isOk, int resCode,
			String errorMsg, Object obj) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", isOk ? "ok" : "fail");
		jsonMap.put("rescode", resCode);
		jsonMap.put("msg", errorMsg);
		jsonMap.put("data", obj);
		return jsonMap;
	}

	/**
	 * 获取dataTables需要的数据格式
	 * 
	 * @Title: dataTableJson
	 * @author zengxiaoyong_of426
	 * @date 2012-7-23上午7:43:04
	 * @param totalCount
	 *            总数目
	 * @param dataList
	 *            数据列表
	 * @return Map<String,Object> 返回类型
	 * @throws
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	protected Map<String, Object> dataTableJson(int totalCount, List<?> dataList) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("iTotalDisplayRecords", totalCount);
		data.put("iTotalRecords", totalCount);
		data.put("aaData", dataList == null ? Collections.EMPTY_LIST : dataList);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", data);
		map.put("result", "ok");
		return map;
	}

	/**
	 * 描述：获取FORM验证错误信息 创建人：fengsen 创建时间：2012-8-23 备注：
	 * 
	 * @param result
	 * @return
	 */
	protected Map<String, Object> getBadParamResult(BindingResult result) {
		String msg = "参数校验错误";
		List<FieldError> list = result.getFieldErrors();
		if (list != null && !list.isEmpty()) {
			msg = list.get(0).getDefaultMessage();
		}
		return getResult(false, RESCODE_FAIL, msg, Collections.EMPTY_MAP);
	}

	/**
	 * 字符串返回
	 * 
	 * @param data
	 * @return
	 */
	protected String ok(String data) {
		JSONObject json = new JSONObject();
		json.put("result", "ok");
		json.put("data", data);
		return json.toJSONString();
	}

	/**
	 * 字符串返回
	 * 
	 * @param msg
	 * @return
	 */
	protected String fail(String msg) {
		JSONObject json = new JSONObject();
		json.put("result", "fail");
		json.put("msg", msg);
		return json.toJSONString();
	}

	/**
	 * /** ajax返回失败
	 * 
	 * @param resCode
	 * @param msg
	 * @return
	 */
	protected Map<String, Object> ajaxFail(boolean isOk, String resCode,
			String errorMsg, Object obj) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("result", isOk ? "ok" : "fail");
		jsonMap.put("rescode", resCode);
		jsonMap.put("msg", errorMsg);
		jsonMap.put("data", obj);
		return jsonMap;
	}

	/**
	 * 获取会话作用域
	 * 
	 * @author 高金[of706]
	 * @date 2013年12月4日 上午10:40:27
	 * @return
	 */
	protected HttpSession sessionContext() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest().getSession(false);
	}

	/**
	 * 获取请求作用域
	 * 
	 * @author 高金[of706]
	 * @date 2013年12月4日 上午10:40:12
	 * @return
	 */
	protected HttpServletRequest requestContext() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}

	/**
	 * 获取application作用域
	 * 
	 * @author 高金[of706]
	 * @date 2013年12月4日 上午10:39:39
	 * @return
	 */
	protected ServletContext applicationContext() {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest().getSession(false);
		if (null != session) {
			return session.getServletContext();
		}
		return null;
	}

	/**
	 * 根据相对路径获取资源绝对路径
	 * 
	 * @author 高金[of706]
	 * @date 2013年12月4日 上午10:37:51
	 * @param path
	 * @return
	 */
	protected String getRealPath(String path) {
		ServletContext app = applicationContext();
		if (null != app) {
			String root = app.getRealPath(String.valueOf(File.separatorChar));
			return root + path;
		}
		return path;
	}

}
