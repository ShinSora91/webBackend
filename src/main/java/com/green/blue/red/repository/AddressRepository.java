package com.green.blue.red.repository;

import com.green.blue.red.domain.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @EntityGraph(attributePaths =  "addImageList")
    @Query("select a from Address a where a.ano = :ano")
    Optional<Address> selectOne(@Param("ano") Long ano);

    //전체조회
    @Query("select a, ai from Address a left join a.addImageList ai")
    Page<Object[]> getList(Pageable pageable);
}
