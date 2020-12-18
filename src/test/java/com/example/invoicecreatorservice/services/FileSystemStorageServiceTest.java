package com.example.invoicecreatorservice.services;

import com.example.invoicecreatorservice.helpers.handlers.StorageFileNotFoundException;
import com.example.invoicecreatorservice.helpers.properties.StorageProperties;
import com.example.invoicecreatorservice.objects.models.FileRecord;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileSystemStorageServiceTest {

    @Rule
    private TemporaryFolder folder = new TemporaryFolder();
    private FileSystemStorageService service;

    private String filePath = "";

    private FileSystemStorageServiceTest() throws IOException {
        folder.create();
        filePath = folder.getRoot().getAbsolutePath() + "/upload-dir";

        StorageProperties properties = new StorageProperties();
        properties.setLocation(filePath);

        service = new FileSystemStorageService(properties);
    }

    private FileRecord storeFile() throws ExecutionException, InterruptedException {
        String companyName = "demoCompany";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "hello.pdf",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        Future<FileRecord> asyncResponse = service.storeAsync(file, companyName);
        return asyncResponse.get();
    }

    @Test
    void initTest(){
        service.init();
        assertTrue(new File(filePath).exists());
    }

    @Test
    void storeAsyncTest() throws ExecutionException, InterruptedException {

        FileRecord record = this.storeFile();

        assertEquals("hello.pdf", record.getName());
        assertNotNull(record.getFileName());
        assertNotNull(record.getUrl());
        assertEquals("pdf", record.getFileType());
        assertNotNull(record.getCreationDate());
    }

    @Test
    void storeAsyncEmptyTest() throws ExecutionException, InterruptedException {

        FileRecord record = this.storeFile();

        assertEquals("hello.pdf", record.getName());
        assertNotNull(record.getFileName());
        assertNotNull(record.getUrl());
        assertEquals("pdf", record.getFileType());
        assertNotNull(record.getCreationDate());
    }

    @Test
    void loadTest() throws ExecutionException, InterruptedException {
        FileRecord record = this.storeFile();
        Path path = service.load(record.getFileName());

        assertTrue(path.toFile().exists());
    }

    @Test
    void loadAsResourceSuccessTest() throws ExecutionException, InterruptedException, MalformedURLException {
        FileRecord record = this.storeFile();

        Resource resource = service.loadAsResource(record.getFileName());

        assertNotNull(resource);
    }

    @Test
    void loadAsResourceFailureTest() {

        String filename = "blablabla.txt";

        Exception exception = assertThrows(StorageFileNotFoundException.class, () -> {
            service.loadAsResource(filename);
        });

        String expectedMessage = "Could not read file: " + filename;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void deleteAllByCompanyTest() throws ExecutionException, InterruptedException {
        FileRecord record = this.storeFile();
        String companyName = "demoCompany";

        service.deleteAllByCompany(companyName);

        Path path = service.load(record.getFileName());

        assertFalse(path.toFile().exists());
    }

    @Test
    void deleteFileAsyncTest() throws ExecutionException, InterruptedException {
        FileRecord record = this.storeFile();

        service.deleteFileAsync(record.getFileName());

        Path path = service.load(record.getFileName());

        assertFalse(path.toFile().exists());

    }
}
