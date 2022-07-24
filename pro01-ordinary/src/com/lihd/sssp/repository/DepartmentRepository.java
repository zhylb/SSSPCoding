package com.lihd.sssp.repository;

import com.lihd.sssp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    @QueryHints({@QueryHint(name=org.hibernate.ejb.QueryHints.HINT_CACHEABLE,value="true")})
    @Query("from Department d")
    List<Department> getAll();
}
