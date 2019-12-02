package pack.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pack.usr.User;
import pack.usr.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        User user = null;
        try {
            user = userRepository.findByUsername(username);
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

}