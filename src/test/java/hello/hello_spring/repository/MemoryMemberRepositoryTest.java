package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    // 테스트는 개별적으로 동작해야 한다. (테스트 끼리 의존관계가 없어야 한다)
    // 때문에 테스트가 끝날 때마다 레포지토리를 깔끔하게 지워주는 코드를 넣어줘야 하낟.

    @AfterEach // 테스트가 끝날 때마다 실행하는 어노테이션
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // Optional에서 반환값은 get으로 꺼낼 수 있다.
        // 검증
        // System.out.println("result = " + (result == member));
        // 글자로 계속 보기가 힘드니까 생긴 기능이 있다 => assertions
        // 방법1. Assertions.assertEquals(result, member);
        // 방법2. 영어로 바로 읽힐 수 있도록 클린 코드
        assertThat(member).isEqualTo(result); // Assertions는 Option + Enter를 할 시에 static import하여 작성하지 않아도 사용할 수 있게된다.
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }


    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2W");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
