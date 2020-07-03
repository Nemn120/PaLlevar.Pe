package com.paLlevar.app.model.repository.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.paLlevar.app.security.UserPrincipal;
import com.paLlevar.app.util.DateUtil;

import org.apache.commons.beanutils.PropertyUtils;


@Aspect
@Component
public class RepositoryAudiTracking {
	
	private static final Logger log = LogManager.getLogger();
	
	@Pointcut("execution(* com.paLLevar.app.model.repository..*.save*(java.lang.Object+,..))" + "&& args(entity,..)")
	public void saveMethodExecution(Object entity) {
	}
	@Around(value="saveMethodExecution(entity)")
	public Object aroundSaveMethodExecution(ProceedingJoinPoint joinPoint, Object entity) throws Throwable {
		Authentication principal = SecurityContextHolder.getContext().getAuthentication();
		Object ob = null;
		try {
			if(PropertyUtils.getProperty(entity, "createDate") == null) {
				PropertyUtils.setProperty(entity, "createDate",DateUtil.getTime());
				PropertyUtils.setProperty(entity, "deleted",false);
				// .. active status
			}
			if(PropertyUtils.getProperty(entity, "organizationId") == null) {
				PropertyUtils.setProperty(entity, "organizationId",((UserPrincipal)principal).getOrganizationId());
			
			}
			if(PropertyUtils.getProperty(entity, "sucursalId") == null) {
				PropertyUtils.setProperty(entity, "sucursalId",((UserPrincipal)principal).getSucursalId());
		
			}
			
			if(PropertyUtils.getProperty(entity, "userCreateId") == null && !"anonymousUser".equals(principal)) {
				PropertyUtils.setProperty(entity, "userCreateId",((UserPrincipal)principal).getId());
			}
			
			
			ob=joinPoint.proceed();
		}catch(Exception e) {
			log.error(e.getMessage());
		}
		return ob;
	}
	

}
