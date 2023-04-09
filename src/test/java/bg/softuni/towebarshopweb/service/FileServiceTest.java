package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.entity.FileEntity;
import bg.softuni.towebarshopweb.repository.FileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {

    private FileService fileService;

    @Mock
    private FileRepository fileRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        fileService = new FileService(fileRepository);
    }


}
