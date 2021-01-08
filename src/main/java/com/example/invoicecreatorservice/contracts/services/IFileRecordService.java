package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.data_transfer_objects.FileRecordDTO;
import com.example.invoicecreatorservice.objects.models.FileRecord;

import java.util.List;

public interface IFileRecordService {
    FileRecord getFileRecord(int id);
    FileRecord getFileRecord(int id, int ownerId);
    Iterable<FileRecordDTO> getAllMyFileRecords(int ownerId);
    Iterable<FileRecordDTO> getAllSharedFileRecords(List<Integer> ids);
    Boolean createRecord(FileRecord record) ;
    boolean deleteRecord(FileRecord record);
    boolean deleteCompanyRecords(int companyId);
    boolean validateAccessPermission(FileRecord record, int companyId, List<Integer> customerIds);
}