package com.heriberto.webapp.jaxws.repo;

import com.heriberto.webapp.jaxws.models.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> findAll();
    Course byId(Long id);
    Course save(Course course);
    void delete(Long id);
}
