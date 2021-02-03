package kr.or.ddit.ioc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:/kr/or/ddit/config/spring/datasource-context.xml")
@ComponentScan(basePackages = {"kr.or.ddit"})
//@Configuration이 붙은 빈 설정 자바클래스에 붙여서 스테레오 타입 어노테이션이 붙은 빈들을 자동으로 스캔해서 등록해준다
@Configuration
public class ComponentScanJavaConfig {

}
