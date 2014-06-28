package org.tools.life.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.tools.life.service.common.SafeService;

public class SafeTage extends SimpleTagSupport {
	@Autowired
	private SafeService safeService;
	
	private String method;
	
	private String data;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public void doTag() throws JspException, IOException {
		if("encode".equals(method)){
			try {
				getJspContext().getOut().write(safeService.encodeToHexString(data));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("decode".equals(method)){
			try {
				getJspContext().getOut().write(safeService.decode(data));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
