package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // componentScan에 의해 component로 등록
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // ComponentScan시 의존관계 자동 주입
    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도(config의 두 번 호출? 의 테스트)
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
