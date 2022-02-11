package kz.epam.pizzeria.service.encryption;

import kz.epam.pizzeria.entity.db.impl.User;

public interface ApplicationEncrypt {
    String calcRegistrationToken(User user);

    String calcUserPasswordHash(String password);
}
