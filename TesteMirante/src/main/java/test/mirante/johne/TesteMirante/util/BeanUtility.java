package test.mirante.johne.TesteMirante.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class BeanUtility implements ApplicationContextAware {

	@Autowired
	private static ApplicationContext applicationContext;

    public static Object getBean (String beanId) {
        return applicationContext.getBean(beanId);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
