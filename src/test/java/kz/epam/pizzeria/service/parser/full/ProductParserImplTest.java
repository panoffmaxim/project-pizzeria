package kz.epam.pizzeria.service.parser.full;

import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.service.parser.full.impl.ProductParserImpl;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static kz.epam.pizzeria.constant.OtherConstants.POSTFIX;
import static org.testng.Assert.*;

public class ProductParserImplTest {
    private final ProductParser productParser = new ProductParserImpl();

    @Test
    public void testParseProduct() {
        Map<String, String> redirect = new HashMap<>();
        Product product = productParser.parseProduct(redirect, "1", "2342", "222");
        assertNotNull(product);
    }

    @Test
    public void testParseProductNegative() {
        Map<String, String> redirect = new HashMap<>();
        Product product = productParser.parseProduct(redirect, "1", "asd", "222");
        assertNull(product);
    }

    @Test
    public void testParseProductErrMsg() {
        Map<String, String> redirect = new HashMap<>();
        productParser.parseProduct(redirect, "1", "asd", "222");
        assertTrue(redirect.containsKey("price" + POSTFIX));
    }

    @Test
    public void testParseProductMsg() {
        Map<String, String> redirect = new HashMap<>();
        productParser.parseProduct(redirect, "1", "2342", "222");
        assertTrue(redirect.containsKey("price"));
    }

    @Test
    public void testParseProductWithId() {
        Map<String, String> redirect = new HashMap<>();
        Product product = productParser.parseProductWithId(redirect, "1", "2", "2342", "333");
        assertNotNull(product);
    }

    @Test
    public void testParseProductWithIdNegative() {
        Map<String, String> redirect = new HashMap<>();
        Product product = productParser.parseProductWithId(redirect, "1", "2", "asd", "333");
        assertNull(product);
    }

    @Test
    public void testParseProductWithIdErrMsg() {
        Map<String, String> redirect = new HashMap<>();
        productParser.parseProductWithId(redirect, "1", "2", "asd", "333");
        assertTrue(redirect.containsKey("price" + POSTFIX));
    }

    @Test
    public void testParseProductWithIdMsg() {
        Map<String, String> redirect = new HashMap<>();
        productParser.parseProductWithId(redirect, "1", "2", "2", "333");
        assertTrue(redirect.containsKey("price"));
    }
}
