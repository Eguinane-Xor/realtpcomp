package pack.security;

import pack.usr.User;

public interface UserService {
    User findByUsername(String username);
}
