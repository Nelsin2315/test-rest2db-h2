package com.example.testrest2dbh2.dao;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.testrest2dbh2.entity.Student;
import javax.management.Query;

import java.util.List;

@Slf4j
@Repository
public class StudentDAOImpl  implements StudentDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudents() {

        Query query = (Query) entityManager.createQuery("from Student");

        List<Student> allStudents = ((jakarta.persistence.Query) query).getResultList();
        log.info("getAllStudents" + allStudents);
        return allStudents;
    }

    @Override
    public Student saveStudent(Student student) {
        return entityManager.merge(student);

    }

    @Override
    public Student getStudent(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void deleteStudent(int id) {
        Query query = (Query) entityManager.createQuery("delete from Student "
                + " where id =:studentId");
        ((jakarta.persistence.Query) query).setParameter("studentId", id);
        ((jakarta.persistence.Query) query).executeUpdate();
    }

}
