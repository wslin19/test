package com.company.project.log.aspect;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.company.project.dao.LogSysMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.LogSys;
import com.company.project.model.User;
import com.company.project.utils.LogUtils;
import com.company.project.utils.RequestUtils;
import com.company.project.utils.TokenUtils;

//@Aspect
//@Component
public class LOGAspect
{
	private static final Logger LOG = LoggerFactory.getLogger(LOGAspect.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private LogSysMapper logSysMapper;
	
//	@Autowired
//	private LightMapper lightMapper;
	
//	@Autowired
//	private LogEquipmentMapper logEquipmentMapper;
	
	@Pointcut("execution(public * com.company.project.web.*.*(..))")
	public void controllerAspect() {}
	
	@Pointcut("execution(public * com.company.project.web.AuthController.login(*))")
	public void beforeLogin() {}
	
	
	@AfterReturning(returning = "result", pointcut = "controllerAspect()")
	public void afterController(JoinPoint joinPoint,Object result) {
		
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		String uri = RequestUtils.getURI(request);
		String method = RequestUtils.getMethod(request);
		
		
		// 登录时没有Token，所以登录的日志单独做，这里排除登录
		if (uri.contains("auth") && "post".equals(method.toLowerCase())) {
			return ;
		}
		
		String token = request.getHeader("token");
		boolean equipmentLog = uri.contains("cmd"); // 判断是设备日志还是系统日志，true：设备日志，false：系统日志
		if (equipmentLog) {
			// 设备日志
			
//			Light light = lightMapper.selectByPrimaryKey(lightId);
//			String attrNum = light.getAttrNum(); // 编号
//			String attrBelonging = light.getAttrBelonging();
//			String lightName = attrBelonging + attrNum; //　灯具名称
//			String equipmentLogNum = LogUtils.getEquipmentLogNum(); // 设备日志编号
//			
//			LogEquipment logEquipment = new LogEquipment();
//			logEquipment.setContent(LOGEnum.LIGHT_CMD_FRESH.toString());
//			logEquipment.setLampname(lightName);
//			logEquipment.setLampnum(attrNum);
//			logEquipment.setNum(equipmentLogNum);
			
//			logEquipmentMapper.insertSelective(logEquipment);
		} else {
			// 系统日志
			LOG.info("--记录系统日志--");
			String sysLogEnum = LogUtils.getSysLogEnumString(uri, method);
			Map<String, Claim> claims = TokenUtils.verifyToken(token);
			String uid = TokenUtils.getInfo(claims, "uid");
			Integer id = Integer.parseInt(uid);
			
			User user = userMapper.selectByPrimaryKey(id);
			String operator = user.getUsername();
			String sysLogNum = LogUtils.getSysLogNum();
			String ipAddress = RequestUtils.getIpAddress(request);
			
			LogSys logSys = new LogSys();
			logSys.setContent(sysLogEnum);
			logSys.setIpaddress(ipAddress);
			logSys.setNum(sysLogNum);
			logSys.setOperator(operator);
			
			JSONObject resultJson = JSONObject.parseObject(result.toString());
			String code = resultJson.getString("code");
			if ("200".equals(code)) {
				LOG.info("--日志记录结果：操作成功--");
				logSys.setResult("成功");
			} else {
				LOG.info("--日志记录结果：操作失败--");
				logSys.setResult("失败");
			}
			logSysMapper.insertSelective(logSys);
		}
	}
}
