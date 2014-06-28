package org.tools.life.service.common;


public interface SafeService {
	
	String encodeToHexString(String data) throws Exception;
	
	String decode(String enHexStr) throws Exception;
}
