package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.models.FileRecord;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Future;

public interface StorageService {

	void init();

	Future<FileRecord> storeAsync(MultipartFile file, String companyName);

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAllByCompany(String companyName);

	Future<Boolean> deleteFileAsync(String filename) throws IOException;
}
