package kz.epam.pizzeria.service.parser.full.impl;

import kz.epam.pizzeria.constant.OtherConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.entity.enums.Role;
import kz.epam.pizzeria.entity.struct.OptionalNullable;
import kz.epam.pizzeria.service.encryption.ApplicationEncrypt;
import kz.epam.pizzeria.service.encryption.impl.ApplicationEncryptImpl;
import kz.epam.pizzeria.service.parser.helper.ValidateAndPutter;
import kz.epam.pizzeria.service.parser.helper.impl.ValidateAndPutterImpl;
import kz.epam.pizzeria.service.parser.parts.impl.*;

import java.time.LocalDateTime;
import java.util.Map;

public class UserParserImpl implements kz.epam.pizzeria.service.parser.full.UserParser {
    private static final Logger LOGGER = LogManager.getLogger(UserParserImpl.class);
    private final ValidateAndPutter validateAndPutter = ValidateAndPutterImpl.getInstance();
    private final ApplicationEncrypt applicationEncrypt = ApplicationEncryptImpl.getInstance();
    private final EmailParser emailParser = EmailParser.getInstance();
    private final FloorParser floorParser = FloorParser.getInstance();
    private final HouseParserUser houseParserOrder = HouseParserUser.getInstance();
    private final NameParser nameParser = NameParser.getInstance();
    private final PasswordParser passwordParser = PasswordParser.getInstance();
    private final PhoneParser phoneParser = PhoneParser.getInstance();
    private final PorchParser porchParser = PorchParser.getInstance();
    private final RoleParser roleParser = RoleParser.getInstance();
    private final RoomParser roomParser = RoomParser.getInstance();
    private final StreetParserUser streetParserOrder = StreetParserUser.getInstance();
    private final UsernameParser usernameParser = UsernameParser.getInstance();
    private final SurnameParser surnameParser = SurnameParser.getInstance();
    private final IdParser idParser = IdParser.getInstance();
    private final BooleanParser booleanParser = BooleanParser.getInstance();

    @Override
    public User parseUser(Map<String, String> redirect, String usernameParam, String passwordParam, String roleParam, String nameParam, String surnameParam, String houseParam, String roomParam, String porchParam, String floorParam, String phoneParam, String emailParam, String streetParam) {
        OptionalNullable<String> username = usernameParser.parse(usernameParam);
        OptionalNullable<String> password = passwordParser.parse(passwordParam);
        OptionalNullable<Role> role = roleParser.parse(roleParam);
        OptionalNullable<String> name = nameParser.parse(nameParam);
        OptionalNullable<String> surname = surnameParser.parse(surnameParam);
        OptionalNullable<String> house = houseParserOrder.parse(houseParam);
        OptionalNullable<String> room = roomParser.parse(roomParam);
        OptionalNullable<Integer> porch = porchParser.parse(porchParam);
        OptionalNullable<Integer> floor = floorParser.parse(floorParam);
        OptionalNullable<String> phone = phoneParser.parse(phoneParam);
        OptionalNullable<String> email = emailParser.parse(emailParam);
        OptionalNullable<String> street = streetParserOrder.parse(streetParam);
        boolean result = validateAndPutter.validateAndPut(redirect, username, "username", usernameParam) &
                validateAndPutter.validateAndPut(redirect, password, "password", passwordParam) &
                validateAndPutter.validateAndPut(redirect, role, "role", roleParam) &
                validateAndPutter.validateAndPut(redirect, name, "name", nameParam) &
                validateAndPutter.validateAndPut(redirect, surname, "surname", surnameParam) &
                validateAndPutter.validateAndPut(redirect, house, "house", houseParam) &
                validateAndPutter.validateAndPut(redirect, room, "room", roomParam) &
                validateAndPutter.validateAndPut(redirect, porch, "porch", porchParam) &
                validateAndPutter.validateAndPut(redirect, floor, "floor", floorParam) &
                validateAndPutter.validateAndPut(redirect, phone, "phone", phoneParam) &
                validateAndPutter.validateAndPut(redirect, email, "email", emailParam) &
                validateAndPutter.validateAndPut(redirect, street, "street", streetParam);
        if (result) {
            return User.newBuilder()
                    .username(username.get())
                    .password(applicationEncrypt.calcUserPasswordHash(password.get()))
                    .role(role.get())
                    .name(name.get())
                    .surname(surname.get())
                    .house(house.get())
                    .room(room.get())
                    .porch(porch.get())
                    .floor(floor.get())
                    .phone(phone.get())
                    .email(email.get())
                    .creation(LocalDateTime.now())
                    .isBlocked(false)
                    .street(street.get())
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public boolean parseUserWithId(Map<String, String> redirect, User base, String usernameParam, String roleParam, String nameParam,
                                   String surnameParam, String houseParam, String roomParam, String porchParam, String floorParam,
                                   String phoneParam, String emailParam, String streetParam, String isBlocked) {
        OptionalNullable<String> username = usernameParser.parse(usernameParam);
        OptionalNullable<Role> role = roleParser.parse(roleParam);
        OptionalNullable<String> name = nameParser.parse(nameParam);
        OptionalNullable<String> surname = surnameParser.parse(surnameParam);
        OptionalNullable<String> house = houseParserOrder.parse(houseParam);
        OptionalNullable<String> room = roomParser.parse(roomParam);
        OptionalNullable<Integer> porch = porchParser.parse(porchParam);
        OptionalNullable<Integer> floor = floorParser.parse(floorParam);
        OptionalNullable<String> phone = phoneParser.parse(phoneParam);
        OptionalNullable<String> email = emailParser.parse(emailParam);
        OptionalNullable<String> street = streetParserOrder.parse(streetParam);
        OptionalNullable<Boolean> isBlockedOpt = booleanParser.parse(isBlocked);
        boolean result = validateAndPutter.validateAndPut(redirect, username, "username", usernameParam) &
                validateAndPutter.validateAndPut(redirect, role, "role", roleParam) &
                validateAndPutter.validateAndPut(redirect, name, "name", nameParam) &
                validateAndPutter.validateAndPut(redirect, surname, "surname", surnameParam) &
                validateAndPutter.validateAndPut(redirect, house, "house", houseParam) &
                validateAndPutter.validateAndPut(redirect, room, "room", roomParam) &
                validateAndPutter.validateAndPut(redirect, porch, "porch", porchParam) &
                validateAndPutter.validateAndPut(redirect, floor, "floor", floorParam) &
                validateAndPutter.validateAndPut(redirect, phone, "phone", phoneParam) &
                validateAndPutter.validateAndPut(redirect, email, "email", emailParam) &
                validateAndPutter.validateAndPut(redirect, street, "street", streetParam) &
                validateAndPutter.validateAndPut(redirect, isBlockedOpt, OtherConstants.BLOCKED, isBlocked);
        if (result) {
            base.setUsername(username.get());
            base.setRole(role.get());
            base.setName(name.get());
            base.setSurname(surname.get());
            base.setHouse(house.get());
            base.setRoom(room.get());
            base.setPorch(porch.get());
            base.setFloor(floor.get());
            base.setPhone(phone.get());
            base.setEmail(email.get());
            base.setStreet(street.get());
            base.setBlocked(isBlockedOpt.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User parseRegistrationUser(Map<String, String> redirect, String emailParam, String phoneParam, String usernameParam,
                                      String passwordParam, String nameParam, String surnameParam, String streetParam, String houseParam,
                                      String roomParam, String porchParam, String floorParam) {
        OptionalNullable<String> username = usernameParser.parse(usernameParam);
        OptionalNullable<String> password = passwordParser.parse(passwordParam);
        OptionalNullable<String> name = nameParser.parse(nameParam);
        OptionalNullable<String> surname = surnameParser.parse(surnameParam);
        OptionalNullable<String> house = houseParserOrder.parse(houseParam);
        OptionalNullable<String> room = roomParser.parse(roomParam);
        OptionalNullable<Integer> porch = porchParser.parse(porchParam);
        OptionalNullable<Integer> floor = floorParser.parse(floorParam);
        OptionalNullable<String> phone = phoneParser.parse(phoneParam);
        OptionalNullable<String> email = emailParser.parse(emailParam);
        OptionalNullable<String> street = streetParserOrder.parse(streetParam);
        boolean result = validateAndPutter.validateAndPut(redirect, username, "username", usernameParam) &
                validateAndPutter.validateAndPut(redirect, password, "password", passwordParam) &
                validateAndPutter.validateAndPut(redirect, name, "name", nameParam) &
                validateAndPutter.validateAndPut(redirect, surname, "surname", surnameParam) &
                validateAndPutter.validateAndPut(redirect, house, "house", houseParam) &
                validateAndPutter.validateAndPut(redirect, room, "room", roomParam) &
                validateAndPutter.validateAndPut(redirect, porch, "porch", porchParam) &
                validateAndPutter.validateAndPut(redirect, floor, "floor", floorParam) &
                validateAndPutter.validateAndPut(redirect, phone, "phone", phoneParam) &
                validateAndPutter.validateAndPut(redirect, email, "email", emailParam) &
                validateAndPutter.validateAndPut(redirect, street, "street", streetParam);
        if (result) {
            return User.newBuilder()
                    .username(username.get())
                    .password(password.get())
                    .role(Role.CLIENT)
                    .name(name.get())
                    .surname(surname.get())
                    .house(house.get())
                    .room(room.get())
                    .porch(porch.get())
                    .floor(floor.get())
                    .phone(phone.get())
                    .email(email.get())
                    .creation(LocalDateTime.now())
                    .isBlocked(false)
                    .street(street.get())
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public User parseRegistrationUserWithToken(Map<String, String> redirect, String emailParam, String phoneParam, String usernameParam,
                                               String passwordParam, String nameParam, String surnameParam, String streetParam, String houseParam,
                                               String roomParam, String porchParam, String floorParam, String token) {
        User user = parseRegistrationUser(redirect, emailParam, phoneParam, usernameParam, passwordParam, nameParam, surnameParam, streetParam,
                houseParam, roomParam, porchParam, floorParam);
        LOGGER.debug("parseRegistrationUserWithToken: user = {}", user);
        LOGGER.debug("redirect = {}", redirect);
        OptionalNullable<String> tokenOpt = OptionalNullable.empty();
        if (user != null && applicationEncrypt.calcRegistrationToken(user).equals(token)) {
            tokenOpt = OptionalNullable.of(token);
        }
        boolean tokenResult = validateAndPutter.validateAndPut(redirect, tokenOpt, "token", token);
        if (user != null && tokenResult) {
            user.setPassword(applicationEncrypt.calcUserPasswordHash(passwordParser.parse(passwordParam).get()));
            return user;
        }
        return null;
    }

    @Override
    public boolean parseWithBaseSelfChange(Map<String, String> redirect, User base, String nameParam, String surnameParam, String houseParam,
                                           String roomParam, String porchParam, String floorParam, String phoneParam, String streetParam) {
        OptionalNullable<String> name = nameParser.parse(nameParam);
        OptionalNullable<String> surname = surnameParser.parse(surnameParam);
        OptionalNullable<String> house = houseParserOrder.parse(houseParam);
        OptionalNullable<String> room = roomParser.parse(roomParam);
        OptionalNullable<Integer> porch = porchParser.parse(porchParam);
        OptionalNullable<Integer> floor = floorParser.parse(floorParam);
        OptionalNullable<String> phone = phoneParser.parse(phoneParam);
        OptionalNullable<String> street = streetParserOrder.parse(streetParam);
        boolean result = validateAndPutter.validateAndPut(redirect, name, "name", nameParam) &
                validateAndPutter.validateAndPut(redirect, surname, "surname", surnameParam) &
                validateAndPutter.validateAndPut(redirect, house, "house", houseParam) &
                validateAndPutter.validateAndPut(redirect, room, "room", roomParam) &
                validateAndPutter.validateAndPut(redirect, porch, "porch", porchParam) &
                validateAndPutter.validateAndPut(redirect, floor, "floor", floorParam) &
                validateAndPutter.validateAndPut(redirect, phone, "phone", phoneParam) &
                validateAndPutter.validateAndPut(redirect, street, "street", streetParam);
        if (result) {
            base.setName(name.get());
            base.setSurname(surname.get());
            base.setHouse(house.get());
            base.setRoom(room.get());
            base.setPorch(porch.get());
            base.setFloor(floor.get());
            base.setPhone(phone.get());
            base.setStreet(street.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean parseChangePassword(Map<String, String> redirect, User base, String passwordOld, String passwordNew) {
        String password = base.getPassword();
        if (password.equals(applicationEncrypt.calcUserPasswordHash(passwordOld))) {
            OptionalNullable<String> parse = passwordParser.parse(passwordNew);
            if (parse.isPresent()) {
                base.setPassword(applicationEncrypt.calcUserPasswordHash(parse.get()));
                return true;
            } else {
                redirect.put("new_password_error", "true");
            }
        } else {
            redirect.put("old_password_error", "true");
        }
        return false;
    }

    public IdParser getIdParser() {
        return idParser;
    }
}
