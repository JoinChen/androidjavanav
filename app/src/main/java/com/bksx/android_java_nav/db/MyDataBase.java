package com.bksx.android_java_nav.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bksx.android_java_nav.dao.StudentDao;
import com.bksx.android_java_nav.entity.Student;

/**
 * @Author JoneChen
 * @Date 2020\7\29 0029-10:45
 * , exportSchema = false 不到出文件 例如数据库升级后的数据文件
 */
@Database(entities = {Student.class}, version = 1)
public abstract class MyDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "my_db";
    private static MyDataBase dataBaseInstance;

    private static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE student ADD COLUMN hobby TEXT");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE temp_student (" +
                    "id INTEGER PRIMARY KEY NOT NULL," +
                    "name TEXT,"+
                    "age TEXT," +
                    "hobby INTEGER NOT NULL DEFAULT 1)");
            database.execSQL("INSERT INTO temp_student(id,name,age)"+
                    "SELECT id,name,age FROM student");
            database.execSQL("DROP TABLE student");
            database.execSQL("ALTER TABLE temp_student RENAME TO student");
        }
    };

    public static synchronized MyDataBase getInstance(Context context) {
        if (dataBaseInstance == null) {
            dataBaseInstance = Room.databaseBuilder(context, MyDataBase.class, DATABASE_NAME)
                    .addMigrations(MIGRATION_1_2,MIGRATION_2_3)
                    .build();
            //基于本地已有的数据库文件创建数据库
//            dataBaseInstance = Room.databaseBuilder(context,MyDataBase.class,DATABASE_NAME)
//                    .createFromAsset("databases/students.db")
//                    .build();

        }
        return dataBaseInstance;
    }

    public abstract StudentDao studentDao();
}
