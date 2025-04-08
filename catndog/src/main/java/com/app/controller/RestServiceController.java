package com.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.service.FileTransferService;

@RestController
@RequestMapping("/rest")
public class RestServiceController {
	private final FileTransferService fileTransferService;
	
	public RestServiceController(FileTransferService fileTransferService) {
		this.fileTransferService = fileTransferService;
	}
	
	Map<String, String> map = new HashMap<>();
	
	@PostMapping("/uploadfile")
	public Map<String, String> uploadImage(@RequestPart(value = "uploadfile") 
						MultipartFile uploadfile) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
		String catordog = fileTransferService.getClass(uploadfile); 
		map.put("catordog", catordog);
		System.out.println(map);
		
		return map;
	}
}
