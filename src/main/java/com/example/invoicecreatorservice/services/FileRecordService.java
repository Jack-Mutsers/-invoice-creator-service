package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.contracts.services.IFileRecordService;
import com.example.invoicecreatorservice.helpers.logger.LoggerService;
import com.example.invoicecreatorservice.objects.data_transfer_objects.FileRecordDTO;
import com.example.invoicecreatorservice.objects.models.FileRecord;
import com.example.invoicecreatorservice.repositories.FileRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileRecordService implements IFileRecordService {

    @Autowired
    private FileRecordRepo repo;

    public FileRecord getFileRecord(int id){
        return repo.findById(id);
    }

    public FileRecord getFileRecord(int id, int ownerId){
        return repo.findByIdAndOwnerId(id, ownerId);
    }

    public Iterable<FileRecordDTO> getAllMyFileRecords(int ownerId){
        List<FileRecord> records = repo.findAllByOwnerId(ownerId);

        return this.convertListToDTO(records);
    }

    public Iterable<FileRecordDTO> getAllSharedFileRecords(List<Integer> ids){
        List<FileRecord> records = new ArrayList<>();

        if(!ids.isEmpty()){
            records = repo.findAllByCustomerIdIn(ids);
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

    @Transactional
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

    @Transactional
    public boolean deleteCompanyRecords(int companyId){
        try{
            repo.deleteAllByOwnerId(companyId);

            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean validateAccessPermission(FileRecord record, int companyId, List<Integer> customerIds){
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

        for(FileRecord record : emptyIfNull(list)){
            recordDTOS.add(new FileRecordDTO(record));
        }

        return recordDTOS;
    }

    protected  <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
        return iterable == null ? List.of() : iterable;
    }
}
