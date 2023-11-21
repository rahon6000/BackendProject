package rahon.example.demo;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public record Actor(    
    @Id
    @Column("actor_id")
    Long actorId,

    @Column("first_name")
    String firstName,

    @Column("last_name")
    String lastName,
    
    // 그냥 java.time.Date 쓰면 에러난다.
    // 추가로 MySql 의 `system_time_zone` 이 이상하게 인코딩 되는 문제도 있는데, 이건 해결 못함.
    // 대신 `time_zone = SYSTEM` 을 override 해서 문제를 우회 해결함.
    
    @Column("last_update")
    ZonedDateTime lastUpdate
) {}
