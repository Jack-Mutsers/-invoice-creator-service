package com.example.invoicecreatorservice.objects.data_transfer_objects;

import com.example.invoicecreatorservice.objects.models.FileRecord;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileRecordDTOTest {

    private final FileRecord entity = new FileRecord(1,"testfile.pdf", "testfile-16122020134026.pdf","company1/testfile-16122020134026.pdf","PDF","16-12-2020",1, 5);

    @Test
    void instantiateEntity(){

        FileRecordDTO DTOentity = new FileRecordDTO(entity);

        assertEquals(entity.getId(), DTOentity.getId());
        assertEquals(entity.getName(), DTOentity.getName());
        assertEquals(entity.getFileName(), DTOentity.getFileName());
        assertEquals(entity.getUrl(), DTOentity.getUrl());
        assertEquals(entity.getFileType(), DTOentity.getFileType());
        assertEquals(entity.getCreationDate(), DTOentity.getCreationDate());
        assertEquals(entity.getCustomerId(), DTOentity.getCustomerId());
        assertEquals(entity.getOwnerId(), DTOentity.getOwnerId());
    }

    @Test
    void instantiateEmptyEntity(){

        FileRecordDTO DTOentity = new FileRecordDTO();

        assertEquals(0, DTOentity.getId());
        assertNull(DTOentity.getName());
        assertNull(DTOentity.getFileName());
        assertNull(DTOentity.getUrl());
        assertNull(DTOentity.getFileType());
        assertNull(DTOentity.getCreationDate());
        assertEquals(0, DTOentity.getCustomerId());
        assertEquals(0, DTOentity.getOwnerId());
    }

    @Test
    void fillEmptyEntity(){

        FileRecordDTO DTOentity = new FileRecordDTO();

        DTOentity.setId(entity.getId());
        DTOentity.setName(entity.getName());
        DTOentity.setFileName(entity.getFileName());
        DTOentity.setUrl(entity.getUrl());
        DTOentity.setFileType(entity.getFileType());
        DTOentity.setCreationDate(entity.getCreationDate());
        DTOentity.setCustomerId(entity.getCustomerId());
        DTOentity.setCustomer(new CustomerDTO());
        DTOentity.setOwnerId(entity.getOwnerId());
        DTOentity.setOwner(new CompanyDTO());

        assertEquals(entity.getId(), DTOentity.getId());
        assertEquals(entity.getName(), DTOentity.getName());
        assertEquals(entity.getFileName(), DTOentity.getFileName());
        assertEquals(entity.getUrl(), DTOentity.getUrl());
        assertEquals(entity.getFileType(), DTOentity.getFileType());
        assertEquals(entity.getCreationDate(), DTOentity.getCreationDate());
        assertEquals(entity.getCustomerId(), DTOentity.getCustomerId());
        assertEquals(entity.getOwnerId(), DTOentity.getOwnerId());
        assertNotNull(DTOentity.getCustomer());
        assertNotNull(DTOentity.getOwner());
    }
}
