package com.example.health.services;

import com.example.health.entities.Hospital;
import com.example.health.entities.User;
import com.example.health.exceptions.ResourceNotFoundException;
import com.example.health.repositories.HospitalRepository;
import com.example.health.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private HospitalRepository hospitalRepository;
    //FIXME
    // daca vreau sa scot un spital de la un user care in adm spitalul il scot de aici sau din hospital?
@Transactional
    public boolean removeHospitalFromUser (Long userId, Long hospitalId) {
        User user = userRepository.findUsersById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Hospital hospital = hospitalRepository.findById(hospitalId).orElseThrow(() -> new ResourceNotFoundException("Hospital not found"));

        if (user == null || hospital == null) {
           return false;
        }
        if (user.getHospitals().contains(hospital)) {
            hospital.setUser(null);
            user.getHospitals().remove(hospital);
            userRepository.save(user);
            hospitalRepository.save(hospital);
            return true;
        }
        return false;
    }
}
