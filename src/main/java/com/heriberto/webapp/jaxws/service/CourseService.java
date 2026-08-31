package com.heriberto.webapp.jaxws.service;


import com.heriberto.webapp.jaxws.models.Course;
import jakarta.ejb.Local;
import jakarta.jws.WebService;

import java.util.List;
import java.util.Optional;

@Local
public interface CourseService {

    List<Course> listAll();

    Optional<Course> byId(Long id);

    Course save(Course course);

    void deleteById(Long id);
}
