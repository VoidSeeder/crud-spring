package com.joaov.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.joaov.crudspring.dto.CourseDTO;
import com.joaov.crudspring.enums.Category;
import com.joaov.crudspring.model.Course;

@Component
public class CourseMapper {
  public CourseDTO toDTO(Course course) {
    if (course == null) {
      return null;
    }

    return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
  }

  public Course toEntity(CourseDTO courseDTO) {
    if (courseDTO == null) {
      return null;
    }

    Course course = new Course();

    if (courseDTO.id() != null) {
      course.setId(courseDTO.id());
    }
    course.setName(courseDTO.name());
    course.setCategory(this.convertCategoryValue(courseDTO.category()));

    return course;
  }

  public Category convertCategoryValue(String value) {
    if (value == null) {
      return null;
    }

    //TODO não gostei disso aqui, preciso generalizar
    return switch (value) {
      case "Front-end" -> Category.FRONT_END;
      case "Back-end" -> Category.BACK_END;
      default -> throw new IllegalArgumentException("Categoria inválida: " + value);
    };
  }
}
