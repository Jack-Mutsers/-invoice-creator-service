package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.FileRecordDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FileRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String fileName;
    private String url;
    private String fileType;
    private String creationDate;
    private int customerId;
    private int ownerId;

    public FileRecord(int id, String name, String fileName, String url, String fileType, String creationDate, int customerId, int ownerId) {
        this.id = id;
        this.name = name;
        this.fileName = fileName;
        this.url = url;
        this.fileType = fileType;
        this.creationDate = creationDate;
        this.customerId = customerId;
        this.ownerId = ownerId;
    }

    public FileRecord(FileRecordDTO record) {
        this.id = record.getId();
        this.name = record.getName();
        this.fileName = record.getFileName();
        this.url = record.getUrl();
        this.fileType = record.getFileType();
        this.creationDate = record.getCreationDate();
        this.customerId = record.getCustomerId();
        this.ownerId = record.getOwnerId();
    }

    public FileRecord(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String filename) {
        this.fileName = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
