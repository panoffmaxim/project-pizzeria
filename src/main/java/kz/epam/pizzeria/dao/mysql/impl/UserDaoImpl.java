package kz.epam.pizzeria.dao.mysql.impl;

import kz.epam.pizzeria.dao.mysql.AbstractBaseDao;
import kz.epam.pizzeria.entity.enums.Role;
import kz.epam.pizzeria.entity.db.impl.User;

import java.sql.*;

public class UserDaoImpl extends AbstractBaseDao<Integer, User> {
    private final DaoHelper daoHelper = DaoHelper.getInstance();
    public static final String FIND_ALL_SQL = "SELECT id, creation, name, password, phone, role, surname, username, email, " +
            "floor, house, porch, room, street, is_blocked FROM user ORDER BY id;";
    private static final String findAllByPart = "SELECT id, creation, name, password, phone, role, surname, username, email, " +
            "floor, house, porch, room, street, is_blocked FROM user ORDER BY id LIMIT ? OFFSET ?;";
    public static final String FIND_ENTITY_BY_ID_SQL = "SELECT id, creation, name, password, phone, role, surname, username, email, " +
            "floor, house, porch, room, street, is_blocked FROM user WHERE id = ?";
    public static final String DELETE_BY_ID_SQL = "DELETE FROM user WHERE id = ?";
    public static final String CREATE_SQL = "INSERT INTO user (creation, name, password, phone, role, surname, username, email, " +
            "floor, house, porch, room, street, is_blocked) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_SQL = "UPDATE user SET  creation = ?, name = ?, password = ?, phone = ?, role = ?, surname = ?, " +
            "username = ?, email = ?, floor = ?, house = ?, porch = ?, room = ?, street = ?, is_blocked = ? WHERE id = ?;";
    private static final String countSql = "SELECT count(id) FROM user;";

    public UserDaoImpl() {
        super(FIND_ALL_SQL, FIND_ENTITY_BY_ID_SQL, DELETE_BY_ID_SQL, CREATE_SQL, UPDATE_SQL, findAllByPart, countSql);
    }

    protected User findEntity(ResultSet resultSet) throws SQLException {
        return User.newBuilder()
                .id(resultSet.getInt("id"))
                .creation(resultSet.getTimestamp("creation").toLocalDateTime())
                .name(resultSet.getString("name"))
                .password(resultSet.getString("password"))
                .phone(resultSet.getString("phone"))
                .role(Role.values()[resultSet.getInt("role")])
                .surname(resultSet.getString("surname"))
                .username(resultSet.getString("username"))
                .email(resultSet.getString("email"))
                .floor(daoHelper.getIntOrNull(resultSet, "floor"))
                .house(resultSet.getString("house"))
                .porch(daoHelper.getIntOrNull(resultSet, "porch"))
                .room(resultSet.getString("room"))
                .street(resultSet.getString("street"))
                .isBlocked(resultSet.getBoolean("is_blocked"))
                .build();
    }

    @Override
    protected void idParam(PreparedStatement statement, Integer integer) throws SQLException {
        statement.setInt(1, integer);
    }

    @Override
    protected Integer getIdFromGeneratedKeys(ResultSet generatedKeys) throws SQLException {
        return generatedKeys.getInt(1);
    }

    protected void createParams(User entity, PreparedStatement statement) throws SQLException {
        statement.setTimestamp(1, Timestamp.valueOf(entity.getCreation()));
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getPassword());
        statement.setString(4, entity.getPhone());
        statement.setInt(5, entity.getRole().ordinal());
        statement.setString(6, entity.getSurname());
        statement.setString(7, entity.getUsername());
        statement.setString(8, entity.getEmail());
        daoHelper.setIntOrNull(statement, entity.getFloor(), 9);
        statement.setString(10, entity.getHouse());
        daoHelper.setIntOrNull(statement, entity.getPorch(), 11);
        statement.setString(12, entity.getRoom());
        statement.setString(13, entity.getStreet());
        statement.setBoolean(14, entity.isBlocked());
    }

    protected void updateParams(User entity, PreparedStatement statement) throws SQLException {
        statement.setTimestamp(1, Timestamp.valueOf(entity.getCreation()));
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getPassword());
        statement.setString(4, entity.getPhone());
        statement.setInt(5, entity.getRole().ordinal());
        statement.setString(6, entity.getSurname());
        statement.setString(7, entity.getUsername());
        statement.setString(8, entity.getEmail());
        daoHelper.setIntOrNull(statement, entity.getFloor(), 9);
        statement.setString(10, entity.getHouse());
        daoHelper.setIntOrNull(statement, entity.getPorch(), 11);
        statement.setString(12, entity.getRoom());
        statement.setString(13, entity.getStreet());
        statement.setBoolean(14, entity.isBlocked());
        statement.setInt(15, entity.getId());
    }
}