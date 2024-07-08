package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 회원객체를 저장하는 Repository
    Member save(Member memeber); // 멤버를 저장

    Optional<Member> findById(Long id); // 해당하는 아이디의 멤버를 반환

    Optional<Member> findByName(String name); // 해당하는 이름의 멤버를 반환

    List<Member> findAll(); // 저장된 모든 회원 리스트 반환
}
