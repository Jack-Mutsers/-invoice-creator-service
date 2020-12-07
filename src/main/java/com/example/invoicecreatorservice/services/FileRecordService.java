package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.helpers.logger.LoggerService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.FileRecordDTO;
import com.example.invoicecreatorservice.objects.models.FileRecord;
import com.example.invoicecreatorservice.repositories.FileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileRecordService {

    @Autowired
    private FileRepo repo;

    public FileRecord getFileRecord(int id, int ownerId){
        return repo.findByIdAndOwnerId(id, ownerId);
    }

    public Iterable<FileRecordDTO> getAllMyFileRecords(int ownerId){
        List<FileRecord> records = repo.findAllByOwnerId(ownerId);

        if(records.isEmpty()){ return null; }

        return this.convertListToDTO(records);
    }

    public Iterable<FileRecordDTO> getAllSharedFileRecords(List<Integer> ids){
        List<FileRecord> records = null;

        if(!ids.isEmpty()){
            records = repo.findAllByCustomerIdIn(ids);
        }

        if(records == null){
            records = new ArrayList<>();
        }

        return this.convertListToDTO(records);
    }

    public Boolean createRecord(FileRecord record) {
        try{
            // set id to 0 to prevent update of existing record on create
            record.setId(0);

            repo.save(record);

            return true;
        }catch (Exception ex){
             LoggerService.warn(ex.getMessage());
            return false;
        }
    }

    public boolean deleteRecord(FileRecord record){
        try{
            if(record == null){
                return false;
            }

            repo.delete(record);

            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean validateAccessPermission(String filename, int companyId, List<Integer> customerIds){
        FileRecord record = repo.findByFileName(filename);

        if(record == null){
            return false;
        }

        if(record.getOwnerId() == companyId){
            return true;
        }

        for(int id : customerIds){
            if(record.getCustomerId() == id){
                return true;
            }
        }

        return false;
    }

    private List<FileRecordDTO> convertListToDTO(List<FileRecord> list){
        List<FileRecordDTO> recordDTOS = new ArrayList<>();

        for(FileRecord record : list){
            recordDTOS.add(new FileRecordDTO(record));
        }

        return recordDTOS;
    }
}
