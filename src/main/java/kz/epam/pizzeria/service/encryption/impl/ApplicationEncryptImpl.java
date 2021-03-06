package kz.epam.pizzeria.service.encryption.impl;

import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.encryption.ApplicationEncrypt;
import org.apache.commons.codec.digest.DigestUtils;

public class ApplicationEncryptImpl implements ApplicationEncrypt {

    private static ApplicationEncryptImpl INSTANCE = new ApplicationEncryptImpl();

    public static ApplicationEncryptImpl getInstance() {
        return INSTANCE;
    }

    private ApplicationEncryptImpl() {
    }

    private static final String REGISTRATION_SECRET = "jdlfkgjneij4lijdfnogijnliu348ilfdjgnlidfjg83";
    private static final String PASSWORD_SECRET = "3lkjfdnligj83ljfdlg38flekLKh3kfuiej84LKDGJ3dlfkgj3";

    private String encryptString(String input, String secretKey) {
        return DigestUtils.sha256Hex(input + secretKey);
    }

    @Override
    public String calcRegistrationToken(User user) {
        String forHashing = user.getEmail() + user.getPhone() + user.getUsername() + user.getPassword() + user.getName() + user.getSurname() + user.getStreet() + user.getHouse() + user.getRoom() + user.getPorch() + user.getFloor();
        return encryptString(forHashing, REGISTRATION_SECRET);
    }

    @Override
    public String calcUserPasswordHash(String password) {
        return encryptString(password, PASSWORD_SECRET);
    }
}
