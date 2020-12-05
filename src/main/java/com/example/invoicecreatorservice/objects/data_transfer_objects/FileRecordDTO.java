package com.example.invoicecreatorservice.objects.data_transfer_objects;


import com.example.invoicecreatorservice.objects.models.FileRecord;

public class FileRecordDTO {
    private int id = 0;
    private String name;
    private String fileName;
    private String url;
    private String fileType;
    private String creationDate;
    private int customerId;
    private int ownerId;

    public FileRecordDTO(int id, String name, String fileName, String url, String fileType, String creationDate, int customerId, int ownerId) {
        this.id = id;
        this.name = name;
        this.fileName = fileName;
        this.url = url;
        this.fileType = fileType;
        this.creationDate = creationDate;
        this.customerId = customerId;
        this.ownerId = ownerId;
    }

    public FileRecordDTO(FileRecord record) {
        this.id = record.getId();
        this.name = record.getName();
        this.fileName = record.getFileName();
        this.url = record.getUrl();
        this.fileType = record.getFileType();
        this.creationDate = record.getCreationDate();
        this.customerId = record.getCustomerId();
        this.ownerId = record.getOwnerId();
    }

    public FileRecordDTO(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }

    public String getUrl() {
        return url;
    }

    public String getFileType() {
        return fileType;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getOwnerId() {
        return ownerId;
    }
}
