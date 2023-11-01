package com.g6.onlineeyecare.spectacles.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.g6.onlineeyecare.exceptions.SpectaclesIdNotFoundException;
import com.g6.onlineeyecare.patient.dao.IPatientRepository;
import com.g6.onlineeyecare.spectacles.dao.ISpectaclesRepository;
import com.g6.onlineeyecare.spectacles.dto.Spectacles;

@Service
public class SpectaclesServiceImpl implements ISpectaclesService {

	@Autowired
	ISpectaclesRepository repository;
	@Autowired
	IPatientRepository patientRepository;

	Logger log = LoggerFactory.getLogger(SpectaclesServiceImpl.class);

	public SpectaclesServiceImpl(ISpectaclesRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	@Transactional
	public Spectacles addSpectacles(Spectacles spectacles)  {
		 try {
			 repository.save(spectacles);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return spectacles;
	}

	@Override
	@Transactional
	public Spectacles updateSpectacles(Spectacles spectacles) throws SpectaclesIdNotFoundException {
		
		Optional<Spectacles> optional = repository.findById(spectacles.getSpectaclesId());
		if (optional.isPresent()) {
			repository.save(spectacles);
		} else {
			throw new SpectaclesIdNotFoundException("Spectacles Id not found for updation");
		}
		return optional.get();
	}

	@Override
	@Transactional
	public Spectacles removeSpectacles(int spectaclesId) throws SpectaclesIdNotFoundException {
		
		Optional<Spectacles> optional = repository.findById(spectaclesId);
		if (optional.isPresent()) {
			repository.deleteById(spectaclesId);
		} else {
			throw new SpectaclesIdNotFoundException("Spectacles Id not found for deletion");
		}
		return optional.get();
	}

	@Override
	public Spectacles viewSpectacles(int spectaclesId) throws SpectaclesIdNotFoundException {
		
		Optional<Spectacles> optional = repository.findById(spectaclesId);
		if (!optional.isPresent()) {
			throw new SpectaclesIdNotFoundException("Spectacles Id not found to view spectacles");
		}
		return optional.get();
	}

	@Override
	public List<Spectacles> viewSpectacles() {
		List<Spectacles> spectacleList = null;
		try {
			spectacleList = repository.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return spectacleList;
	}

	@Override
	public List<Spectacles> viewSpectaclesByRating(int rating) {
		List<Spectacles> ratingList = new ArrayList<>();
		try {
			ratingList = repository.viewSpectaclesByRating(rating);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ratingList;
	}

	@Override
    public List<Spectacles> viewByModel(String spectaclesModel) {
        List<Spectacles> modelList = new ArrayList<>();
        try {
            modelList = repository.viewByModel(spectaclesModel);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return modelList;

    }

	@Override
	public List<Spectacles> viewByCostRange(double cost1, double cost2) {
		
		 List<Spectacles> modelList = new ArrayList<>();
	        try {
	            modelList = repository.viewByCostRange(cost1, cost2);
	        } catch (Exception e) {
	            log.error(e.getMessage(), e);
	        }

	        return modelList;

	    }

	@Override
	public List<Spectacles> viewByCostHighToLow() {
		
		List<Spectacles> costList = new ArrayList<>();
        try {
        	costList = repository.viewByCostHighToLow();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return costList;

    }

	@Override
	public List<Spectacles> viewByCostLowToHigh() {
		
		List<Spectacles> costList = new ArrayList<>();
        try {
        	costList = repository.viewByCostLowToHigh();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return costList;

    }
}
