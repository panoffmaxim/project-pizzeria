package kz.epam.pizzeria.dao.mysql.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.dao.exception.NullParamDaoException;
import kz.epam.pizzeria.dao.mysql.AbstractBaseDao;
import kz.epam.pizzeria.dao.mysql.Transaction;
import kz.epam.pizzeria.entity.enums.ProductType;
import kz.epam.pizzeria.entity.db.impl.ProductGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductGroupMysqlDao extends AbstractBaseDao<Integer, ProductGroup> {
    private static final Logger LOGGER = LogManager.getLogger(ProductGroupMysqlDao.class);
    private static final String FIND_ALL_QUERY = "SELECT id, description, name, photo_name, type, disabled " +
            "FROM product_group ORDER BY id;";
    private static final String FIND_ALL_BY_PART_QUERY = "SELECT id, description, name, photo_name, type, disabled " +
            "FROM product_group ORDER BY id LIMIT ? OFFSET ?;";
    private static final String FIND_ENTITY_BY_ID_QUERY = "SELECT id, description, name, photo_name, type, disabled " +
            "FROM product_group WHERE id = ?;";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM product_group WHERE id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO product_group (description, name, photo_name, type, disabled) " +
            "VALUES (?,?,?,?,?);";
    private static final String UPDATE_QUERY = "UPDATE product_group SET  description = ?, name = ?, photo_name = ?, type = ?, disabled = ? " +
            "WHERE id = ?;";
    private static final String FIND_ALL_BY_PRODUCT_GROUP_NOT_DISABLED = "SELECT id, description, name, photo_name, type, disabled " +
            "FROM product_group WHERE type = ? and disabled = ?;";
    private static final String COUNT_QUERY = "SELECT count(id) FROM product_group;";

    public ProductGroupMysqlDao() {
        super(FIND_ALL_QUERY, FIND_ENTITY_BY_ID_QUERY, DELETE_BY_ID_QUERY, CREATE_QUERY, UPDATE_QUERY, FIND_ALL_BY_PART_QUERY, COUNT_QUERY);
    }

    @Override
    protected ProductGroup findEntity(ResultSet resultSet) throws SQLException {
        return ProductGroup.newBuilder()
                .id(resultSet.getInt("id"))
                .description(resultSet.getString("description"))
                .name(resultSet.getString("name"))
                .photoName(resultSet.getString("photo_name"))
                .type(ProductType.values()[resultSet.getInt("type")])
                .disabled(resultSet.getBoolean("disabled"))
                .build();
    }

    @Override
    protected void idParam(PreparedStatement statement, Integer integer) throws SQLException {
        statement.setInt(1, integer);
    }

    @Override
    protected void createParams(ProductGroup entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getDescription());
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getPhotoName());
        statement.setInt(4, entity.getType().ordinal());
        statement.setBoolean(5, entity.isDisabled());
    }

    @Override
    protected void updateParams(ProductGroup entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getDescription());
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getPhotoName());
        statement.setInt(4, entity.getType().ordinal());
        statement.setBoolean(5, entity.isDisabled());
        statement.setInt(6, entity.getId());
    }

    public List<ProductGroup> findAllByProductTypeAndDisabled(ProductType type, boolean disabled,
                                                              Transaction transaction) throws NullParamDaoException {
        if (type == null) {
            throw new NullParamDaoException("type is null");
        }
        LOGGER.debug("entered findAllByProductTypeAndDisabled");
        Connection cn = transaction.getConnection();
        LOGGER.info("findAllByProductTypeAndDisabled: cn = {}", cn);
        List<ProductGroup> productGroups = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(FIND_ALL_BY_PRODUCT_GROUP_NOT_DISABLED)) {
            statement.setInt(1, type.ordinal());
            statement.setBoolean(2, disabled);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductGroup entity = findEntity(resultSet);
                productGroups.add(entity);
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.info("Exception while executing findAllByProductTypeAndDisabled ", e);
        }
        return productGroups;
    }

    @Override
    protected Integer getIdFromGeneratedKeys(ResultSet generatedKeys) throws SQLException {
        return generatedKeys.getInt(1);
    }
}
