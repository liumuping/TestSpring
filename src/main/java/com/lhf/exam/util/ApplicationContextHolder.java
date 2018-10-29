package com.lhf.exam.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author admin 这是对SpringIOC容器的持有类
 */
@Component
@Slf4j
public class ApplicationContextHolder implements ApplicationContextAware {

	public static ApplicationContext context = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		ApplicationContextHolder.context = applicationContext;
		log.info("############################Set applicationContext############################");
	}

}
