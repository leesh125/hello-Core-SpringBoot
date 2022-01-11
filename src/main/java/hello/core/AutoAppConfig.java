package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Component 애노테이션이 붙은 클래스를 찾아서 자동으로 스프링 빈 등록
@ComponentScan(
        basePackages = "hello.core.member", // 탐색할 패키지 시작 위치 지정(지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치)
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 스캔에서 제외할 것
)
public class AutoAppConfig {

   /* @Bean(name = "memoryMemberRepository") // 수동 빈 등록 vs 자동 빈 등록 (빈 이름 중복), 수동 빈 등록이 우선권(수동 빈이 자동 빈을 오버라이딩)
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}
