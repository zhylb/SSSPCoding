package com.lihd.sssp.test;

import org.hibernate.annotations.QueryHints;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.QueryHint;
import javax.sql.DataSource;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/28 21:48
 */
public class StartTest {
    private ClassPathXmlApplicationContext context;
    private EntityManagerFactory entityManagerFactory;
    {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        entityManagerFactory = context.getBean(EntityManagerFactory.class);

    }

    @Test
    public void getDataSource(){
        DataSource bean = context.getBean(DataSource.class);
        System.out.println(bean);
    }

    @Test
    public void testSecondCache(){

        String jpql = "from Department d";

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(jpql);
        for (Object o : query.setHint(QueryHints.CACHEABLE, true).getResultList()) {
            System.out.println(o);
        }
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        query = entityManager.createQuery(jpql);
        for (Object o : query.setHint(QueryHints.CACHEABLE, true).getResultList()) {
            System.out.println(o);
        }
        entityManager.close();


    }
}
