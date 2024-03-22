package com.example.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String saveOrUpDate(ModelMap model, @ModelAttribute("STAFFDTO") StaffDTO dto) {
		Optional<Staffs> optionalStaff = staffService.findById(dto.getId());
		Staffs staff = null;
		
		String image = "Logo.png";
		Path path = (Path) Paths.get("upload/");
		if (optionalStaff.isPresent()) {
			// tuc la update
			if(dto.getPhoto().isEmpty()) {
				image = optionalStaff.get().getPhoto();
			}
			else {
				try {
					InputStream inputStream = dto.getPhoto().getInputStream();
					Files.copy(inputStream, path.resolve(dto.getPhoto().getOriginalFilename()),
							StandardCopyOption.REPLACE_EXISTING);
					image = dto.getPhoto().getOriginalFilename().toString();
				} catch (Exception e) {
					e.printStackTrace(); 

				}
			}
		} else {
			// tuc la add new
			if(!dto.getPhoto().isEmpty()) {
				try {
					InputStream inputStream = dto.getPhoto().getInputStream();
					Files.copy(inputStream, path.resolve(dto.getPhoto().getOriginalFilename()),
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

	@RequestMapping("/staff-edit/{id}")
	public String editStaff(ModelMap model, @PathVariable(name = "id")String id ) {
		Optional<Staffs> optionalStaff = staffService.findById(id);
		StaffDTO dto = null; 	
		
		if(optionalStaff.isPresent()) {
			Staffs st = optionalStaff.get();
			//optionalStaff.get() lay gia tri
			File file = new File("uploads/" + st.getPhoto());
			FileInputStream input;
			try{
				input = new FileInputStream(file);
				MultipartFile multiPhoto = new MockMultipartFile("file", file.getName(), "text/plain",
						IOUtils.toByteArray(input));
				dto = new StaffDTO(st.getId(), st.getName(), st.isGender(),
						st.getBirthday(), multiPhoto, st.getEmail(),
						st.getPhone(), st.getSalary(), st.getNotes(), st.getDeparts().getId());
				//	public StaffDTO(String id, String name, boolean gender, Date birthday, MultipartFile photo, String email,
				//String phone, String salary, String notes, String departId)
				
			} catch(FileNotFoundException e){
				e.printStackTrace();
				
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			model.addAttribute("STAFFDTO", dto);
			
			
		} else {
			model.addAttribute("StaffDTO", new StaffDTO());
		}
		model.addAttribute("ACTION", "/staff/saveOrUpdate");
		return "staff";
	}
	
	@RequestMapping("/staff-delete/{id}")
	public String delStaff(ModelMap model, @PathVariable("id") String id) {
		staffService.deleteById(id);
		model.addAttribute("STAFFS", staffService.findAll());
		return "view-staffs";
	}
	@ModelAttribute(name = "DEPARTS")
	public List<Departs> getAllDepart() {
		return staffService.findAllDeparts();
	}
}
