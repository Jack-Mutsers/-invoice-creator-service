package com.example.invoicecreatorservice.controllers;

import com.example.invoicecreatorservice.contracts.services.StorageService;
import com.example.invoicecreatorservice.helpers.handlers.StorageFileNotFoundException;
import com.example.invoicecreatorservice.objects.data_transfer_objects.FileRecordDTO;
import com.example.invoicecreatorservice.objects.data_transfer_objects.ResponseDTO;
import com.example.invoicecreatorservice.objects.models.FileRecord;
import com.example.invoicecreatorservice.services.CompanyService;
import com.example.invoicecreatorservice.services.CustomerService;
import com.example.invoicecreatorservice.services.FileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/upload")
public class FileUploadController extends BaseController {

	@Autowired
	private final FileRecordService recordService = new FileRecordService();

	@Autowired
	private final CustomerService customerService = new CustomerService();

	@Autowired
	private final CompanyService companyService = new CompanyService();

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/send")
	public ResponseEntity<ResponseDTO> listMyUploadedFiles(HttpServletRequest request) {
		int companyId = super.getCompanyId(request);

		Iterable<FileRecordDTO> records = recordService.getAllMyFileRecords(companyId);

		for(FileRecordDTO record : records){
			record.setCustomer(customerService.getCustomer(record.getCustomerId()));
		}

		if(records == null){
			return new ResponseEntity<>(new ResponseDTO(false, "There are currently no companies available"), HttpStatus.OK);
		}

		return new ResponseEntity<>(new ResponseDTO(true, records), HttpStatus.OK);
	}

	@GetMapping("/received")
	public ResponseEntity<ResponseDTO> listUploadedFilesForMe(HttpServletRequest request) {
		int userId = super.getUserId(request);
		List<Integer> ids = customerService.getMyCustomerIds(userId);

		Iterable<FileRecordDTO> records = recordService.getAllSharedFileRecords(ids);

		for(FileRecordDTO record : records){
			record.setOwner(companyService.getCompany(record.getOwnerId()));
		}

		return new ResponseEntity<>(new ResponseDTO(true, records), HttpStatus.OK);
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Object> serveFile(HttpServletRequest request, @PathVariable String filename) {
		int companyId = super.getCompanyId(request);
		int userId = super.getUserId(request);
		List<Integer> ids = customerService.getMyCustomerIds(userId);

		// validate if requested file is for or from the request user
		boolean allowed = recordService.validateAccessPermission(filename, companyId, ids);

		if(!allowed){
			return new ResponseEntity<>(new ResponseDTO(false, "You do not have access to this file"), HttpStatus.FORBIDDEN);
		}

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().body(file);
	}

	@PostMapping("")
	public ResponseEntity<ResponseDTO> handleFileUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam("customerId") int customerId) {
		int companyId = super.getCompanyId(request);

		try{
			FileRecord record = storageService.store(file);
			record.setCustomerId(customerId);
			record.setOwnerId(companyId);

			recordService.createRecord(record);

			return new ResponseEntity<>(new ResponseDTO(true, new FileRecordDTO(record)), HttpStatus.OK);

		}catch (Exception ex){
			return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong while storing files"), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDTO> delete(HttpServletRequest request, @PathVariable("id") int id){
		int companyId = super.getCompanyId(request);

		FileRecord record = recordService.getFileRecord(id, companyId);

		if(record == null){
			return new ResponseEntity<>(new ResponseDTO(false, "File could not be found or you dont have permissions to access this file"), HttpStatus.FORBIDDEN);
		}

		if(!storageService.deleteFile(record.getFileName())){
			return new ResponseEntity<>(new ResponseDTO(false, "Something went wrong while deleting the file"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		recordService.deleteRecord(record);
		return new ResponseEntity<>(new ResponseDTO(true, "File has been deleted"), HttpStatus.OK);
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<ResponseDTO> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return new ResponseEntity<>(new ResponseDTO(false, "Requested file could not be found"), HttpStatus.NOT_FOUND);
	}

}
