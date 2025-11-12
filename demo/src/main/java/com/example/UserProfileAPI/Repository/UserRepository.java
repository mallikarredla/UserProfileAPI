package com.example.UserProfileAPI.Repository;

import com.example.UserProfileAPI.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameIgnoreCase(String name);
    Page<User> findByNameIgnoreCaseContaining(String name, Pageable pageable);


    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
//Pagination is like handling large sets of Data , Breaking the datasets into smaller chunks, Pagination can be done in two ways,
//1. Offset Based is for small datasets where adding and modifications of data sets are very few , In such cases go with Offset based pagination.
//2.Cursor Based is for large and fast changing data Sets i.e Better approach. Cursor based has two approaches. Time based and Key Based.
//cursor based is for performance and consistency.