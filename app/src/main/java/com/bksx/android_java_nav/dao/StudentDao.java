package com.bksx.android_java_nav.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bksx.android_java_nav.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author JoneChen
 * @Date 2020\7\29 0029-10:38
 */
@Dao
public interface StudentDao {
    @Insert
    void insertStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Update
    void updateStudent(Student student);

    @Query("SELECT * FROM student")
    LiveData<List<Student>> getStudentList();

    @Query("SELECT * FROM student WHERE id=:id")
    Student getStudentById(int id);
}
