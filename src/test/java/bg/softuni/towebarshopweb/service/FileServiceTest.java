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

    @Test
    public void testSaveFile() throws IOException {
        // Arrange
        String fileName = "test-file.txt";
        byte[] data = "test data".getBytes();
        String contentType = "text/plain";
        MultipartFile file = new MockMultipartFile(fileName, fileName, contentType, data);

        FileEntity fileEntity = new FileEntity().setFileName(fileName).setContentType(contentType).setData(data);

        Mockito.when(fileRepository.save(fileEntity)).thenReturn(fileEntity);

        // Act
        FileEntity savedFile = fileService.saveFile(file);

        // Assert
        Assertions.assertNotNull(savedFile, "The saved file should not be null");
        Assertions.assertEquals(fileName, savedFile.getFileName(), "The file name should match");
        Assertions.assertEquals(contentType, savedFile.getContentType(), "The content type should match");
        Assertions.assertArrayEquals(data, savedFile.getData(), "The file data should match");
        Mockito.verify(fileRepository, Mockito.times(1)).save(fileEntity);
    }
}
