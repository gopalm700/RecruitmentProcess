package com.heavenhr;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.heavenhr.factory.NotificationFactory;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
@EnableAutoConfiguration
public class RecruitmentApplication {

  public static void main(String... args) {
    SpringApplication.run(RecruitmentApplication.class, args);
  }

  @Bean("notificationFactory")
  public ServiceLocatorFactoryBean notificationFactory() {
    ServiceLocatorFactoryBean slfb = new ServiceLocatorFactoryBean();
    slfb.setServiceLocatorInterface(NotificationFactory.class);
    return slfb;
  }

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
        .paths(PathSelectors.any()).build().apiInfo(metaData());
  }

  private ApiInfo metaData() {
    return new ApiInfo("HeavenHr Application", "Simple recruitment application",
        "1.0", "Terms of service", new Contact("Gopal Mondal",
            "https://www.linkedin.com/in/gopalm700/", "gopalm700@gmail.com"),
        "Public license", "Public");
  }

}
