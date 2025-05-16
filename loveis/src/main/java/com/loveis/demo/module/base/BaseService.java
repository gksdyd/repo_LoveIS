package com.loveis.demo.module.base;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.google.gson.Gson;
import com.loveis.demo.module.util.UtilDateTime;

public class BaseService {
	@Value("${cloud_aws_bucket}")
	private String bucket;
	
	@Value("${face_image_key}")
	private String faceKye;
	
	public void uploadFilesToS3(MultipartFile[] multipartFiles, BaseDto dto, String tableName, int type, int maxNumber, String pSeq, BaseDao dao, AmazonS3Client amazonS3Client) throws Exception {
		
		for(int i=0; i<multipartFiles.length; i++) {
			
			if(!multipartFiles[i].isEmpty()) {
				
//				String className = dto.getClass().getSimpleName().toString().toLowerCase();
				// 접두사: 4, 접미사: uploaded (8) 삭제
				String className = tableName;		
//				String className = tableName.substring(4).substring(0,tableName.length()-12);		
				String fileName = multipartFiles[i].getOriginalFilename();
				String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
				String uuid = UUID.randomUUID().toString();
				String uuidFileName = uuid + "." + ext;
				String pathModule = className;
				String nowString = UtilDateTime.nowString();
				String pathDate = nowString.substring(0,4) + "/" + nowString.substring(5,7) + "/" + nowString.substring(8,10); 
				String path = pathModule + "/" + type + "/" + pathDate + "/";
//				String pathForView = Constants.UPLOADED_PATH_PREFIX_FOR_VIEW_LOCAL + "/" + pathModule + "/" + type + "/" + pathDate + "/";
				
				
		        ObjectMetadata metadata = new ObjectMetadata();
		        metadata.setContentLength(multipartFiles[i].getSize());
		        metadata.setContentType(multipartFiles[i].getContentType());
		        
		        amazonS3Client.putObject(bucket, path + uuidFileName, multipartFiles[i].getInputStream(), metadata);
//		        https://doris-tt.s3.ap-northeast-2.amazonaws.com/Goods/1002/2025/04/17/de100958-3684-4ed1-94d9-de77c98dbb94.jpeg
		        
		        String objectUrl = amazonS3Client.getUrl(bucket, path + uuidFileName).toString();
		        
				dto.setPath(objectUrl);
				dto.setOriginalName(fileName);
				dto.setUuidName(uuidFileName);
				dto.setExt(ext);
				dto.setSize(multipartFiles[i].getSize());
				
				dto.setTableName(tableName);
				dto.setType(type);
	//			dto.setDefaultNy();
				dto.setSort(maxNumber + i);
				dto.setPseq(pSeq);
				
				dao.insertUploaded(dto);
			}
		}
	}
	
	public Boolean humanCheck(MultipartFile[] multipartFiles) {
		try {
			File tempFile = new File(System.getProperty("java.io.tmpdir") + File.separator + multipartFiles[0].getOriginalFilename());
			multipartFiles[0].transferTo(tempFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String openApiURL = "http://aiopen.etri.re.kr:8000/HumanParsing";
		String openApiURL = "http://aiopen.etri.re.kr:8000/FaceDeID";
		String accessKey = faceKye;    // 발급받은 API Key
		String type = "1";     // 얼굴 비식별화 기능 "1"로 설정
		String file = System.getProperty("java.io.tmpdir") + multipartFiles[0].getOriginalFilename();  	// 이미지 파일 경로
		String imageContents = "";
		
	    Gson gson = new Gson();
	  
	    Map<String, Object> request = new HashMap<>();
	    Map<String, String> argument = new HashMap<>();
	  
	    try {
	    	Path path = Paths.get(file);
	    	byte[] imageBytes = Files.readAllBytes(path);
	    	imageContents = Base64.getEncoder().encodeToString(imageBytes);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	  
    	argument.put("type", type);
    	argument.put("file", imageContents);
  
    	request.put("argument", argument);
  
    	URL url;
    	Integer responseCode = null;
    	String responBody = null;
    	int result = 0;
    	try {
    		url = new URL(openApiURL);
    		HttpURLConnection con = (HttpURLConnection)url.openConnection();
    		con.setRequestMethod("POST");
    		con.setDoOutput(true);
    		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    		con.setRequestProperty("Authorization", accessKey);
  
    		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
    		wr.write(gson.toJson(request).getBytes("UTF-8"));
    		wr.flush();
    		wr.close();
  
    		responseCode = con.getResponseCode();
    		InputStream is = con.getInputStream();
    		byte[] buffer = new byte[is.available()];
    		int byteRead = is.read(buffer);
    		responBody = new String(buffer);
  
    		System.out.println("[responseCode] " + responseCode);
    		System.out.println("[responBody]");
    		System.out.println(responBody);
    		
    		if (responBody.indexOf(']') - responBody.indexOf('[') != 1) {
    			int count = 0;
    			int start = 0;
    			while (true) {
    				if (responBody.indexOf("conf") != -1 && count == 0) {
    					System.out.println("처음 사진 초기화");
    					start = responBody.indexOf("conf") + 6;
    					count++;
    				} else if (responBody.indexOf("conf", start) != -1 && count > 0) {
    					System.out.println("사진 여러 개");
    					return false;
    				} else {
    					System.out.println("사진 1개");
    					break;
    				}
    			}
    			int end = responBody.indexOf(',', start);
    			result = (int)(Double.parseDouble(responBody.substring(start, end)) * 100);
        	}
  
    	} catch (MalformedURLException e) {
    		e.printStackTrace();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	if (result > 90) {
    		return true;
    	}
    	return false;
	}
}
