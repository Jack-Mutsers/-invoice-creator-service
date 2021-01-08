package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.FileRecord;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRecordDTO {
    private int id = 0;
    private String name;
    private String fileName;
    private String url;
    private String fileType;
    private String creationDate;
    private int customerId;
    private CustomerDTO customer = new CustomerDTO();
    private int ownerId;
    private CompanyDTO owner = new CompanyDTO();

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

}
