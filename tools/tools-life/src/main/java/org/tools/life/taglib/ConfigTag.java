package org.tools.life.taglib;


import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.beans.factory.annotation.Configurable;
import org.tools.life.support.CommonConstants;


/**
 * 图片资源格式化
 * @author 高金[OF706]
 * @date 2013-11-4 下午3:04:45
 *
 */
@Configurable
public class ConfigTag extends SimpleTagSupport { 
    
    private final static String KEY_PREFIX = "url"; 

    private String name;
    
    private String namespace = KEY_PREFIX;

    public void doTag() throws JspException, IOException {
        String url = CommonConstants.getProperty(namespace+"."+name);
        getJspContext().getOut().write(url);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    
}