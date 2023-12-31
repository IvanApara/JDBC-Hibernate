package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() {
        try(PreparedStatement preparedStatement =
                    Util.getConnection().prepareStatement
                            ("CREATE TABLE users(id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, " +
                                    " name varchar NOT NULL , lastname varchar NOT NULL , age int NOT NULL )")) {


            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (PreparedStatement preparedStatement =
                     Util.getConnection().prepareStatement("DROP TABLE users")){


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement preparedStatement =
                    Util.getConnection().prepareStatement("INSERT INTO users(name, lastname, age) VALUES (?, ?, ?)")) {


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3,age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement =
                Util.getConnection().prepareStatement("DELETE FROM users WHERE id=?");){

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = Util.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {


            while (resultSet.next()){
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }
        } catch (SQLException e){
            e.getStackTrace();
        }


        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement =
                     Util.getConnection().prepareStatement("DELETE FROM users")){

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
