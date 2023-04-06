package bg.softuni.towebarshopweb.service;

import bg.softuni.towebarshopweb.model.entity.FileEntity;
import bg.softuni.towebarshopweb.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileEntity saveFile(MultipartFile file) throws IOException {

        FileEntity fileEntity = new FileEntity().
                setFileName(file.getOriginalFilename()).
                setContentType(file.getContentType()).
                setData(file.getBytes());



        return fileRepository.save(fileEntity);
    }
}
