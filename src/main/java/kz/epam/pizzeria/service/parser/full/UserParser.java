package kz.epam.pizzeria.service.parser.full;

import kz.epam.pizzeria.entity.db.impl.User;

import java.util.Map;

public interface UserParser {
    User parseUser(Map<String, String> redirect, String usernameParam, String passwordParam, String roleParam, String nameParam,
                   String surnameParam, String houseParam, String roomParam, String porchParam, String floorParam, String phoneParam,
                   String emailParam, String streetParam);

    boolean parseUserWithId(Map<String, String> redirect, User base, String usernameParam, String roleParam, String nameParam,
                            String surnameParam, String houseParam, String roomParam, String porchParam, String floorParam,
                            String phoneParam, String emailParam, String streetParam, String isBlocked);

    User parseRegistrationUser(Map<String, String> redirect, String emailParam, String phoneParam, String usernameParam,
                               String passwordParam, String nameParam, String surnameParam, String streetParam, String houseParam,
                               String roomParam, String porchParam, String floorParam);

    User parseRegistrationUserWithToken(Map<String, String> redirect, String emailParam, String phoneParam, String usernameParam,
                                        String passwordParam, String nameParam, String surnameParam, String streetParam, String houseParam,
                                        String roomParam, String porchParam, String floorParam, String token);

    boolean parseWithBaseSelfChange(Map<String, String> redirect, User base, String nameParam, String surnameParam, String houseParam,
                                    String roomParam, String porchParam, String floorParam, String phoneParam, String streetParam);

    boolean parseChangePassword(Map<String, String> redirect, User base, String passwordOld, String passwordNew);
}
