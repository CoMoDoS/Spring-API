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
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
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
        User user = new User();
        user.setEmail(caregiverDTO.getEmail());
        user.setPassword(caregiverPasswordEncoder().encode(caregiverDTO.getPassword()));
        user.setStatus("activ");
        user.setType("CAREGIVER");
        user = userRepository.save(user);
        if(user != null ){
            Caregiver caregiver = new Caregiver();
            caregiver.setName(caregiverDTO.getName());
            caregiver.setGender(caregiverDTO.getGender());
            caregiver.setBirthdate(caregiverDTO.getBirthdate());
            caregiver.setAddress(caregiverDTO.getAddress());
            caregiver.setStatus("activ");
            caregiver.setUser(user);
            caregiver = caregiverRepository.save(caregiver);
            return new CaregiverDTO(caregiver);
        }else {
            return null;
        }
    }

    public CaregiverDTO get(int id){
        Caregiver caregiver = caregiverRepository.findById(id);
        if( caregiver != null )
            return new CaregiverDTO(caregiver);
        else
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName());
    }

    public List<CaregiverDTO> getAll(){
        List<CaregiverDTO> response = new ArrayList<>();
        List<Caregiver> caregivers = caregiverRepository.findAll();
        for (Caregiver c : caregivers) {
            response.add(new CaregiverDTO(c));
        }
        return response;
    }

    public CaregiverDTO update(int id, CaregiverDTO caregiverDTO){
        Caregiver caregiver = caregiverRepository.findById(id);
        if(caregiver != null) {
            caregiver.setName(caregiverDTO.getName());
            caregiver.setStatus(caregiverDTO.getStatus());
            caregiver.setAddress(caregiverDTO.getAddress());
            caregiver.setBirthdate(caregiverDTO.getBirthdate());
            caregiver.setGender(caregiverDTO.getGender());
            Optional<User> user = userRepository.findById(caregiverDTO.getUser_id());
            if (user.isPresent())
                caregiver.setUser(user.get());
            caregiver = caregiverRepository.save(caregiver);
            return new CaregiverDTO(caregiver);
        }
        else
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName());

    }

    public int delete(int id){
       Caregiver caregiver = caregiverRepository.findById(id);
       if(caregiver != null){
           caregiverRepository.delete(caregiver);
           return id;
       }
       else
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName());
    }

    public CaregiverDTO getByUserId(int id){
        Optional<User> u = userRepository.findById(id);
        if(u.isPresent()){
            User user = u.get();
            Caregiver caregiver = caregiverRepository.findByUserId(user.getId());
            return new CaregiverDTO(caregiver);
        } else {
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName());
        }
    }

}
