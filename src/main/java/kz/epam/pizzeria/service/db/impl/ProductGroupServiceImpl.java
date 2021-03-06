package kz.epam.pizzeria.service.db.impl;

import kz.epam.pizzeria.constant.OtherConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.dao.DAOFactory;
import kz.epam.pizzeria.dao.exception.DaoException;
import kz.epam.pizzeria.dao.exception.NullParamDaoException;
import kz.epam.pizzeria.dao.mysql.Transaction;
import kz.epam.pizzeria.dao.mysql.impl.ProductGroupMysqlDao;
import kz.epam.pizzeria.dao.mysql.impl.ProductDaoImpl;
import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.entity.enums.ProductType;
import kz.epam.pizzeria.service.db.ProductGroupService;
import kz.epam.pizzeria.service.exception.NullServiceException;
import kz.epam.pizzeria.service.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

public class ProductGroupServiceImpl implements ProductGroupService {
    private static final Logger LOGGER = LogManager.getLogger(ProductGroupServiceImpl.class);
    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ProductDaoImpl productDaoImpl = dAOFactory.getProductMysqlDao();
    private final ProductGroupMysqlDao productGroupMysqlDao = dAOFactory.getProductGroupMysqlDao();

    @Override
    public List<ProductGroup> findAll() throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            List<ProductGroup> all = productGroupMysqlDao.findAll(transaction);
            for (ProductGroup productGroup : all) {
                buildProductGroup(productGroup, transaction);
            }
            return all;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ProductGroup> findAllByPart(int part) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            List<ProductGroup> all = productGroupMysqlDao.findAllByPart(transaction, (part - 1) * OtherConstants.MAX_PAGINATION_ELEMENTS,
                    OtherConstants.MAX_PAGINATION_ELEMENTS);
            for (ProductGroup productGroup : all) {
                buildProductGroup(productGroup, transaction);
            }
            return all;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ProductGroup findEntityById(Integer id) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            ProductGroup entityById = productGroupMysqlDao.findEntityById(id, transaction);
            if (entityById != null) {
                buildProductGroup(entityById, transaction);
            }
            return entityById;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ProductGroup create(ProductGroup entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            ProductGroup productGroup = productGroupMysqlDao.create(entity, transaction);
            if (productGroup != null) {
                insertProductsIfPossible(productGroup, transaction);
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return productGroup;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void insertProductsIfPossible(ProductGroup entity, Transaction transaction) {
        List<Product> products = entity.getProducts();
        if (products != null) {
            products.stream()
                    .map(p -> productDaoImpl.findEntityById(p.getId(), transaction))
                    .peek(p -> p.setProductGroup(entity))
                    .forEach(ent -> productDaoImpl.update(ent, transaction));
        }
    }

    @Override
    public boolean update(ProductGroup entity) throws ServiceException {
        if (entity == null || entity.getId() == null) {
            return false;
        }
        if (!updatePhotoNameIfNeed(entity)) {
            return false;
        }
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            try {
                boolean update = productGroupMysqlDao.update(entity, transaction);
                insertAndDeleteOthers(entity, transaction);
                if (update) {
                    transaction.commit();
                }
                return update;
            } catch (ServiceException e) {
                transaction.rollBack();
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean updatePhotoNameIfNeed(ProductGroup entity) throws ServiceException {
        if (entity.getPhotoName() == null) {
            ProductGroup entityById = findEntityById(entity.getId());
            if (entityById != null) {
                entity.setPhotoName(entityById.getPhotoName());
            } else {
                return false;
            }
        }
        return true;
    }

    private void insertAndDeleteOthers(ProductGroup entity, Transaction transaction) throws ServiceException, DaoException {
        try {
            final List<Product> products = entity.getProducts();
            final List<Product> allByProductGroupId = productDaoImpl.findAllByProductGroupId(entity.getId(), transaction);
            List<Product> toDelete = allByProductGroupId.stream()
                    .filter(p -> {
                        for (Product product : products) {
                            if (product.getId().equals(p.getId())) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .collect(Collectors.toList());
            deleteAll(toDelete, transaction);
            List<Product> toAdd = products.stream()
                    .filter(p -> {
                        for (Product product : allByProductGroupId) {
                            if (product.getId().equals(p.getId())) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .collect(Collectors.toList());
            insertAll(toAdd, entity, transaction);
        } catch (NullParamDaoException e) {
            transaction.rollBack();
            throw new ServiceException(e);
        }
    }

    private void deleteAll(List<Product> toDelete, Transaction transaction) {
        for (Product product : toDelete) {
            Product entityById = productDaoImpl.findEntityById(product.getId(), transaction);
            entityById.setProductGroup(null);
            productDaoImpl.update(entityById, transaction);
        }
    }

    private void insertAll(List<Product> toAdd, ProductGroup entity, Transaction transaction) {
        for (Product product : toAdd) {
            Product entityById = productDaoImpl.findEntityById(product.getId(), transaction);
            entityById.setProductGroup(entity);
            productDaoImpl.update(entityById, transaction);
        }
    }

    @Override
    public List<ProductGroup> findAllByProductTypeNotDisabled(ProductType type) throws ServiceException {

        try (final Transaction transaction = dAOFactory.createTransaction()) {
            try {
                List<ProductGroup> list = productGroupMysqlDao.findAllByProductTypeAndDisabled(type, false, transaction);
                for (ProductGroup productGroup : list) {
                    buildProductGroup(productGroup, transaction);
                }
                return list;
            } catch (NullParamDaoException e) {
                throw new NullServiceException(e);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ProductGroup> findAllExcept(ProductGroup productGroup) throws ServiceException {
        List<ProductGroup> all = findAll();
        if (productGroup != null) {
            all = all.stream()
                    .filter(p -> !p.getId().equals(productGroup.getId()))
                    .collect(Collectors.toList());
        }
        return all;
    }

    @Override
    public void disableById(Integer id) throws ServiceException {
        if (id == null) {
            throw new NullServiceException("null id");
        }
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            ProductGroup productGroup = productGroupMysqlDao.findEntityById(id, transaction);
            if (!productGroup.isDisabled()) {
                productGroup.setDisabled(true);
                productGroupMysqlDao.update(productGroup, transaction);
                transaction.commit();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void enableById(Integer id) throws ServiceException {
        if (id == null) {
            throw new NullServiceException("null id");
        }
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            ProductGroup productGroup = productGroupMysqlDao.findEntityById(id, transaction);
            if (productGroup.isDisabled()) {
                productGroup.setDisabled(false);
                productGroupMysqlDao.update(productGroup, transaction);
                transaction.commit();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void buildProductGroup(ProductGroup productGroup, Transaction transaction) throws NullServiceException {
        try {
            List<Product> products = productGroup.getProducts();
            List<Product> allByProductGroupId = productDaoImpl.findAllByProductGroupId(productGroup.getId(), transaction);
            products.addAll(allByProductGroupId);
            LOGGER.info("buildProductGroup: products = {}", products);
        } catch (NullParamDaoException e) {
            throw new NullServiceException();
        }
    }

    @Override
    public int count() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return productGroupMysqlDao.count(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
