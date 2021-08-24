package springboot.rl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.rl.model.RoleType;
import springboot.rl.model.User;
import springboot.rl.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public User findUser(String username){
        User user = userRepository.findByUsername(username).orElseGet(() -> {
            return new User();
        });
        return user;
    }

    @Transactional
    public void save(User user){
        String encode = encoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

}
