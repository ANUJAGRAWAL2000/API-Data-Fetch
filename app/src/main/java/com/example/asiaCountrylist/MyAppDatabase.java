package com.example.asiaCountrylist;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Country.class} , version = 4,exportSchema = false)
public abstract class MyAppDatabase extends RoomDatabase
{
    public abstract MyDao myDao();

    static final Migration MIGRATION_2_4 = new Migration(2, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };
}
