package com.sgg.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sgg.rest.model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer>,JpaSpecificationExecutor<Attachment> {

}
