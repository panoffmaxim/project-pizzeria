package kz.epam.pizzeria.service.parser.full.impl;

import kz.epam.pizzeria.service.parser.full.OrderParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.enums.OrderStatus;
import kz.epam.pizzeria.entity.enums.PaymentType;
import kz.epam.pizzeria.entity.db.impl.DeliveryInf;
import kz.epam.pizzeria.entity.db.impl.Order;
import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.entity.struct.OptionalNullable;
import kz.epam.pizzeria.service.parser.helper.ValidateAndPutter;
import kz.epam.pizzeria.service.parser.helper.impl.ValidateAndPutterImpl;
import kz.epam.pizzeria.service.parser.parts.impl.*;
import kz.epam.pizzeria.service.validator.BasketValidator;

import java.time.LocalDateTime;
import java.util.Map;

public class OrderParserImpl implements OrderParser {
    private static final Logger LOGGER = LogManager.getLogger(OrderParserImpl.class);
    private final StreetParserOrder streetParserOrder = StreetParserOrder.getInstance();
    private final CommentsParser commentsParser = CommentsParser.getInstance();
    private final FloorParser floorParser = FloorParser.getInstance();
    private final PorchParser porchParser = PorchParser.getInstance();
    private final RoomParser roomParser = RoomParser.getInstance();
    private final HouseParserOrder houseParserOrder = HouseParserOrder.getInstance();
    private final NameParser nameParser = NameParser.getInstance();
    private final PhoneParser phoneParser = PhoneParser.getInstance();
    private final EmailParser emailParser = EmailParser.getInstance();
    private final DateTimeParser dateTimeParser = DateTimeParser.getInstance();
    private final TimeParser timeParser = TimeParser.getInstance();
    private final PriceParser priceParser = PriceParser.getInstance();
    private final IdParser idParser = IdParser.getInstance();
    private final OrderStatusForOperatorParser orderStatusForOperatorParser = OrderStatusForOperatorParser.getInstance();
    private final PaymentTypeParser paymentTypeParser = PaymentTypeParser.getInstance();
    private final BasketValidator basketValidator = BasketValidator.getInstance();
    private final ValidateAndPutter validateAndPutter = ValidateAndPutterImpl.getInstance();

    @Override
    public Order parse(Map<String, String> redirect, String streetParam, String commentsParam, String floorParam, String porchParam,
                       String roomParam, String houseParam, String nameParam, String phoneParam, String emailParam, String timeParam,
                       Map<Product, Integer> basket, String paymentTypeParam) {
        OptionalNullable<String> name = nameParser.parse(nameParam);
        OptionalNullable<String> house = houseParserOrder.parse(houseParam);
        OptionalNullable<String> room = roomParser.parse(roomParam);
        OptionalNullable<Integer> porch = porchParser.parse(porchParam);
        OptionalNullable<Integer> floor = floorParser.parse(floorParam);
        OptionalNullable<String> phone = phoneParser.parse(phoneParam);
        OptionalNullable<String> email = emailParser.parse(emailParam);
        OptionalNullable<String> street = streetParserOrder.parse(streetParam);
        OptionalNullable<String> comments = commentsParser.parse(commentsParam);
        OptionalNullable<LocalDateTime> time = timeParser.parse(timeParam);
        OptionalNullable<PaymentType> paymentType = paymentTypeParser.parse(paymentTypeParam);
        boolean result = validateAndPutter.validateAndPut(redirect, name, "name", nameParam) &
                validateAndPutter.validateAndPut(redirect, house, "house", houseParam) &
                validateAndPutter.validateAndPut(redirect, room, "room", roomParam) &
                validateAndPutter.validateAndPut(redirect, porch, "porch", porchParam) &
                validateAndPutter.validateAndPut(redirect, floor, "floor", floorParam) &
                validateAndPutter.validateAndPut(redirect, phone, "tel", phoneParam) &
                validateAndPutter.validateAndPut(redirect, email, "email", emailParam) &
                validateAndPutter.validateAndPut(redirect, comments, "comments", commentsParam) &
                validateAndPutter.validateAndPut(redirect, time, "time", timeParam) &
                validateAndPutter.validateAndPut(redirect, street, "street", streetParam) &
                validateAndPutter.validateAndPut(redirect, paymentType, "payment_type", paymentTypeParam);
        if (result && basketValidator.isValid(basket, redirect, "basket")) {
            DeliveryInf deliveryInf = DeliveryInf.newBuilder()
                    .porch(porch.get())
                    .deliveryTime(time.get())
                    .comments(comments.get())
                    .email(email.get())
                    .phone(phone.get())
                    .floor(floor.get())
                    .house(house.get())
                    .room(room.get())
                    .street(street.get())
                    .build();
            return Order.newBuilder()
                    .creation(LocalDateTime.now())
                    .paymentType(paymentType.get())
                    .clientName(name.get())
                    .deliveryInf(deliveryInf)
                    .status(OrderStatus.CONFIRMED)
                    .price(calcSum(basket))
                    .products(basket)
                    .user(null)
                    .build();
        } else {
            return null;
        }
    }

    private Integer calcSum(Map<Product, Integer> basket) {
        return basket.entrySet().stream()
                .map(e -> e.getKey().getPrice() * e.getValue())
                .reduce(0, Integer::sum);
    }

    @Override
    public boolean parseWithBase(Map<String, String> redirect, Order order, String streetParam, String commentsParam, String floorParam,
                                 String porchParam, String roomParam, String houseParam, String nameParam, String phoneParam, String emailParam,
                                 String timeParam, String paymentTypeParam) {
        OptionalNullable<String> name = nameParser.parse(nameParam);
        OptionalNullable<String> house = houseParserOrder.parse(houseParam);
        OptionalNullable<String> room = roomParser.parse(roomParam);
        OptionalNullable<Integer> porch = porchParser.parse(porchParam);
        OptionalNullable<Integer> floor = floorParser.parse(floorParam);
        OptionalNullable<String> phone = phoneParser.parse(phoneParam);
        OptionalNullable<String> email = emailParser.parse(emailParam);
        OptionalNullable<String> street = streetParserOrder.parse(streetParam);
        OptionalNullable<String> comments = commentsParser.parse(commentsParam);
        OptionalNullable<LocalDateTime> time = timeParser.parse(timeParam);
        OptionalNullable<PaymentType> paymentType = paymentTypeParser.parse(paymentTypeParam);
        boolean result = validateAndPutter.validateAndPut(redirect, name, "name", nameParam) &
                validateAndPutter.validateAndPut(redirect, house, "house", houseParam) &
                validateAndPutter.validateAndPut(redirect, room, "room", roomParam) &
                validateAndPutter.validateAndPut(redirect, porch, "porch", porchParam) &
                validateAndPutter.validateAndPut(redirect, floor, "floor", floorParam) &
                validateAndPutter.validateAndPut(redirect, phone, "tel", phoneParam) &
                validateAndPutter.validateAndPut(redirect, email, "email", emailParam) &
                validateAndPutter.validateAndPut(redirect, comments, "comments", commentsParam) &
                validateAndPutter.validateAndPut(redirect, time, "time", timeParam) &
                validateAndPutter.validateAndPut(redirect, street, "street", streetParam) &
                validateAndPutter.validateAndPut(redirect, paymentType, "payment_type", paymentTypeParam);
        if (result && basketValidator.isValid(order.getProducts(), redirect, "basket")) {
            DeliveryInf deliveryInf = DeliveryInf.newBuilder()
                    .porch(porch.get())
                    .deliveryTime(time.get())
                    .comments(comments.get())
                    .email(email.get())
                    .phone(phone.get())
                    .floor(floor.get())
                    .house(house.get())
                    .room(room.get())
                    .street(street.get())
                    .build();
            order.setCreation(LocalDateTime.now());
            order.setClientName(name.get());
            order.setPaymentType(paymentType.get());
            order.setDeliveryInf(deliveryInf);
            order.setStatus(OrderStatus.CONFIRMED);
            order.setPrice(calcSum(order.getProducts()));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean parseForOperatorWithBase(Map<String, String> redirect, Order order, String streetParam, String commentsParam, String floorParam, String porchParam, String roomParam, String houseParam, String nameParam, String phoneParam, String emailParam, String timeParam, String statusParam, String paymentTypeParam, String priceParam) {
        OptionalNullable<String> name = nameParser.parse(nameParam);
        OptionalNullable<String> house = houseParserOrder.parse(houseParam);
        OptionalNullable<String> room = roomParser.parse(roomParam);
        OptionalNullable<Integer> porch = porchParser.parse(porchParam);
        OptionalNullable<Integer> floor = floorParser.parse(floorParam);
        OptionalNullable<String> phone = phoneParser.parse(phoneParam);
        OptionalNullable<String> email = emailParser.parse(emailParam);
        OptionalNullable<String> street = streetParserOrder.parse(streetParam);
        OptionalNullable<String> comments = commentsParser.parse(commentsParam);
        OptionalNullable<LocalDateTime> time = dateTimeParser.parse(timeParam);
        OptionalNullable<Integer> price = priceParser.parse(priceParam);
        OptionalNullable<OrderStatus> status = orderStatusForOperatorParser.parse(statusParam);
        OptionalNullable<PaymentType> paymentType = paymentTypeParser.parse(paymentTypeParam);
        LOGGER.debug("dateTimeParser = {}", dateTimeParser);
        boolean result = validateAndPutter.validateAndPut(redirect, name, "name", nameParam) &
                validateAndPutter.validateAndPut(redirect, house, "house", houseParam) &
                validateAndPutter.validateAndPut(redirect, room, "room", roomParam) &
                validateAndPutter.validateAndPut(redirect, porch, "porch", porchParam) &
                validateAndPutter.validateAndPut(redirect, floor, "floor", floorParam) &
                validateAndPutter.validateAndPut(redirect, phone, "tel", phoneParam) &
                validateAndPutter.validateAndPut(redirect, email, "email", emailParam) &
                validateAndPutter.validateAndPut(redirect, comments, "comments", commentsParam) &
                validateAndPutter.validateAndPut(redirect, time, "time", timeParam) &
                validateAndPutter.validateAndPut(redirect, street, "street", streetParam) &
                validateAndPutter.validateAndPut(redirect, price, "price", priceParam) &
                validateAndPutter.validateAndPut(redirect, status, "status", statusParam) &
                validateAndPutter.validateAndPut(redirect, paymentType, "payment_type", paymentTypeParam);
        if (result && basketValidator.isValid(order.getProducts(), redirect, "basket")) {
            DeliveryInf deliveryInf = order.getDeliveryInf();
            deliveryInf.setPorch(porch.get());
            deliveryInf.setDeliveryTime(time.get());
            deliveryInf.setComments(comments.get());
            deliveryInf.setEmail(email.get());
            deliveryInf.setPhone(phone.get());
            deliveryInf.setFloor(floor.get());
            deliveryInf.setHouse(house.get());
            deliveryInf.setRoom(room.get());
            deliveryInf.setStreet(street.get());
            order.setCreation(LocalDateTime.now());
            order.setClientName(name.get());
            order.setPaymentType(PaymentType.CASH);
            order.setDeliveryInf(deliveryInf);
            order.setStatus(OrderStatus.CONFIRMED);
            order.setPrice(price.get());
            order.setStatus(status.get());
            order.setPaymentType(paymentType.get());
            return true;
        } else {
            return false;
        }
    }

    public IdParser getIdParser() {
        return idParser;
    }
}
