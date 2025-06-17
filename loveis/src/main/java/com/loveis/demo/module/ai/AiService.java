package com.loveis.demo.module.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.loveis.demo.module.base.BaseService;

@Service
public class AiService extends BaseService {

	@Autowired
	private AmazonS3Client amazonS3Client;
	
	@Autowired
	private AiDao aiDao;
	
	public int aiLoveInsert(AiDto aiDto) throws Exception {
		uploadFilesToS3(aiDto.getUploadImg1(), aiDto, "image", 100, 1, "1", aiDao, amazonS3Client);
		return 1;
	}
}
