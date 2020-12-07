package com.example.invoicecreatorservice.contracts.services;

import com.example.invoicecreatorservice.objects.models.FileRecord;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	FileRecord store(MultipartFile file);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

	boolean deleteFile(String filename);
}
