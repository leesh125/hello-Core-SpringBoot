package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // @Bean memberService() -> new MemoryMemberRepository()
    // @Bean orderService() -> new MemoryMemberRepository()

    /*
        개발자의 가시적인 호출 순서
          1. call AppConfig.memberService
          2. call AppConfig.memberRepository
          3. call AppConfig.memberRepository
          4. call AppConfig.orderService
          5. call AppConfig.memberRepository

        스프링의 호출 순서(싱글톤 보장)
          1. call AppConfig.memberService
          2. call AppConfig.memberRepository
          3. call AppConfig.orderService
     */


    @Bean // 스프링 컨테이너에 등록이 됨
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy(); // 정액 할인
        return new RateDiscountPolicy();  // 정률 할인
    }

}
