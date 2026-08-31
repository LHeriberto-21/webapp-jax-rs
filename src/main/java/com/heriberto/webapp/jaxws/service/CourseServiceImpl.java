package com.heriberto.webapp.jaxws.service;

import com.heriberto.webapp.jaxws.models.Course;
import com.heriberto.webapp.jaxws.repo.CourseRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Stateless
public class CourseServiceImpl implements CourseService {

    @Inject
    private CourseRepository courseRepository;

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> byId(Long id) {
        return Optional.ofNullable(courseRepository.byId(id));
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }


    @Override
    public void deleteById(Long id) {
        try {
            courseRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
