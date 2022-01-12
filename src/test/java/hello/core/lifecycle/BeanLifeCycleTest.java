package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class); // 객체 생성 -> 의존관계 주입(스프링 빈 라이프사이클)
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        // @Bean(initMethod = "init", destroyMethod = "close")
        @Bean(initMethod = "init") // 위 코드 처럼 destroyMethod를 지정하지 않아도 close 메소드가 동작함(스프링이 close,shutdown 같은 이름이 붙은 함수를 알아서 추론함)
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); // 객체를 먼저 생성(생성자 내부에 있는 메소드 등)
            networkClient.setUrl("http://hello-spring.dev"); // 객체를 먼저 생성한 다음 url을 주입했기에 내부(생성자)에서 실행한 것들은 null값이 나옴
            return networkClient;
        }
    }
}
