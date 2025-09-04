package com.victor.documenttracker.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private Long fileSize;

    @ManyToOne
    @JoinColumn(name = "uploaded_by", nullable = false)
    private User uploadedBy;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime uploadedAt;

    @Column(nullable = false)
    private String category;


    public Document() {}

    public Document(String filename, String originalName, Long fileSize, User uploadedBy, String status, LocalDateTime uploadedAt, String category){
        this.filename = filename;
        this.originalName = originalName;
        this.fileSize = fileSize;
        this.uploadedBy = uploadedBy;
        this.uploadedAt = uploadedAt;
        this.status = status;
        this.category = category;
    }

    //getters setters
    public Long getId(){
        return id;
    }

    public String getFilename(){
        return filename;
    }

    public void setFilename(String filename){
        this.filename = filename;
    }

    public String getOriginalName(){
        return originalName;
    }

    public void setOriginalName(String originalName){
        this.originalName = originalName;
    }

    public Long getFileSize(){
        return fileSize;
    }

    public void setFileSize(Long fileSize){
        this.fileSize = fileSize;
    }

    public User getUploadedBy(){
        return uploadedBy;
    }

    public void setUploadedBy(User uploadedBy){
        this.uploadedBy = uploadedBy;
    }

    public LocalDateTime getUploadedAt(){
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt){
        this.uploadedAt = uploadedAt;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

}
