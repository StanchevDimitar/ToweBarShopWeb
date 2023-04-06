package bg.softuni.towebarshopweb.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Table(name = "files")
@Entity
public class FileEntity extends BaseEntity{

    private String fileName;

    private String contentType;

    @Lob
    @Column(length = Integer.MAX_VALUE)
    private byte[] data;


    public String getFileName() {
        return fileName;
    }

    public FileEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public FileEntity setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public byte[] getData() {
        return data;
    }

    public FileEntity setData(byte[] data) {
        this.data = data;
        return this;
    }
}
