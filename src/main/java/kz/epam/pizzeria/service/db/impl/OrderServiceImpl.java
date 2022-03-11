package kz.epam.pizzeria.service.db.impl;

import kz.epam.pizzeria.constant.OtherConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.dao.DAOFactory;
import kz.epam.pizzeria.dao.exception.DaoException;
import kz.epam.pizzeria.dao.mysql.Transaction;
import kz.epam.pizzeria.dao.mysql.impl.DeliveryInfDaoImpl;
import kz.epam.pizzeria.dao.mysql.impl.OrderDaoImpl;
import kz.epam.pizzeria.dao.mysql.impl.ProductGroupMysqlDao;
import kz.epam.pizzeria.dao.mysql.impl.ProductDaoImpl;
import kz.epam.pizzeria.entity.db.impl.*;
import kz.epam.pizzeria.entity.enums.OrderStatus;
import kz.epam.pizzeria.service.db.ProductService;
import kz.epam.pizzeria.service.db.OrderService;
import kz.epam.pizzeria.service.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);
    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final OrderDaoImpl orderDaoImpl = dAOFactory.getOrderMysqlDao();
    private final ProductDaoImpl productDaoImpl = dAOFactory.getProductMysqlDao();
    private final DeliveryInfDaoImpl deliveryInfDaoImpl = dAOFactory.getDeliveryInfMysqlDao();
    private final ProductGroupMysqlDao productGroupMysqlDao = dAOFactory.getProductGroupMysqlDao();
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public List<Order> findAll() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return orderDaoImpl.findAll(transaction).stream()
                    .peek(o -> buildOrder(o, transaction))
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findAllByPart(int part) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return orderDaoImpl.findAllByPart(transaction, (part - 1) * OtherConstants.MAX_PAGINATION_ELEMENTS, OtherConstants.MAX_PAGINATION_ELEMENTS).stream()
                    .peek(o -> buildOrder(o, transaction))
                    .collect(Collectors.toList());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void buildOrder(Order o, Transaction transaction) {
        LOGGER.debug("buildOrder: o = {}", o);
        Map<Product, Integer> products =
                productDaoImpl.findAllByOrderId(o.getId(), transaction);
        Map<Product, Integer> buildingProducts = products.entrySet().stream()
                .map(p -> {
                    buildProduct(p.getKey(), transaction);
                    return Map.entry(p.getKey(), p.getValue());
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        LOGGER.debug("buildingProducts = {}", buildingProducts);
        o.setProducts(buildingProducts);
        LOGGER.debug("buildOrder: o = {}", o);
        if (o.getDeliveryInf() != null) {
            DeliveryInf delInf = deliveryInfDaoImpl.findEntityById(o.getDeliveryInf().getId(), transaction);
            o.setDeliveryInf(delInf);
        }
    }

    private void buildProduct(Product p, Transaction transaction) {
        ProductGroup productGroup = p.getProductGroup();
        if (productGroup != null) {
            Integer id = productGroup.getId();
            p.setProductGroup(productGroupMysqlDao.findEntityById(id, transaction));
        }
    }

    @Override
    public Order findEntityById(Integer id) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            Order order = orderDaoImpl.findEntityById(id, transaction);
            if (order != null) {
                buildOrder(order, transaction);
            }
            return order;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Order entity) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = orderDaoImpl.delete(entity, transaction);
            LOGGER.debug("result = {}", result);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order create(Order entity) throws ServiceException {
        if (entity == null) {
            return null;
        }
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            LOGGER.debug("create1");
            DeliveryInf deliveryInf = deliveryInfDaoImpl.create(entity.getDeliveryInf(), transaction);
            if (deliveryInf == null) {
                transaction.rollBack();
                return null;
            }
            entity.setDeliveryInf(deliveryInf);
            Order order = orderDaoImpl.create(entity, transaction);
            if (order == null) {
                transaction.rollBack();
                return null;
            }
            Map<Product, Integer> products = order.getProducts();
            boolean result = saveProducts(products, order, transaction);
            if (!result) {
                transaction.rollBack();
                return null;
            } else {
                transaction.commit();
            }
            return order;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean saveProducts(Map<Product, Integer> products, Order order, Transaction transaction) {
        return orderDaoImpl.addProductsOnCreate(products, order, transaction);
    }

    @Override
    public boolean update(Order entity) throws ServiceException {
        if (entity == null || entity.getId() == null) {
            return false;
        }
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            Order oldOrder = orderDaoImpl.findEntityById(entity.getId(), transaction);
            DeliveryInf curDeliveryInf = entity.getDeliveryInf();
            setIdToDeliveryInf(oldOrder, curDeliveryInf);
            if (!saveDeliveryInf(transaction, curDeliveryInf)) {
                return false;
            }
            boolean result = orderDaoImpl.update(entity, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean saveDeliveryInf(Transaction transaction, DeliveryInf curDeliveryInf) throws DaoException {
        if (curDeliveryInf != null && curDeliveryInf.getId() != null) {
            boolean update = deliveryInfDaoImpl.update(curDeliveryInf, transaction);
            if (!update) {
                transaction.rollBack();
                return false;
            }
        } else {
            DeliveryInf deliveryInf = deliveryInfDaoImpl.create(curDeliveryInf, transaction);
            if (deliveryInf == null) {
                transaction.rollBack();
                return false;
            }
        }
        return true;
    }

    private void setIdToDeliveryInf(Order oldOrder, DeliveryInf curDeliveryInf) {
        if (oldOrder != null &&
                oldOrder.getDeliveryInf() != null &&
                oldOrder.getDeliveryInf().getId() != null) {
            curDeliveryInf.setId(oldOrder.getDeliveryInf().getId());
        }
    }

    @Override
    public void plusProduct(final Integer orderId, final Integer prodId) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            if (!orderDaoImpl.plusProductFirst(orderId, prodId, transaction)) {
                LOGGER.debug("executing second");
                if (!orderDaoImpl.plusExistingProduct(orderId, prodId, transaction)) {
                    transaction.rollBack();
                    throw new ServiceException();
                } else {
                    transaction.commit();
                }
            } else {
                transaction.commit();
            }
            LOGGER.debug("executed first");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteProduct(Integer orderId, Integer prodId) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = orderDaoImpl.removeProduct(orderId, prodId, transaction);
            LOGGER.debug("deleteProduct: result = {}", result);
            if (!result) {
                transaction.rollBack();
                throw new ServiceException();
            } else {
                transaction.commit();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void minusOrDelete(Integer orderId, Integer prodId) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            if (!orderDaoImpl.minusProduct(orderId, prodId, transaction)) {
                if (!orderDaoImpl.removeProduct(orderId, prodId, transaction)) {
                    transaction.rollBack();
                    throw new ServiceException();
                } else {
                    transaction.commit();
                }
            } else {
                transaction.commit();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order findOrCreateCurrentByUserId(Integer userId) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            Order current = findCurrentByUserId(userId);
            if (current == null) {
                current = orderDaoImpl.create(
                        Order.newBuilder()
                                .user(User.newBuilder().id(userId).build())
                                .status(OrderStatus.WAITING)
                                .build(),
                        transaction
                );
            }
            LOGGER.debug("current = {}", current);
            buildOrder(current, transaction);
            if (current != null) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return current;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order findCurrentByUserId(Integer id) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            Order current = orderDaoImpl.findCurrentByUserId(id, transaction);
            if (current != null) {
                buildOrder(current, transaction);
            }
            return current;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean cancelOrDeleteById(Integer idInt) throws ServiceException {
        LOGGER.debug("cancelOrDeleteById working...");
        if (idInt == null) {
            throw new ServiceException("Input is null");
        }
        Order entityById = findEntityById(idInt);
        LOGGER.debug("cancelOrDeleteById: entityById = {}", entityById);
        if (entityById == null) {
            throw new ServiceException("No order with so id. id = " + idInt);
        }
        if (entityById.getStatus() == OrderStatus.WAITING) {
            LOGGER.debug("deleting...");
            return delete(entityById);
        } else {
            entityById.setStatus(OrderStatus.CANCELED);
            return update(entityById);
        }
    }

    @Override
    public int count() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return orderDaoImpl.count(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public ProductService getProductService() {
        return productService;
    }
}
