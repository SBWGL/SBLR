package springboot.rl.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot.rl.model.User;
import springboot.rl.repository.UserRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌 때 username,password 변수 2개를 가로채는데
    // password 부분 처리는 알아서 함.
    // username이 DB에 있는지만 확인해주면 됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if (userEntity != null){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
