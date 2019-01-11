package com.company.project.utils;

import com.company.project.log.LOGEnum;

public class LogUtils
{
	
	/**
	 * 获取设备日志编号
	 * @return
	 */
	public static String getEquipmentLogNum () {
		long currentTimeMillis = System.currentTimeMillis();
		String num = "DEVICE" + currentTimeMillis;
		return num;
	}
	
	/**
	 * 获取系统日志编号
	 * @return
	 */
	public static String getSysLogNum () {
		long currentTimeMillis = System.currentTimeMillis();
		String num = "SYS" + currentTimeMillis;
		return num;
	}
	
	/**
	 * 解析URI 获取系统日志内容
	 * @param uri
	 * @return
	 */
	public static String getSysLogEnumString(String uri,String m) {
		// 先将m转换位小写
		String method = m.toLowerCase();
		
		String[] resources = uri.split("/");
		String resource = resources[2];
		
		if (resource.startsWith("light")) { // light接口
			if ("get".equals(method)) {
				return LOGEnum.LIGHT_GET.toString();
			} else if ("post".equals(method)) {
				return LOGEnum.LIGHT_POST.toString();
			} else if ("put".equals(method)) {
				return LOGEnum.LIGHT_PUT.toString();
			} else if ("delete".equals(method)) {
				return LOGEnum.LIGHT_DELETE.toString();
			}
		} else if (resource.startsWith("auth")) { // 登录接口、修改密码接口
			if ("post".equals(method)) {
				return LOGEnum.AUTH_POST.toString();
			}else if ("put".equals(method)) {
				return LOGEnum.AUTH_PUT.toString();
			}
		} else if (resource.startsWith("contact")) { // 联系人接口
			if ("get".equals(method)) {
				return LOGEnum.CONTACT_GET.toString();
			} else if ("post".equals(method)) {
				return LOGEnum.CONTACT_POST.toString();
			} else if ("put".equals(method)) {
				return LOGEnum.CONTACT_PUT.toString();
			} else if ("delete".equals(method)) {
				return LOGEnum.CONTACT_DELETE.toString();
			}
		} else if (resource.startsWith("node")) { // 节点接口
			if ("get".equals(method)) {
				return LOGEnum.NODE_GET.toString();
			} else if ("post".equals(method)) {
				return LOGEnum.NODE_POST.toString();
			} else if ("put".equals(method)) {
				return LOGEnum.NODE_PUT.toString();
			} else if ("delete".equals(method)) {
				return LOGEnum.NODE_DELETE.toString();
			}
		} else if (resource.startsWith("user")) { // 用户接口
			if ("get".equals(method)) {
				return LOGEnum.USER_GET.toString();
			} else if ("post".equals(method)) {
				return LOGEnum.USER_POST.toString();
			} else if ("put".equals(method)) {
				return LOGEnum.USER_PUT.toString();
			} else if ("delete".equals(method)) {
				return LOGEnum.USER_DELETE.toString();
			}
		} else if (resource.startsWith("log")) { // 日志接口
			if ("get".equals(method)) {
				return LOGEnum.LOG_GET.toString();
			}
		} else if (resource.startsWith("devicedata")) {
			if ("get".equals(method)) {
				return LOGEnum.DEVICEDATA_GET.toString();
			}
		}else if (resource.startsWith("statistcs")) {
			if ("get".equals(method)) {
				return LOGEnum.DEVICEDATA_GET.toString();
			}
		}
		
		return LOGEnum.ILLEGALOPERATION.toString();
	}
	
	
	/**
	 * 获取设备日志内容
	 * @param uri
	 * @param m
	 * @return
	 */
	public static String getLogEquipmentEnum(String uri,String m) {
		// 先将m转换位小写
		String method = m.toLowerCase();
		
//		String[] resources = uri.split("/");
//		String resource = resources[2];
		
		if ("put".equals(method)) {
			return LOGEnum.LIGHT_CMD_PUT.toString();
		} else if ("get".equals(method)) {
			return LOGEnum.LIGHT_CMD_GET.toString();
		}
		
		return LOGEnum.ILLEGALOPERATION.toString();
	}
}
