package com.paLlevar.app.model.services.impl;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paLlevar.app.model.entities.ComplaintEntity;
import com.paLlevar.app.model.repository.ComplaintRepository;
import com.paLlevar.app.model.services.ComplaintService;

@Service
@Transactional
public class ComplaintServiceImpl implements ComplaintService{
	
	@Autowired
	private ComplaintRepository repo;

	@Override
	public List<ComplaintEntity> getAll() {
		return repo.findAll();
	}
	
	@Override
	public ComplaintEntity getOneById(Integer id) {
		return repo.findById(id).orElse(new ComplaintEntity());
	}

	@Override
	public ComplaintEntity save(ComplaintEntity t) {
		if(t.getPhoto() != null &&  t.getPhoto().length>0) {
			repo.updatePhoto(t.getId(),t.getPhoto());
		}
		t.setCreateDate(LocalDateTime.now());
		return repo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);	
	}

	@Override
	public List<ComplaintEntity> getAllComplaintByOrganizationId(Integer idOrg) {
		return repo.findByOrganizationId(idOrg);
	}


}
