package com.example.invoicecreatorservice.objects.models;

import com.example.invoicecreatorservice.objects.data_transfer_objects.FileRecordDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class FileRecordTest {

    @Test
    void instantiateEntity(){
        int id = 1;
        String name = "testfile.pdf";
        String fileName = "testfile-16122020134026.pdf";
        String url = "company1/testfile-16122020134026.pdf";
        String fileType = "PDF";
        String creationDate = "16-12-2020";
        int customerId = 1;
        int companyId = 5;

        FileRecord entity = new FileRecord(
                id,
                name,
                fileName,
                url,
                fileType,
                creationDate,
                customerId,
                companyId
        );

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(fileName, entity.getFileName());
        assertEquals(url, entity.getUrl());
        assertEquals(fileType, entity.getFileType());
        assertEquals(creationDate, entity.getCreationDate());
        assertEquals(customerId, entity.getCustomerId());
        assertEquals(companyId, entity.getOwnerId());
    }

    @Test
    void instantiateEmptyEntity(){
        int id = 0;
        int customerId = 0;
        int companyId = 0;

        FileRecord entity = new FileRecord();

        assertEquals(id, entity.getId());
        assertNull(entity.getName());
        assertNull(entity.getFileName());
        assertNull(entity.getUrl());
        assertNull(entity.getFileType());
        assertNull(entity.getCreationDate());
        assertEquals(customerId, entity.getCustomerId());
        assertEquals(companyId, entity.getOwnerId());
    }

    @Test
    void instantiateEntityByDTO(){
        int id = 1;
        String name = "testfile.pdf";
        String fileName = "testfile-16122020134026.pdf";
        String url = "company1/testfile-16122020134026.pdf";
        String fileType = "PDF";
        String creationDate = "16-12-2020";
        int customerId = 1;
        int companyId = 5;

        FileRecordDTO DTOentity = new FileRecordDTO(
            new FileRecord(
                id,
                name,
                fileName,
                url,
                fileType,
                creationDate,
                customerId,
                companyId
            )
        );

        FileRecord entity = new FileRecord(DTOentity);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(fileName, entity.getFileName());
        assertEquals(url, entity.getUrl());
        assertEquals(fileType, entity.getFileType());
        assertEquals(creationDate, entity.getCreationDate());
        assertEquals(customerId, entity.getCustomerId());
        assertEquals(companyId, entity.getOwnerId());
    }

    @Test
    void fillEmptyEntity(){
        int id = 1;
        String name = "testfile.pdf";
        String fileName = "testfile-16122020134026.pdf";
        String url = "company1/testfile-16122020134026.pdf";
        String fileType = "PDF";
        String creationDate = "16-12-2020";
        int customerId = 1;
        int companyId = 5;

        FileRecord entity = new FileRecord();

        entity.setId(id);
        entity.setName(name);
        entity.setFileName(fileName);
        entity.setUrl(url);
        entity.setFileType(fileType);
        entity.setCreationDate(creationDate);
        entity.setCustomerId(customerId);
        entity.setOwnerId(companyId);

        assertEquals(id, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(fileName, entity.getFileName());
        assertEquals(url, entity.getUrl());
        assertEquals(fileType, entity.getFileType());
        assertEquals(creationDate, entity.getCreationDate());
        assertEquals(customerId, entity.getCustomerId());
        assertEquals(companyId, entity.getOwnerId());
    }

}
