package com.app.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileTransferService {
	public String getClass(MultipartFile uploadfile) throws IOException {
		String check;
		double doubleCheck;
		String baseUrl = "C://Temp/input_image/";

		if (!uploadfile.isEmpty()) {

			File newFileName = new File(baseUrl + uploadfile.getOriginalFilename());

			// 전달된 내용을 실제 물리적인 파일로 저장
			uploadfile.transferTo(newFileName);

			check = RequestUtil.restRequest("http://localhost:8000/catdog/predict/",
					baseUrl + uploadfile.getOriginalFilename());

			System.out.println("result from FastAPI:" + check);
			doubleCheck = Double.parseDouble(check);

			if (doubleCheck < 0.5) {
				return "cat";
			} else {
				return "dog";
			}
		}
		return "error";
	}
}
