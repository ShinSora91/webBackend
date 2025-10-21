package com.green.blue.red.repository;

import com.green.blue.red.domain.CartItem;
import com.green.blue.red.dto.CartItemListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // 1) 이메일로 카트 아이템 조회 (대표이미지 ord=0, 이미지 없으면 null 허용)
    @Query("""
        select new com.green.blue.red.dto.CartItemListDTO(
            ci.cino, ci.qty, p.pno, p.pname, p.price, pi.fileName
        )
        from CartItem ci
          join ci.cart mc
          join ci.product p
          left join p.imageList pi
        where mc.owner.email = :email
          and (pi is null or pi.ord = 0)
        order by ci.cino desc
    """)
    List<CartItemListDTO> getItemsOfCartDTOByEmail(@Param("email") String email);

    // 2) 사용자의 카트에 특정 상품이 있는지 조회
    @Query("""
        select ci
        from CartItem ci
          join ci.cart c
        where c.owner.email = :email
          and ci.product.pno = :pno
    """)
    CartItem getItemOfPno(@Param("email") String email, @Param("pno") Long pno);

    // 3) 아이템이 속한 카트 번호
    @Query("""
        select c.cno
        from CartItem ci
          join ci.cart c
        where ci.cino = :cino
    """)
    Long getCartFromItem(@Param("cino") Long cino);

    // 4) 카트 번호로 아이템 조회 (대표이미지 ord=0, 이미지 없으면 null 허용)
    @Query("""
        select new com.green.blue.red.dto.CartItemListDTO(
            ci.cino, ci.qty, p.pno, p.pname, p.price, pi.fileName
        )
        from CartItem ci
          join ci.cart mc
          join ci.product p
          left join p.imageList pi
        where mc.cno = :cno
          and (pi is null or pi.ord = 0)
        order by ci.cino desc
    """)
    List<CartItemListDTO> getItemsOfCartDTOByCart(@Param("cno") Long cno);
}
