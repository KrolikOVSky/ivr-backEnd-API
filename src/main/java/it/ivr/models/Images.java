package it.ivr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Images {
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    private Long id;
    private String imageFileName;
    private Long uploadDate;

    public Images(Long id, String imageFileName, Long uploadDate) {
        this.id = id;
        this.imageFileName = imageFileName;
        this.uploadDate = uploadDate;
    }

    public Images() {
        this.id = 0L;
        this.imageFileName = "";
        this.uploadDate = 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public Long getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Long uploadDate) {
        this.uploadDate = uploadDate;
    }
}
