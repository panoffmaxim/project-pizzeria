package kz.epam.pizzeria.service.parser.full.impl;

import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.entity.struct.OptionalNullable;
import kz.epam.pizzeria.service.parser.full.ProductParser;
import kz.epam.pizzeria.service.parser.helper.ValidateAndPutter;
import kz.epam.pizzeria.service.parser.helper.impl.ValidateAndPutterImpl;
import kz.epam.pizzeria.service.parser.parts.impl.PriceParser;
import kz.epam.pizzeria.service.parser.parts.impl.WeightParser;
import kz.epam.pizzeria.service.parser.parts.impl.IdParser;
import kz.epam.pizzeria.service.parser.parts.impl.ProductGroupInProductParser;

import java.util.Map;

public class ProductParserImpl implements ProductParser {
    private final ValidateAndPutter validateAndPutter = ValidateAndPutterImpl.getInstance();
    private final IdParser idParser = IdParser.getInstance();
    private final ProductGroupInProductParser productGroupInProductParser = ProductGroupInProductParser.getInstance();
    private final PriceParser priceParser = PriceParser.getInstance();
    private final WeightParser weightParser = WeightParser.getInstance();

    @Override
    public Product parseProduct(Map<String, String> redirect, String productGroup, String price, String weight) {
        OptionalNullable<Integer> productGroupOpt = productGroupInProductParser.parse(productGroup);
        OptionalNullable<Integer> priceOpt = priceParser.parse(price);
        OptionalNullable<Integer> weightOpt = weightParser.parse(weight);
        boolean result = validateAndPutter.validateAndPut(redirect, productGroupOpt, "product_group", productGroup) &
                validateAndPutter.validateAndPut(redirect, priceOpt, "price", price) &
                validateAndPutter.validateAndPut(redirect, weightOpt, "weight", weight);
        if (result) {
            return Product.newBuilder()
                    .productGroup(
                            ProductGroup.newBuilder().id(productGroupOpt.get()).build()
                    )
                    .weight(weightOpt.get())
                    .price(priceOpt.get())
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public Product parseProductWithId(Map<String, String> redirect, String id, String productGroup, String price, String weight) {
        Product product = parseProduct(redirect, productGroup, price, weight);
        if (product != null) {

            OptionalNullable<Integer> idOpt = idParser.parse(id);
            boolean result = validateAndPutter.validateAndPut(redirect, idOpt, "id", id);
            if (result) {
                product.setId(idOpt.get());
                return product;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
