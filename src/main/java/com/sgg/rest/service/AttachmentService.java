package com.sgg.rest.service;

import org.springframework.data.domain.Page;

import com.sgg.rest.model.Attachment;
import com.sgg.rest.model.AttachmentQuery;

public interface AttachmentService {
	boolean createAttachment( Integer equipmentId,Attachment attachment);
	Page<Attachment> findAttachmentCriteria(Integer page,Integer size,AttachmentQuery attachmentQuery);  
}
