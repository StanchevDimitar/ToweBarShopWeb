package bg.softuni.towebarshopweb.model.dto;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadDto {

    @Nullable
    private MultipartFile img;

    public MultipartFile getImg() {
        return img;
    }

    public FileUploadDto setImg(MultipartFile img) {
        this.img = img;
        return this;
    }
}
