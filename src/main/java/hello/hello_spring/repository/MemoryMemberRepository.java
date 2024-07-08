package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member memeber) {
        memeber.setId(++sequence);
        store.put(memeber.getId(), memeber);
        return memeber;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 결과가 없으면 null이기 때문에 Optional로 감쌈
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // 같은 경우에만 반환
                .findAny(); // 하나를 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        // 자바에서 실무할 때는 루프 돌리기가 편한 List를 많이 사용한다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
