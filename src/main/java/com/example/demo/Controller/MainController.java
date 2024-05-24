package com.example.demo.Controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Students;
import com.example.demo.repository.StudentReppo;

@RestController
public class MainController {
	
	@Autowired
	StudentReppo studentRepo;

	@PostMapping("/addStudent")
	public void addStudent(@RequestBody Students student) {
		studentRepo.save(student);
	} 
	
	@GetMapping("/fetchStudent")
	public List<Students> fetchStudent() {
		return studentRepo.findAll();
	}
	
	@GetMapping("/getStudent/{id}")
	public Students getStudent(@PathVariable Integer id) {
    return studentRepo.findById(id).orElse(null);
	}
	

	@PutMapping("/updateStudent")
	public void updateStudent(@RequestBody Students student) {
		Students Data = studentRepo.findById(student.getRno()).orElse(null);
		System.out.println(Data);
		if(Data!= null) {
			
			Data.setName(student.getName());
			Data.setAddress(student.getAddress());
			studentRepo.save(Data);

		}
	}
	@DeleteMapping("/deleteStudent/{id}")
	public void deleteStudent(@PathVariable Integer id) {
		studentRepo.deleteById(id);
	}
	
	@PostMapping("/upload") 
	  public ResponseEntity<?> handleFileUpload( @RequestParam("file") MultipartFile file )throws Exception {

	    String fileName = file.getOriginalFilename();
	    try {
	      file.transferTo( new File("C:\\Users\\Sakshi\\Documents\\" + fileName));
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	    return ResponseEntity.ok("File uploaded successfully.");
	  }

}
	
	
