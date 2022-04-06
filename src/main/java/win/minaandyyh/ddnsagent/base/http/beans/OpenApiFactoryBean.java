package win.minaandyyh.ddnsagent.base.http.beans;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import win.minaandyyh.ddnsagent.base.http.interfaces.RequestClient;
import win.minaandyyh.ddnsagent.base.http.proxy.OpenApiProxyHandler;

import java.lang.reflect.Proxy;

/**
 * @author 22454
 */
public class OpenApiFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {
    private final Class<T> beanClass;
    private RequestClient client;
    private ApplicationContext applicationContext;

    public OpenApiFactoryBean(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public T getObject() {
        client = applicationContext.getBean(RequestClient.class);
        return (T) Proxy.newProxyInstance(
                OpenApiFactoryBean.class.getClassLoader(),
                new Class[]{beanClass},
                new OpenApiProxyHandler(client));
    }

    public RequestClient getClient() {
        return client;
    }

    public void setClient(RequestClient client) {
        this.client = client;
    }

    @Override
    public Class<T> getObjectType() {
        return beanClass;
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
