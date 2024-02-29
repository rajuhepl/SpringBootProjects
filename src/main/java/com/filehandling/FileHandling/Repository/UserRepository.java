package com.filehandling.FileHandling.Repository;

import com.filehandling.FileHandling.Model.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
