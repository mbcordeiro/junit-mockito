package com.matheuscordeiro.user;

public class DefaultUserService implements UserService {
    public DefaultUserService(UserRepository userRepository, SettingRepository settingRepository, MailClient mailClient) {
    }

    @Override
    public User register(User user) {
        return null;
    }
}
