package com.smatt.service;

import com.smatt.config.StorageProperties;
import com.smatt.exceptions.StorageException;
import com.smatt.exceptions.StorageFileNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by smatt on 19/04/2017.
 */
@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    Logger logger  = Logger.getLogger(FileSystemStorageService.class);

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());

    }

    @Override
    public String store(MultipartFile file) {
        try(InputStream in = file.getInputStream()) {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            String newFileName = RandomStringUtils.randomAlphanumeric(10) + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")) ;
            Files.copy(in, this.rootLocation.resolve(newFileName));
            return newFileName;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
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
            logger.info("file to Uri = " + file.toUri());
            logger.info("file exists = " + resource.exists());
            if(resource.exists() || resource.isReadable()) {

                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void delete(String filename) {
        try {
            Files.delete(load(filename));
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageFileNotFoundException("Unable to delete file " + filename + " NOT FOUND!");
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (java.nio.file.FileAlreadyExistsException e) {
            logger.debug("FileAlready exists");
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
