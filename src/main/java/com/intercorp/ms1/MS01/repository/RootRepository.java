package com.intercorp.ms1.MS01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intercorp.ms1.MS01.model.Root2;

@Repository
public interface RootRepository extends JpaRepository<Root2, Long> {

}
