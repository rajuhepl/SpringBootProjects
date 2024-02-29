package com.filehandling.FileHandling.Repository;

import com.filehandling.FileHandling.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
