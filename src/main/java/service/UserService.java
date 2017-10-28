package service;

import com.freelancer.backend.model.User;

/**
 * Created by AdamR on 2017-10-28.
 */


public interface UserService {

    public User findUserByEmail(String email);
    public void saveUser(User user);


}
