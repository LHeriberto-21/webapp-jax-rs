package com.heriberto.webapp.jaxws.repo;

import com.heriberto.webapp.jaxws.models.Course;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RequestScoped
public class CourseRepositoryImpl implements CourseRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Course> findAll() {
        return em.createQuery("select c from Course c left outer join fetch c.instructor", Course.class).getResultList();
    }

    @Override
    public Course byId(Long id) {
        return em.createQuery("select c from Course c left outer join fetch c.instructor where c.id=:id", Course.class)
                .setParameter("id", id)
                .getSingleResult();

    }

    @Override
    public Course save(Course course) {
        if (course.getId() != null && course.getId() > 0) {
            em.merge(course);
        } else {
            em.persist(course);
        }
        return course;
    }

    @Override
    public void delete(Long id) {
        Course course = byId(id);
        em.remove(course);
    }
}
