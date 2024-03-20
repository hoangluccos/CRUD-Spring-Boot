package com.example.controllers;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.JpaSort.Path;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dto.StaffDTO;
import com.example.models.Departs;
import com.example.models.Staffs;
import com.example.services.DepartService;
import com.example.services.StaffService;

import ch.qos.logback.core.model.Model;

@Controller
public class StaffController {
	@Autowired
	StaffService staffService;

	@Autowired
	DepartService departService;

	@RequestMapping("/list-staff")
	public String listStaff(ModelMap model) {
		model.addAttribute("STAFFS", staffService.findAll());
		return "view-staffs";
	}

	@GetMapping("/add-staff")
	public String addOrEdit(ModelMap model) {
		StaffDTO staff = new StaffDTO();
		model.addAttribute("STAFFDTO", staff);
//		model.addAttribute("DEPARTS", staffService.findAllDeparts());
		model.addAttribute("ACTION", "/staff/saveOrUpdate");
		return "staff";
	}

	@PostMapping("/staff/saveOrUpdate")
	public String saveOrUpDate(@ModelAttribute("STAFFDTO") StaffDTO dto) {
		// Chuyển đổi kiểu dữ liệu từ String sang Date
        
		
		
		Optional<Staffs> optionalStaff = staffService.findById(dto.getId());
		Staffs staff = null;
		
		
		
		String image = "Logo.png";
		Path path = (Path) Paths.get("upload/");
		if (optionalStaff.isPresent()) {
			// tuc la update

		} else {
			// tuc la add new
			if (!dto.getPhoto().isEmpty()) {
				try {
					InputStream inputStream = dto.getPhoto().getInputStream();
					Files.copy(inputStream, ((java.nio.file.Path) path).resolve(dto.getPhoto().getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
					image = dto.getPhoto().getOriginalFilename().toString();
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}
		
		staff = new Staffs(dto.getId(),dto.getName(),dto.isGender(), dto.getBirthday(), image, dto.getEmail(),
				dto.getPhone(), dto.getSalary(), dto.getNotes(), new Departs(dto.getDepartId(), ""));
		
		staffService.save(staff);
		System.out.println("Save successfully!");
		return "staff";

	}

	@ModelAttribute(name = "DEPARTS")
	public List<Departs> getAllDepart() {
		return staffService.findAllDeparts();
	}
}
