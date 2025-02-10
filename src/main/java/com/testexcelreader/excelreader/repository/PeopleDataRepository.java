package com.testexcelreader.excelreader.repository;


import com.testexcelreader.excelreader.entity.PeopleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleDataRepository extends JpaRepository<PeopleData,Long> {
    public Optional<PeopleData> findByEmail(String email);

    Optional<PeopleData> findByEmailAndPId(String email, String pId);

//    @Query("SELECT p.email, p.pid from PeopleData p")
//    public List<PeoplePartialData> findAllEmailAndPid();
}
