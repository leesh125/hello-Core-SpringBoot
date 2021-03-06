package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor // final이 붙은(필수 값) 필드를 가지고 생성자 주입 코드를 똑같이 만들어줌
public class OrderServiceImpl implements OrderService{

    // final을 통해 생성자에서 값이 설정되지 않을 경우 오류 발생
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
        의존 관계 주입 방법
        1. 생성자 주입
        2. 수정자 주입(setter 주입)
        3. 필드 주입
        4. 일반 메서드 주입
     */

    // 1. 생성자 주입 - lombok에 의해 자동 생성
    @Autowired // 최근에는 생성자를 딱 1개 두고, @Autowired를 생략하는 방법을 사용
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;               // qualifire로 등록한 것 중 원하는 것 이름 넣어주면 그것이 생성자로 들어감
        this.discountPolicy = discountPolicy;
        // discountPolicy의 하위 구현체 2개가 동시에 스프링 빈으로 등록된 경우
        // 필드명 또는 파라미터 이름으로 빈 이름을 매칭(rateDiscountPolicy)
    }


    /* 2. 빈 두개 이상 등록 해결(@Qualifier로 따로 이름 지정)
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;               // qualifire로 등록한 것 중 원하는 것 이름 넣어주면 그것이 생성자로 들어감
//        this.discountPolicy = discountPolicy;
//        // discountPolicy의 하위 구현체 2개가 동시에 스프링 빈으로 등록된 경우
//        // 필드명 또는 파라미터 이름으로 빈 이름을 매칭(rateDiscountPolicy)
//    }

    /* 1. 빈 두개 이상 등록 해결(필드명, 파라미터로 매칭)
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
        // discountPolicy의 하위 구현체 2개가 동시에 스프링 빈으로 등록된 경우
        // 필드명 또는 파라미터 이름으로 빈 이름을 매칭(rateDiscountPolicy)
    }*/

    /* 2. 수정자 주입(setter 주입)  // 외부에서 수정이 가능하기에 비추
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

       3. 필드 주입  // 외부에서 변경이 불가능해서 비추
         @Autowired private MemberRepository memberRepository;
         @Autowired private DiscountPolicy discountPolicy;

       4. 일반 메서드 주입
       @Autowired
       public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
            this.memberRepository = memberRepository;
            this.discountPolicy = discountPolicy;
       }
    */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
