package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.contracts.services.StorageService;
import com.example.invoicecreatorservice.helpers.handlers.StorageException;
import com.example.invoicecreatorservice.helpers.handlers.StorageFileNotFoundException;
import com.example.invoicecreatorservice.helpers.properties.StorageProperties;
import com.example.invoicecreatorservice.helpers.util.AsyncResponse;
import com.example.invoicecreatorservice.objects.models.FileRecord;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

import static com.example.invoicecreatorservice.helpers.tools.Helper.validateStringValue;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() && resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);
			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	public void deleteAllByCompany(String companyName) {
		Path fileLocation = Paths.get(this.rootLocation.toString() + "/" + companyName);
		FileSystemUtils.deleteRecursively(fileLocation.toFile());
	}

	@Async
	public Future<Boolean> deleteFileAsync(String filename) {
		AsyncResponse<Boolean> response = new AsyncResponse<>();

		try{
			Path path = this.load(filename);
			Files.delete(path);

			response.complete(true);
			return response;
		}catch (Exception ex){
			response.complete(false);
			return response;
		}

	}

	public void init() {
		try {
			if (!Files.exists(rootLocation)) {
				Files.createDirectories(rootLocation);
			}
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	@Async
	public Future<FileRecord> storeAsync(MultipartFile file, String companyName) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}

			Date currentDate = new Date();
			String formattedDate = new SimpleDateFormat("-yyyyMMddHHmms").format(new Date());

			String originalFilename = file.getOriginalFilename();

			if(validateStringValue(originalFilename)){
				return null;
			}

			File theDir = new File(this.rootLocation + "/" + companyName);
			if (!theDir.exists()){
				theDir.mkdirs();
			}

			String filename = originalFilename.replace(file.getOriginalFilename(), FilenameUtils.getBaseName(file.getOriginalFilename()).concat(formattedDate) + "." + FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase());
			filename = companyName + "/" + filename.replace(" ", "_");

			Path destinationFile = this.rootLocation.resolve(
					Paths.get(filename))
					.normalize().toAbsolutePath();

			if (!destinationFile.getParent().getParent().equals(this.rootLocation.toAbsolutePath())) {
				// This is a security check
				throw new StorageException(
						"Cannot store file outside current directory.");
			}

			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
						StandardCopyOption.REPLACE_EXISTING);
			}

			AsyncResponse<FileRecord> response = new AsyncResponse<>();
			response.complete(new FileRecord(
					0,
					originalFilename,
					filename,
					"http://localhost:9090/upload/files/" + filename,
					FilenameUtils.getExtension(file.getOriginalFilename()),
					new SimpleDateFormat("dd-MM-yyyy").format(currentDate),
					0,
					0
			));

			return response;
		}
		catch (StorageException ex){
			throw new StorageException(ex.getMessage());
		}
		catch (Exception e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

}
