package com.heriberto.webapp.jaxws.controllers;

import com.heriberto.webapp.jaxws.models.Course;
import com.heriberto.webapp.jaxws.service.CourseService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

import static jakarta.ws.rs.core.MediaType.*;

@RequestScoped
@Path("/courses")
@Produces(APPLICATION_JSON)
public class CourseController {

    @Inject
    private CourseService courseService;

    @GET
    public List<Course> findAll() {
        return courseService.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces({APPLICATION_JSON})
    public Response byId(@PathParam("id") Long id) {
        Optional<Course> course = courseService.byId(id);
        if (course.isPresent()) {
            return Response.ok(courseService.byId(id).orElseThrow()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response create(Course course) {
        try {
            Course newC = courseService.save(course);
            return Response.ok(newC).build();
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(APPLICATION_JSON)
    public Response edit(@PathParam("id") Long id, Course course) {
        Optional<Course> courseOptional = courseService.byId(id);
        if (courseOptional.isPresent()) {
            Course c = courseOptional.get();
            c.setName(course.getName());
            c.setDescription(course.getDescription());
            c.setDuration(course.getDuration());
            c.setInstructor(course.getInstructor());

            try {
                courseService.save(c);
                return Response.ok(c).build();
            } catch (Exception e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Optional<Course> course = courseService.byId(id);
        if (course.isPresent()) {
            try {
                courseService.deleteById(course.get().getId());
                return Response.noContent().build();
            } catch (Exception e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
                return Response.serverError().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
