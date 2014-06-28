package org.tools.life.support;


import java.util.Properties;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 这里实际上是引用了所有的配置，名字改成loadConfigProperties
 * 
 * @author 彭文哲[OF789]
 * @date 2013-10-16 下午2:12:40
 *
 */
public class CommonPropertyConfigurer extends PropertyPlaceholderConfigurer {

    @Override
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactory, Properties props) {
        super.processProperties(beanFactory, props);
        CommonConstants.loadConfigProperties(props);
    }

}
