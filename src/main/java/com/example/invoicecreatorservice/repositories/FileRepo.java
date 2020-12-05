package com.example.invoicecreatorservice.repositories;

import com.example.invoicecreatorservice.objects.models.FileRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FileRepo extends CrudRepository<FileRecord, Integer> {
    FileRecord findById(int id);
    List<FileRecord> findAllByCustomerIdIn(List<Integer> inventoryIdList);
    List<FileRecord> findAllByCustomerId(int customerId);
    FileRecord findByIdAndOwnerId(int id, int ownerId);
    List<FileRecord> findAllByOwnerId(int ownerId);
    FileRecord findByFileName(String fileName);
}
