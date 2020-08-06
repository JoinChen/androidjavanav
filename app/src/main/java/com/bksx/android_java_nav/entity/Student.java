package com.bksx.android_java_nav.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @Author JoneChen
 * @Date 2020\7\29 0029-10:28
 */
@Entity(tableName = "student")//不重新命名情况下默认使用类名为数据库名称
public class Student {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id",typeAffinity = ColumnInfo.INTEGER)
    public int id;

    @ColumnInfo(name = "age",typeAffinity = ColumnInfo.TEXT)
    public String age;

    @ColumnInfo(name = "name",typeAffinity = ColumnInfo.TEXT)
    public String name;

    @ColumnInfo(name = "hobby",typeAffinity = ColumnInfo.INTEGER)
    public int hobby;

    public Student(int id, String age, String name, int hobby) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.hobby = hobby;
    }

    @Ignore
    public Student(int id, String age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Ignore
    public Student(int id, String age) {
        this.id = id;
        this.age = age;
    }

    @Ignore
    public Student(int id){
        this.id = id;
    }
}
