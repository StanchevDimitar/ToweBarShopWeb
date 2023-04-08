package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.entity.PriorityPhoneNumbers;
import bg.softuni.towebarshopweb.repository.PhoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {

    private PhoneService phoneService;

    @Mock
    private PhoneRepository phoneRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        phoneService = new PhoneService(phoneRepository);
    }

    @Test
    public void testAddPhone() {
        String number = "123456789";
        PriorityPhoneNumbers phone = new PriorityPhoneNumbers();
        phone.setNumber(number);
        phone.setInsertedTime(LocalDateTime.now());

        when(phoneRepository.save(any(PriorityPhoneNumbers.class))).thenAnswer(new Answer<PriorityPhoneNumbers>() {
            @Override
            public PriorityPhoneNumbers answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                PriorityPhoneNumbers phone = (PriorityPhoneNumbers) args[0];
                phone.setId(1L);
                return phone;
            }
        });

        phoneService.add(number);

        verify(phoneRepository, times(1)).save(any(PriorityPhoneNumbers.class));
    }
}
