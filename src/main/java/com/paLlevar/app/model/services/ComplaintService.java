package com.paLlevar.app.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paLlevar.app.model.entities.ComplaintEntity;
@Service
public interface ComplaintService extends GenericCRUD<ComplaintEntity,Integer> {
	List<ComplaintEntity> getAllComplaintByOrganizationId(Integer idOrg);
	
	ComplaintEntity findComplaintByOrderId(Integer orderId);
}
