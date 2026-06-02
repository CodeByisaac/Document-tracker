package com.victor.documenttracker.model;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
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
    private String path;

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

    public Document(String filename, String originalName, String path, User uploadedBy, String status, LocalDateTime uploadedAt, String category){
        this.filename = filename;
        this.originalName = originalName;
        this.path = path;
        this.uploadedBy = uploadedBy;
        this.uploadedAt = uploadedAt;
        this.status = status;
        this.category = category;
    }
}
