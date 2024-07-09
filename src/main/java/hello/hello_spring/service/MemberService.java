package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();


    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 비즈니스 룰: 같은 이름이 있는 중복 회원 X
        validateDulicateMember(member); // 메서드화 단축키: Command + Option + M
        // 기존에는 If(null이 아니면) 이런 식으로 했겠지만, 지금은 null일 가능성이 있으면 Optional로 감싼다.
        // result.get보다는 result.orElseGet과 같은 메서드를 사용한다.
        /*
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDulicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }



    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
