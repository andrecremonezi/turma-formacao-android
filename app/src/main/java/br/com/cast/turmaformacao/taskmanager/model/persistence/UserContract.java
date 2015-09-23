package br.com.cast.turmaformacao.taskmanager.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.taskmanager.model.entities.Task;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;

/**
 * Created by Administrador on 21/09/2015.
 */
public class UserContract {

    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String USER = "user";
    public static final String PASSWORD = "password";

    public static final String[] COLUNS = {ID, USER, PASSWORD};

    private UserContract(){
        super();
    }

    public static String getCreateTableScript(){
        final StringBuilder create = new StringBuilder();
        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(USER + " TEXT NOT NULL, ");
        create.append(PASSWORD + " TEXT NOT NULL ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();

        values.put(UserContract.ID, user.getId());
        values.put(UserContract.USER, user.getUser());
        values.put(UserContract.PASSWORD, user.getPassword());

        return values;
    }

    public static User getUser(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getLong(cursor.getColumnIndex(UserContract.ID)));
            user.setUser(cursor.getString(cursor.getColumnIndex(UserContract.USER)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(UserContract.PASSWORD)));

            return user;
        }

        return null;

    }

    public static List<User> getUsers(Cursor cursor) {
        User user = new User();

        List<User> users = new ArrayList<>();

        while (cursor.moveToNext()) {
            users.add(getUser(cursor));
        }
        return users;
    }



}
