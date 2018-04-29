package com.sgg.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgg.rest.model.Attachment;
import com.sgg.rest.model.AttachmentQuery;
import com.sgg.rest.service.AttachmentService;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
	@Autowired  
	private AttachmentService attachmentService;
	@RequestMapping(value="/create", method= {RequestMethod.POST,RequestMethod.GET})
	public ResponseEntity<Map<String,Object>> createAttachment(@RequestParam int equipmentId,@RequestBody Attachment attachment) {
		boolean res =attachmentService.createAttachment(equipmentId, attachment);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result",res);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/queryList", method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> getAttachmentListByEquipmentId(@PageableDefault(value = 15, sort = { "id" }) @RequestParam int page,@RequestBody AttachmentQuery attachmentQuery) {
		 Integer size=10;
		 Page<Attachment>  attachmentList= attachmentService.findAttachmentCriteria(page, size, attachmentQuery);
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("attachmentList", attachmentList);
	    return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
}
