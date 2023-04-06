package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.entity.PriorityPhoneNumbers;
import bg.softuni.towebarshopweb.repository.PhoneRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PhoneService {



    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public void add(String number) {
        PriorityPhoneNumbers phone = new PriorityPhoneNumbers();
        phone.setNumber(number);
        phone.setInsertedTime(LocalDateTime.now());
        phoneRepository.save(phone);
    }
}
