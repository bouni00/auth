package config;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import service.InMemoryUserDetailsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ProjectConfig{

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails u = new User("john","12345","read");
        List<UserDetails> users = List.of(u);
        return new InMemoryUserDetailsService(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        Map<String,PasswordEncoder> encoders = new HashMap<>();

        encoders.put("noop",NoOpPasswordEncoder.getInstance());
        encoders.put("bcrypt",new BCryptPasswordEncoder());
//        encoders.put("scrypt",new SCryptPasswordEncoder());
        return new DelegatingPasswordEncoder("bcrypt",encoders);
    }
}
