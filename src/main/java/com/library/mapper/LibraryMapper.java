package com.library.mapper;

import com.library.entity.Book;
import com.library.entity.Student;
import org.apache.ibatis.annotations.Insert;

public interface LibraryMapper {
    @Insert("insert into student(name, sex, grade) values (#{name}, #{sex}, #{grade})")
    int addStudent(Student student);

    @Insert("insert into book(title, description, price) values (#{title}, #{description}, #{price})")
    int addBook(Book book);
}
