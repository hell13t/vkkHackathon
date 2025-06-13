package com.example.vkHackathon.repository;

import com.example.vkHackathon.entity.File;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File, Long> {
}
