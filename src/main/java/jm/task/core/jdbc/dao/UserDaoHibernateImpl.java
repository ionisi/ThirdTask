package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import jm.task.core.jdbc.util.Util;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Function;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    private <R> R executeTransaction(Function<Session, R> function) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                R result = function.apply(session);
                transaction.commit();
                return result;
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS users " +
                "(id SERIAL PRIMARY KEY, " +
                " name VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age TINYINT)";
        executeTransaction(session -> {
            session.createSQLQuery(sqlCreate).executeUpdate();
            return null;
        });
    }

    @Override
    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE IF EXISTS users";
        executeTransaction(session -> {
            session.createSQLQuery(sqlDrop).executeUpdate();
            return null;
        });
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        executeTransaction(session -> {
            User user = new User(name, lastName, age);
            session.save(user);
            return null;
        });
    }

    @Override
    public void removeUserById(long id) {
        executeTransaction(session -> {
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            return null;
        });
    }

    @Override
    public List<User> getAllUsers() {
        return executeTransaction(session -> {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.list();
        });
    }

    @Override
    public void cleanUsersTable() {
        String sqlClean = "TRUNCATE TABLE users";
        executeTransaction(session -> {
            session.createSQLQuery(sqlClean).executeUpdate();
            return null;
        });
    }
}
