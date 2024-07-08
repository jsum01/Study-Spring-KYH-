package hello.hello_spring.domain;

public class Member {

    private Long id; // 임의의 값: 고객 이정하는 Id가 아니라 System이 정하는 id
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
