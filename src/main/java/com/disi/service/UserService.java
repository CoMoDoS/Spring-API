package com.disi.service;
import com.disi.errorHandler.EntityValidationException;
import com.disi.errorHandler.ResourceNotFoundException;
import com.disi.models.*;
import com.disi.repository.DoctorRepository;
import com.disi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found"));
        return user
                .map(CustomUserDetails::new).get();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User addUser(User user){

        validateUser(user);
        findUserByEmail(user.getEmail());
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }

    public User getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
            return user.get();
        else
            throw new ResourceNotFoundException(User.class.getSimpleName());
    }



    private void findUserByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
            List<String> errors = new ArrayList<String>();
            errors.add("This email already exists");
            throw new EntityValidationException(User.class.getSimpleName(), errors);
        }
    }

    private void validateUser(User user){
        List<String> errors = new ArrayList<String>();

        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        if (user.getStatus() == null || user.getStatus().equals("")) {
            errors.add("Name field should not be empty");
        }

        if (user.getPassword() == null || user.getPassword().equals("")) {
            errors.add("Password field should not be empty");
        }

        if(user.getEmail() != null) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmail());
            if (!matcher.find())
                errors.add("Email field is not valid");
        }

        if (user.getEmail() == null || user.getEmail().equals("")) {
            errors.add("Email field is not valid");
        }

        if (!errors.isEmpty()) {
            throw new EntityValidationException(User.class.getSimpleName(),errors);
        }
    }

    public void createAdmin(User user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));

        user = userRepository.save(user);
        Doctor doctor = new Doctor("DEFAULT", user, "DEFAULT");
        doctorRepository.save(doctor);
    }

}
