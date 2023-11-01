package com.g6.onlineeyecare.spectacles.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.g6.onlineeyecare.exceptions.SpectaclesIdNotFoundException;
import com.g6.onlineeyecare.spectacles.dto.Spectacles;
import com.g6.onlineeyecare.spectacles.dto.SpectaclesDTO;
import com.g6.onlineeyecare.spectacles.dto.SpectaclesResponseDTO;
import com.g6.onlineeyecare.spectacles.service.ISpectaclesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "Spectacles Rest Controller")
@RestController
@RequestMapping("/spectacles")
@CrossOrigin(origins = {"http://localhost:9999","http://localhost:4200"}, allowedHeaders = "*")
public class SpectaclesController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	ISpectaclesService spectaclesService;

	@ApiOperation(value = "add a new Spectacles", response = Spectacles.class)
	@PostMapping("/add")
	public ResponseEntity<SpectaclesResponseDTO> addSpectacles(@RequestBody @Valid SpectaclesDTO spectacles)
		 {

		Spectacles actucal = modelMapper.map(spectacles, Spectacles.class);
		SpectaclesResponseDTO response = modelMapper.map(this.spectaclesService.addSpectacles(actucal),
				SpectaclesResponseDTO.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "update Spectacles", response = Spectacles.class)
	@PutMapping("/update")
	public ResponseEntity<SpectaclesResponseDTO> updateSpectacles(@RequestBody SpectaclesDTO spectacles)
			throws SpectaclesIdNotFoundException {

		Spectacles actucal = modelMapper.map(spectacles, Spectacles.class);
		SpectaclesResponseDTO response = modelMapper.map(this.spectaclesService.updateSpectacles(actucal),
				SpectaclesResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "delete Spectacles", response = Spectacles.class)
	@DeleteMapping("/delete/{spectaclesId}")
	public ResponseEntity<SpectaclesResponseDTO> deleteSpectacles(@PathVariable("spectaclesId") int spectaclesId)
			throws SpectaclesIdNotFoundException {

		SpectaclesResponseDTO response = modelMapper.map(this.spectaclesService.removeSpectacles(spectaclesId),
				SpectaclesResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "view Spectacles by Id", response = Spectacles.class)
	@GetMapping("/view/{spectaclesId}")
	public ResponseEntity<SpectaclesResponseDTO> viewSpectacles(@PathVariable("spectaclesId") int spectaclesId)
			throws SpectaclesIdNotFoundException {

		SpectaclesResponseDTO response = modelMapper.map(this.spectaclesService.viewSpectacles(spectaclesId),
				SpectaclesResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "view all Spectacles", response = Spectacles.class)
	@GetMapping("/viewAll")
	public ResponseEntity<List<SpectaclesResponseDTO>> viewSpectacles() {

		List<Spectacles> spectaclesList = this.spectaclesService.viewSpectacles();
		List<SpectaclesResponseDTO> spectaclesDtoList = new ArrayList<>();
		for (Spectacles s : spectaclesList) {
			SpectaclesResponseDTO spectaclesDto = modelMapper.map(s, SpectaclesResponseDTO.class);
			spectaclesDtoList.add(spectaclesDto);
		}
		if (!(spectaclesDtoList.isEmpty())) {
			return new ResponseEntity<>(spectaclesDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(spectaclesDtoList, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "view all Spectacles", response = Spectacles.class)
	@GetMapping("/rating/{rating}")
	public ResponseEntity<List<SpectaclesResponseDTO>> viewSpectaclesByRating(@PathVariable("rating") int rating) {

		List<Spectacles> spectaclesList = this.spectaclesService.viewSpectaclesByRating(rating);
		List<SpectaclesResponseDTO> spectaclesDtoList = new ArrayList<>();
		for (Spectacles s : spectaclesList) {
			SpectaclesResponseDTO spectaclesDto = modelMapper.map(s, SpectaclesResponseDTO.class);
			spectaclesDtoList.add(spectaclesDto);
		}
		if (!(spectaclesDtoList.isEmpty())) {
			return new ResponseEntity<>(spectaclesDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(spectaclesDtoList, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Get the required spectacle by model ", response = Spectacles.class)
	@GetMapping("/viewByModel/{model}")
	public ResponseEntity<List<SpectaclesResponseDTO>> viewByModel(@PathVariable("model") String model) {
		List<Spectacles> spectacleList = this.spectaclesService.viewByModel(model);
		List<SpectaclesResponseDTO> spectacleDtoList = new ArrayList<>();
		for (Spectacles a : spectacleList) {
			SpectaclesResponseDTO specDto = modelMapper.map(a, SpectaclesResponseDTO.class);
			spectacleDtoList.add(specDto);
		}
		if (!(spectacleDtoList.isEmpty())) {
			return new ResponseEntity<>(spectacleDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(spectacleDtoList, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Get the required spectacle by model ", response = Spectacles.class)
	@GetMapping("/viewByCost/{range1}/{range2}")
	public ResponseEntity<List<SpectaclesResponseDTO>> viewByCostRange(@PathVariable("range1")double cost1,@PathVariable("range2")double cost2) {
		List<Spectacles> spectacleList = this.spectaclesService.viewByCostRange(cost1, cost2);
		List<SpectaclesResponseDTO> spectacleDtoList = new ArrayList<>();
		for (Spectacles a : spectacleList) {
			SpectaclesResponseDTO specDto = modelMapper.map(a, SpectaclesResponseDTO.class);
			spectacleDtoList.add(specDto);
		}
		if (!(spectacleDtoList.isEmpty())) {
			return new ResponseEntity<>(spectacleDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(spectacleDtoList, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Get the required spectacle by model ", response = Spectacles.class)
	@GetMapping("/viewByCost/h")
	public ResponseEntity<List<SpectaclesResponseDTO>> viewByCostHighToLow() {
		List<Spectacles> spectacleList = this.spectaclesService.viewByCostHighToLow();
		List<SpectaclesResponseDTO> spectacleDtoList = new ArrayList<>();
		for (Spectacles a : spectacleList) {
			SpectaclesResponseDTO specDto = modelMapper.map(a, SpectaclesResponseDTO.class);
			spectacleDtoList.add(specDto);
		}
		if (!(spectacleDtoList.isEmpty())) {
			return new ResponseEntity<>(spectacleDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(spectacleDtoList, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Get the required spectacle by model ", response = Spectacles.class)
	@GetMapping("/viewByCost/l")
	public ResponseEntity<List<SpectaclesResponseDTO>> viewByCostLowToHigh() {
		List<Spectacles> spectacleList = this.spectaclesService.viewByCostLowToHigh();
		List<SpectaclesResponseDTO> spectacleDtoList = new ArrayList<>();
		for (Spectacles a : spectacleList) {
			SpectaclesResponseDTO specDto = modelMapper.map(a, SpectaclesResponseDTO.class);
			spectacleDtoList.add(specDto);
		}
		if (!(spectacleDtoList.isEmpty())) {
			return new ResponseEntity<>(spectacleDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(spectacleDtoList, HttpStatus.BAD_REQUEST);
		}
	}
}
