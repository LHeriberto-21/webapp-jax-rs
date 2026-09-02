package com.heriberto.webapp.jaxws.service;

import com.heriberto.webapp.jaxws.models.Course;
import com.heriberto.webapp.jaxws.repo.CourseRepository;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"USER", "ADMIN"})
public class CourseServiceImpl implements CourseService {

    @Inject
    private CourseRepository courseRepository;

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public Optional<Course> byId(Long id) {
        return Optional.ofNullable(courseRepository.byId(id));
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void deleteById(Long id) {
        try {
            courseRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
