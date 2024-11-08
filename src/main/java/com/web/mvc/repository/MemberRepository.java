package com.web.mvc.repository;

import com.web.mvc.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("select m from Member m where m.id=?1")
    Member duplicateCheck(String id);

    /*
    existsById 나 findById 는 이미 기본 제공 함수임. 사실 밑에 코드 없어도 된다
     */
    Boolean existsById(String id);

    Member findById(String id);
}
