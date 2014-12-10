package com.life.jfx.pojo.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class BaseInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4539037118246917640L;

	@Override
	public String toString() {
		try {
		Map<String,Object> properties = PropertyUtils.describe(this);
		properties.remove("class");
		return new StringBuffer(this.getClass().getSimpleName()).append(properties.toString().replaceAll("\\{", " [").replaceAll("\\}", "]")).toString();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

}
