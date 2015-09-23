package br.com.cast.turmaformacao.taskmanager.model.persistence;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import br.com.cast.turmaformacao.taskmanager.model.entities.User;

public class UserRepository {

    private UserRepository()
    {
        super();
    }

    public static void save(User user) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = UserContract.getContentValues(user);

        if (user.getId() == null) {

            db.insert(UserContract.TABLE, null, values);

        } else {

            String where = UserContract.ID + " = ? ";
            String[] params = {user.getId().toString()};
            db.update(UserContract.TABLE, values, where, params);
        }

        db.close();
        dataBaseHelper.close();
    }

    public static void delete(long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

        String where = UserContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};

        db.delete(UserContract.TABLE, where, params);

        db.close();
        dataBaseHelper.close();
    }

    public static User checkLogin(User user){
        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = UserContract.USER + " = ? and " + UserContract.PASSWORD + " = ?";
        String[] params = {user.getUser(),user.getPassword()};

        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUNS, where, params, null, null, UserContract.ID);

        user = UserContract.getUser(cursor);

        db.close();
        dataBaseHelper.close();

        return user;
    }


    public static List<User> getAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUNS, null, null, null, null, UserContract.ID);
        List<User> values = UserContract.getUsers(cursor);

        db.close();
        dataBaseHelper.close();

        return values;
    }

}
