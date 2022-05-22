# matchNow
## Spec
Language: Java<br>
Framework: Spring boot<br>
Database: MySQL using JPA(hibernate)<br>
## ER diagram
![스크린샷 2022-05-11 오후 10 53 28](https://user-images.githubusercontent.com/58351498/167867695-3a52ba5d-d986-4302-a383-74c7d0258d0e.png)
## 트러블 슈팅
- 양방향 Entity 무한 재귀 현상
해당 문제는 `@JsonBackReference` 어노테이션을 사용해 해결하였다. 이 어노테이션은 직렬화에서 User 객체를 제외시켜 응답으로 보내주는 역할을 한다. 따라서 유저의 skillStackList 내부에서 또 다시 User 정보가 담겨있지 않게 되었다!
```java
public class User{
    @OneToMany(mappedBy = "user") // user가 가진 스킬 스택
    private List<SkillStack> skillStackList = new ArrayList<>();
}

public class SkillStack{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_skill_id")
    @JsonBackReference
    private User user; //user
}
```
