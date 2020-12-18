package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.FileRecordDTO;
import com.example.invoicecreatorservice.objects.models.FileRecord;
import com.example.invoicecreatorservice.repositories.FileRecordRepo;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class FileRecordServiceTest {
    @Mock
    FileRecordRepo repo;

    @InjectMocks
    FileRecordService service;

    private final List<FileRecord> entityList = new ArrayList<>();

    private FileRecordServiceTest(){
        entityList.add(new FileRecord(1,"testfile.pdf","testfile-25082020134026.pdf","company5/testfile-25082020134026.pdf","PDF","25-08-2020",1,5));
        entityList.add(new FileRecord(2,"testfile2.pdf","testfile2-25082020132605.pdf","company5/testfile2-25082020132605.pdf","PDF","25-08-2020",1,5));
        entityList.add(new FileRecord(3,"testfile3.pdf","testfile3-27082020132605.pdf","company2/testfile3-27082020132605.pdf","PDF","27-08-2020",5,2));
        entityList.add(new FileRecord(4,"testfile4.pdf","testfile4-07112020132605.pdf","company1/testfile4-07112020132605.pdf","PDF","07-11-2020",10,1));
        entityList.add(new FileRecord(5,"testfile5.pdf","testfile5-16122020132605.pdf","company5/testfile5-16122020132605.pdf","PDF","16-12-2020",3,5));
    }

    @Test
    void getFileRecordTest(){
        //Arrange
        FileRecord entity = this.entityList.get(4);

        //Prepare overwrites
        when(repo.findById(entity.getId())).thenReturn(entity);

        //Act
        FileRecord resultEntity = service.getFileRecord(entity.getId());

        //Assert
        assertEquals(entity.getId(), resultEntity.getId());
        assertEquals(entity.getName(), resultEntity.getName());
        assertEquals(entity.getFileName(), resultEntity.getFileName());
        assertEquals(entity.getUrl(), resultEntity.getUrl());
        assertEquals(entity.getFileType(), resultEntity.getFileType());
        assertEquals(entity.getCreationDate(), resultEntity.getCreationDate());
        assertEquals(entity.getCustomerId(), resultEntity.getCustomerId());
        assertEquals(entity.getOwnerId(), resultEntity.getOwnerId());
    }

    @Test
    void getFileRecordIdZeroTest(){
        //Arrange
        int id = 0;

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(null);

        //Act
        FileRecord resultEntity = service.getFileRecord(id);

        //Assert
        assertNull(resultEntity);
    }

    @Test
    void getFileRecordWithOwnerTest(){
        //Arrange
        FileRecord entity = this.entityList.get(4);

        //Prepare overwrites
        when(repo.findByIdAndOwnerId(entity.getId(), entity.getOwnerId())).thenReturn(entity);

        //Act
        FileRecord resultEntity = service.getFileRecord(entity.getId(), entity.getOwnerId());

        //Assert
        assertEquals(entity.getId(), resultEntity.getId());
        assertEquals(entity.getName(), resultEntity.getName());
        assertEquals(entity.getFileName(), resultEntity.getFileName());
        assertEquals(entity.getUrl(), resultEntity.getUrl());
        assertEquals(entity.getFileType(), resultEntity.getFileType());
        assertEquals(entity.getCreationDate(), resultEntity.getCreationDate());
        assertEquals(entity.getCustomerId(), resultEntity.getCustomerId());
        assertEquals(entity.getOwnerId(), resultEntity.getOwnerId());
    }

    @Test
    void getFileRecordWithOwnerIdZeroTest(){
        //Arrange
        int id = 0;

        //Prepare overwrites
        when(repo.findById(id)).thenReturn(null);

        //Act
        FileRecord resultEntity = service.getFileRecord(id, id);

        //Assert
        assertNull(resultEntity);
    }

    @Test
    void getAllMyFileRecordsTest(){
        //Arrange
        int ownerId = 5;
        List<FileRecord> recordList = new ArrayList<>();
        recordList.add(this.entityList.get(0));
        recordList.add(this.entityList.get(1));
        recordList.add(this.entityList.get(4));

        //Prepare overwrites
        when(repo.findAllByOwnerId(ownerId)).thenReturn(recordList);

        //Act
        List<FileRecordDTO> result = Lists.newArrayList(service.getAllMyFileRecords(ownerId));

        //Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(recordList.get(0).getName(), result.get(0).getName());
        assertEquals(recordList.get(1).getName(), result.get(1).getName());
        assertEquals(recordList.get(2).getName(), result.get(2).getName());
    }

    @Test
    void getAllMyFileRecordsNoRecordsTest(){
        //Arrange
        int ownerId = 50;

        //Prepare overwrites
        when(repo.findAllByOwnerId(ownerId)).thenReturn(null);

        //Act
        List<FileRecordDTO> result = Lists.newArrayList(service.getAllMyFileRecords(ownerId));

        //Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void getAllSharedFileRecordsTest(){
        //Arrange
        List<FileRecord> expectedResult = new ArrayList<>();
        expectedResult.add(entityList.get(0));
        expectedResult.add(entityList.get(1));
        expectedResult.add(entityList.get(3));

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(10);

        //Prepare overwrites
        when(repo.findAllByCustomerIdIn(ids)).thenReturn(expectedResult);

        //Act
        List<FileRecordDTO> result = Lists.newArrayList(service.getAllSharedFileRecords(ids));

        //Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(expectedResult.get(0).getName(), result.get(0).getName());
        assertEquals(expectedResult.get(1).getName(), result.get(1).getName());
        assertEquals(expectedResult.get(2).getName(), result.get(2).getName());
    }

    @Test
    void getAllSharedFileRecordsNoIdsTest(){
        //Arrange
        List<Integer> ids = new ArrayList<>();

        //Prepare overwrites
        when(repo.findAllByCustomerIdIn(ids)).thenReturn(null);

        //Act
        List<FileRecordDTO> result = Lists.newArrayList(service.getAllSharedFileRecords(ids));

        //Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void getAllSharedFileRecordsNoRecordsTest(){
        //Arrange
        List<Integer> ids = new ArrayList<>();
        ids.add(20);

        //Prepare overwrites
        when(repo.findAllByCustomerIdIn(ids)).thenReturn(null);

        //Act
        List<FileRecordDTO> result = Lists.newArrayList(service.getAllSharedFileRecords(ids));

        //Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void createRecordTest() {
        //Arrange
        FileRecord record = entityList.get(2);
        record.setId(6);

        //Prepare overwrites
        when(repo.save(record)).thenReturn(record);

        //Act
        boolean result = service.createRecord(record);

        //Assert
        assertTrue(result);

    }

    @Test
    void createRecordExceptionTest() {
        //Arrange
        FileRecord record = null;

        //Prepare overwrites

        //Act
        boolean result = service.createRecord(record);

        //Assert
        assertFalse(result);
    }

    @Test
    void deleteRecordTest(){
        //Arrange
        FileRecord record = entityList.get(3);

        //Prepare overwrites

        //Act
        boolean result = service.deleteRecord(record);

        //Assert
        assertTrue(result);

    }

    @Test
    void deleteRecordNullTest(){
        //Arrange
        FileRecord record = entityList.get(3);

        //Prepare overwrites

        //Act
        boolean result = service.deleteRecord(null);

        //Assert
        assertFalse(result);

    }

    @Test
    void deleteRecordExceptionTest(){
        //Arrange
        FileRecord record = new FileRecord(20, "blabla.txt", "hello/blabla.txt", "http:blabla.com", "txt", "18-12-2020", 50, 60 );

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(20)).when(repo).delete(record);

        //Act
        boolean result = service.deleteRecord(record);

        //Assert
        assertFalse(result);
    }

    @Test
    void deleteCompanyRecordsTest(){
        //Arrange
        int companyId = 5;

        //Prepare overwrites

        //Act
        boolean result = service.deleteCompanyRecords(companyId);

        //Assert
        assertTrue(result);
    }

    @Test
    void deleteCompanyRecordsExceptionTest(){
        //Arrange
        int companyId = 5;

        //Prepare overwrites
        doThrow(new EmptyResultDataAccessException(5)).when(repo).deleteAllByOwnerId(companyId);

        //Act
        boolean result = service.deleteCompanyRecords(companyId);

        //Assert
        assertFalse(result);
    }

    @Test
    void validateAccessPermissionCompanyTest(){
        //Arrange
        FileRecord record = entityList.get(2);
        int companyId = 2;
        List<Integer> customerIds = new ArrayList<>();
        customerIds.add(1);
        customerIds.add(7);
        customerIds.add(5);
        customerIds.add(3);

        //Prepare overwrites

        //Act
        boolean result = service.validateAccessPermission(record, companyId, customerIds);

        //Assert
        assertTrue(result);

    }

    @Test
    void validateAccessPermissionNullRecordTest(){
        //Arrange
        FileRecord record = null;
        int companyId = 5;
        List<Integer> customerIds = new ArrayList<>();
        customerIds.add(1);
        customerIds.add(7);
        customerIds.add(5);
        customerIds.add(3);

        //Prepare overwrites

        //Act
        boolean result = service.validateAccessPermission(record, companyId, customerIds);

        //Assert
        assertFalse(result);

    }

    @Test
    void validateAccessPermissionCustomerTest(){
        //Arrange
        FileRecord record = entityList.get(2);
        int companyId = 5;
        List<Integer> customerIds = new ArrayList<>();
        customerIds.add(1);
        customerIds.add(7);
        customerIds.add(5);
        customerIds.add(3);

        //Prepare overwrites

        //Act
        boolean result = service.validateAccessPermission(record, companyId, customerIds);

        //Assert
        assertTrue(result);

    }

    @Test
    void validateAccessPermissionNoPermissionTest(){
        //Arrange
        FileRecord record = entityList.get(2);
        int companyId = 5;
        List<Integer> customerIds = new ArrayList<>();
        customerIds.add(1);
        customerIds.add(7);
        customerIds.add(2);
        customerIds.add(3);

        //Prepare overwrites

        //Act
        boolean result = service.validateAccessPermission(record, companyId, customerIds);

        //Assert
        assertFalse(result);

    }

}
