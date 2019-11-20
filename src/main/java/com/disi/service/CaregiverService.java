package com.disi.service;

import com.disi.dto.CaregiverDTO;
import com.disi.errorHandler.ResourceNotFoundException;
import com.disi.models.Caregiver;
import com.disi.models.User;
import com.disi.repository.CaregiverRepository;
import com.disi.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CaregiverService {
    @Autowired
    private CaregiverRepository caregiverRepository;
    @Autowired
    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder caregiverPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public CaregiverDTO add(CaregiverDTO caregiverDTO){
        CaregiverDTO response = new CaregiverDTO();
        User auxUser = new User();
        auxUser.setEmail(caregiverDTO.getEmail());
        auxUser.setPassword(caregiverPasswordEncoder().encode(caregiverDTO.getPassword()));
        auxUser.setStatus("activ");
        auxUser.setType("CAREGIVER");
        User caregiverUser = userRepository.save(auxUser);
        if(caregiverUser != null ){
            Caregiver caregiver = new Caregiver();
            caregiver.setName(caregiverDTO.getName());
            caregiver.setGender(caregiverDTO.getGender());
            caregiver.setBirthdate(caregiverDTO.getBirthdate());
            caregiver.setAddress(caregiverDTO.getAddress());
            caregiver.setStatus("activ");
            caregiver.setUser(caregiverUser);
            caregiver = caregiverRepository.save(caregiver);
            response = createDTO(caregiverUser,caregiver);
            return response;
        }else {
            return null;
        }

    }
    public CaregiverDTO get(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            Caregiver caregiver = caregiverRepository.findByUserId(user.get().getId());

            return createDTO(user.get(),caregiver);
        }else{
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName());
        }
    }
    public List<CaregiverDTO> getAll(){
        List<User> users = userRepository.findAllByType("CAREGIVER");
        List<CaregiverDTO> response = new ArrayList<>();
        for (User u:users) {
            Caregiver caregiver = caregiverRepository.findByUserId(u.getId());
            response.add(createDTO(u,caregiver));
        }
        return response;
    }
    public CaregiverDTO update(int id, CaregiverDTO caregiverDTO){
        CaregiverDTO response = new CaregiverDTO();
        Optional<User> auxUser = userRepository.findById(id);
        if(auxUser.isPresent()){
            User user = new User();
            user.setId(auxUser.get().getId());
            user.setEmail(caregiverDTO.getEmail());
            user.setPassword(caregiverPasswordEncoder().encode(caregiverDTO.getPassword()));
            user.setType(caregiverDTO.getType());
            user.setStatus(caregiverDTO.getStatus());
            user = userRepository.save(user);
            Caregiver caregiver = caregiverRepository.findByUserId(user.getId());
            caregiver.setAddress(caregiverDTO.getAddress());
            caregiver.setBirthdate(caregiverDTO.getBirthdate());
            caregiver.setGender(caregiverDTO.getGender());
            caregiver.setName(caregiverDTO.getName());
            caregiver = caregiverRepository.save(caregiver);
            return createDTO(user,caregiver);

        }else{
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName());
        }
    }
    public int delete(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            Caregiver caregiver = caregiverRepository.findByUserId(id);
            caregiverRepository.delete(caregiver);
            userRepository.delete(user.get());
            return id;
        }else{
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName());
        }

    }

    private CaregiverDTO createDTO(User user, Caregiver caregiver){
        CaregiverDTO response = new CaregiverDTO();
        response.setId(caregiver.getId());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setType(user.getType());
        response.setStatus(user.getStatus());
        response.setName(caregiver.getName());
        response.setBirthdate(caregiver.getBirthdate());
        response.setGender(caregiver.getGender());
        response.setAddress(caregiver.getAddress());
        response.setUser_id(user.getId());
        return response;
    }
}
