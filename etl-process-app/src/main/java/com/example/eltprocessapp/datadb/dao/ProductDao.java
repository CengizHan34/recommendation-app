package com.example.eltprocessapp.datadb.dao;

import com.example.eltprocessapp.dto.CategoryProductSaleDto;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author created by cengizhan on 7.07.2021
 */
@Repository
@Transactional
public class ProductDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<CategoryProductSaleDto> getCategoryProductSaleDtoList() {
        Session session = entityManager.unwrap(Session.class);
        return session
                .createSQLQuery("select category_id as category_id, product_id as product_id, count(*) as distinct_user_count " +
                        "from (select  p.category_id ,oi.product_id, o.user_id  from order_items oi " +
                        "inner join orders o    on o.order_id = oi.order_id " +
                        "inner join products p  on p.product_id  = oi.product_id  " +
                        "group by p.category_id , oi.product_id, o.user_id ) a " +
                        "group by category_id, product_id " + "order by distinct_user_count desc;"
                ).addScalar("category_id", new StringType())
                .addScalar("product_id", new StringType())
                .addScalar("distinct_user_count", new IntegerType())
                .setResultTransformer(Transformers.aliasToBean(CategoryProductSaleDto.class)).list();
    }
}
