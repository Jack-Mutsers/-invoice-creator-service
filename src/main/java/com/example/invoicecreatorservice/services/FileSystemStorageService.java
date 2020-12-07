package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.contracts.services.StorageService;
import com.example.invoicecreatorservice.helpers.handlers.StorageException;
import com.example.invoicecreatorservice.helpers.handlers.StorageFileNotFoundException;
import com.example.invoicecreatorservice.helpers.properties.StorageProperties;
import com.example.invoicecreatorservice.objects.models.FileRecord;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public FileRecord store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}

			Date currentDate = new Date();
			String formattedDate = new SimpleDateFormat("-yyyyMMddHHmms").format(new Date());

			String originalFilename = file.getOriginalFilename();

			if(originalFilename == null){
				return null;
			}

			String filename = originalFilename.replace(file.getOriginalFilename(), FilenameUtils.getBaseName(file.getOriginalFilename()).concat(formattedDate) + "." + FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase());
			filename = filename.replace(" ", "_");

			Path destinationFile = this.rootLocation.resolve(
					Paths.get(filename))
					.normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				// This is a security check
				throw new StorageException(
						"Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}

			return new FileRecord(
				0,
				originalFilename,
				filename,
				"http://localhost:9090/upload/files/" + filename,
				FilenameUtils.getExtension(file.getOriginalFilename()),
				new SimpleDateFormat("dd-MM-yyyy").format(currentDate),
				0,
				0
			);

		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
		catch (NullPointerException ex){
			throw new StorageException("Failed to store file.", ex);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1)
				.filter(path -> !path.equals(this.rootLocation))
				.map(this.rootLocation::relativize);
		}
		catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public boolean deleteFile(String filename) {
		Path path = this.load(filename);
		return path.toFile().delete();
	}

	@Override
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
}
