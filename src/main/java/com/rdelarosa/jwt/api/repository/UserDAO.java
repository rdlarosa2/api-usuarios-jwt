package com.rdelarosa.jwt.api.repository;

import java.util.List;
import com.rdelarosa.jwt.api.entity.Phone;
import com.rdelarosa.jwt.api.entity.User;

public interface UserDAO {
    User findByUserName(String username);
    User getUserById(Integer id);
    List<User> findAll();
    User save(User theUser);
    List<User> saveAll(List<User> theUser);

    List<Phone> getPhonesByUserId(Integer userId);
    void update(User theUser);
    void updatePhone(Phone thePhone);
    Phone savePhone(Phone thePhone);
}
