package ro.sdi.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ro.sdi.core.config.JPAConfig;
import ro.sdi.core.config.ServiceConfig;


@Configuration
@Import({JPAConfig.class, ServiceConfig.class})
public class CoreConfig
{
    /*@Bean
    RmiServiceExporter controllerExporter(Service service)
    {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("Service");
        rmiServiceExporter.setServiceInterface(Service.class);
        rmiServiceExporter.setService(service);
        rmiServiceExporter.setRegistryPort(ServerInformation.PORT);
        return rmiServiceExporter;
    }*/

    /*@Bean
    RmiProxyFactoryBean rmiServiceProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(Service.class);
        String url = String.format("rmi://localhost:%d/Service", ServerInformation.PORT);
        rmiProxyFactoryBean.setServiceUrl(url);
        return rmiProxyFactoryBean;
    }*/
}
