package kz.epam.pizzeria.dao.mysql.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.dao.exception.NullParamDaoException;
import kz.epam.pizzeria.dao.mysql.AbstractBaseDao;
import kz.epam.pizzeria.dao.mysql.Transaction;
import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.entity.db.impl.ProductGroup;

import java.sql.*;
import java.util.*;

public class ProductDaoImpl extends AbstractBaseDao<Integer, Product> {
    private static final Logger LOGGER = LogManager.getLogger(ProductDaoImpl.class);
    private static final String FIND_ALL_QUERY = "SELECT id, price, weight, product_group_id FROM product ORDER BY id;";
    private static final String FIND_ALL_BY_PART_QUERY = "SELECT id, price, weight, product_group_id FROM product ORDER BY id LIMIT ? OFFSET ?;";
    private static final String FIND_BY_ID_QUERY = "SELECT id, price, weight, product_group_id FROM product WHERE id = ?;";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM product WHERE id = ?";
    private static final String CREATE_QUERY = "INSERT INTO product (price, weight, product_group_id) VALUES (?,?,?);";
    private static final String UPDATE_QUERY = "UPDATE product SET  price = ?, weight = ?, product_group_id = ? WHERE id = ?";
    private static final String FIND_PRODUCT_BY_PRODUCT_GROUP_QUERY = "SELECT id, price, weight, product_group_id FROM product " +
            "WHERE product_group_id = ?;";
    private static final String FIND_PRODUCTS_BY_ORDER_QUERY = "SELECT id, price, weight, product_group_id, count " +
            "FROM product INNER JOIN order_product ON product.id = order_product.product_id WHERE order_id = ?;";
    private static final String FIND_ALL_BY_PRODUCT_ID_NOT_DISABLED = "SELECT prod.id as id, prod.price as price, " +
            "prod.weight as weight, prod.product_group_id as product_group_id " +
            "FROM product as prod INNER JOIN product_group as prodgr ON prod.product_group_id = prodgr.id " +
            "WHERE prodgr.disabled = FALSE ORDER BY prod.id;";
    private static final String COUNT_SQL_QUERY = "SELECT count(id) FROM product;";

    public ProductDaoImpl() {
        super(FIND_ALL_QUERY, FIND_BY_ID_QUERY, DELETE_BY_ID_QUERY, CREATE_QUERY, UPDATE_QUERY, FIND_ALL_BY_PART_QUERY, COUNT_SQL_QUERY);
    }

    protected Product findEntity(ResultSet resultSet) throws SQLException {
        int productGroupId = resultSet.getInt("product_group_id");
        ProductGroup productGroup;
        if (productGroupId == 0) {
            productGroup = null;
        } else {
            productGroup = ProductGroup.newBuilder().id(productGroupId).build();
        }
        return Product.newBuilder()
                .id(resultSet.getInt("id"))
                .price(resultSet.getInt("price"))
                .weight(resultSet.getInt("weight"))
                .productGroup(productGroup)
                .build();
    }

    @Override
    protected void idParam(PreparedStatement statement, Integer integer) throws SQLException {
        statement.setInt(1, integer);
    }

    protected void createParams(Product entity, PreparedStatement statement) throws SQLException {
        statement.setInt(1, entity.getPrice());
        statement.setInt(2, entity.getWeight());
        setProductGroup(3, entity.getProductGroup(), statement);
    }

    protected void updateParams(Product entity, PreparedStatement statement) throws SQLException {
        statement.setInt(1, entity.getPrice());
        statement.setInt(2, entity.getWeight());
        setProductGroup(3, entity.getProductGroup(), statement);
        statement.setInt(4, entity.getId());
    }

    private void setProductGroup(int index, ProductGroup productGroup,
                                 PreparedStatement statement) throws SQLException {
        if (productGroup == null || productGroup.getId() == null) {
            statement.setNull(index, Types.INTEGER);
        } else {
            statement.setInt(index, productGroup.getId());
        }
    }

    public List<Product> findAllByProductGroupId(Integer id, Transaction transaction) throws NullParamDaoException {
        if (id == null) {
            throw new NullParamDaoException("id is null");
        }
        Connection cn = transaction.getConnection();
        List<Product> productGroups = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(FIND_PRODUCT_BY_PRODUCT_GROUP_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product entity = findEntity(resultSet);
                productGroups.add(entity);
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.info("Exception while executing findAllByProductGroupId ", e);
        }
        return productGroups;
    }

    @Override
    protected Integer getIdFromGeneratedKeys(ResultSet generatedKeys) throws SQLException {
        return generatedKeys.getInt(1);
    }

    public Map<Product, Integer> findAllByOrderId(Integer id, Transaction transaction) {
        Connection cn = transaction.getConnection();
        Map<Product, Integer> result = new HashMap<>();
        try (PreparedStatement statement = cn.prepareStatement(FIND_PRODUCTS_BY_ORDER_QUERY)) {
            idParam(statement, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product entity = findEntity(resultSet);
                Integer count = findCount(resultSet);
                result.put(entity, count);
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.info("Exception while executing findAllByOrderId ", e);
        }
        return result;
    }

    private Integer findCount(ResultSet resultSet) throws SQLException {
        return resultSet.getInt("count");
    }

    public List<Product> findAllByProductGroupNotDisabled(Transaction transaction) {
        Connection cn = transaction.getConnection();
        List<Product> entities = new ArrayList<>();
        try (Statement statement = cn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(FIND_ALL_BY_PRODUCT_ID_NOT_DISABLED);
            while (resultSet.next()) {
                Product entity = findEntity(resultSet);
                entities.add(entity);
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.info("Exception while executing findAllByProductGroupNotDisabled ", e);
        }
        return entities;
    }
}
