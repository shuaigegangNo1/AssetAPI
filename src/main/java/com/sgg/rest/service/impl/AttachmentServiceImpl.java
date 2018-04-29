package com.sgg.rest.service.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sgg.rest.model.Attachment;
import com.sgg.rest.model.AttachmentQuery;
import com.sgg.rest.model.Equipment;
import com.sgg.rest.repository.AttachmentRepository;
import com.sgg.rest.repository.EquipmentRepository;
import com.sgg.rest.service.AttachmentService;
@Service
public class AttachmentServiceImpl implements AttachmentService{
	@Resource  
    AttachmentRepository attachmentRepository;
	@Resource  
    EquipmentRepository equipmentRepository;

	@Override
	public boolean createAttachment(Integer equipmentId, Attachment attachment) {
		Equipment equipment =equipmentRepository.findOne(equipmentId);
		if(equipment !=null) {
			attachment.setEquipment(equipment);
		}
		attachment.setCreater_time(new Date());
		attachment.setUpdater_time(new Date());
		if(attachmentRepository.save(attachment) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Page<Attachment> findAttachmentCriteria(Integer page, Integer size, AttachmentQuery attachmentQuery) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");  
        Page<Attachment> attachmentPage = attachmentRepository.findAll(new Specification<Attachment>(){  
            @Override  
            public Predicate toPredicate(Root<Attachment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {  
            	    			Predicate p1 = criteriaBuilder.equal(root.get("equipment").get("id").as(String.class), attachmentQuery.getEquipment_id());
            	    			query.where(criteriaBuilder.and(p1)); 
                return query.getRestriction();  
            }  
        },pageable);  
        return attachmentPage;  
	}

}
