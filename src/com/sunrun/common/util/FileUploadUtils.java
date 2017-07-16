package com.sunrun.common.util;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jeecms.cms.Constants;
import com.jeecms.common.upload.FileRepository;
/**
 * 文件提交工具
 * @author wangcy
 * @ClassName FileUploadUtils.java
 * @CreateDate  2017-5-23
 * @descrintion  
 * @editor 
 * @editDate
 */
public class FileUploadUtils {

	/**
	 * 备案登记图片
	 */
	public static String  BEI_AN=Constants.UPLOAD_PATH +"beiandengji/img";
	
	/**
	 * 实名认证图片
	 */
	public static String  REAL_NAME=Constants.UPLOAD_PATH +"authentication/img";
    /**
     * 用户头像
     */
	public static String  USER=Constants.UPLOAD_PATH +"user/img";
	
	public static String uploadRealName(MultipartFile imgFile, HttpServletRequest request, FileRepository fileRepository) {
		return uploadFIle(REAL_NAME, imgFile, request, fileRepository);
	}
	
	public static String uploadBeiAnImg(MultipartFile imgFile, HttpServletRequest request, FileRepository fileRepository) {
		return uploadFIle(BEI_AN, imgFile, request, fileRepository);
	}
	public static String uploadUserImg(MultipartFile imgFile, HttpServletRequest request, FileRepository fileRepository) {
		return uploadFIle(USER, imgFile, request, fileRepository);
	}
	

	
	/**
	 * 上传文件
	 * @param fileMainUrl 文件主要路径
	 * @param imgFile 文件
	 * @param request 
	 * @param fileRepository
	 * @return
	 */
	public static String uploadFIle(String fileMainUrl, MultipartFile imgFile, HttpServletRequest request, FileRepository fileRepository) {
		String fileUrl = "";
		if (imgFile != null && !imgFile.isEmpty()) {
			String ext = FilenameUtils.getExtension(imgFile.getOriginalFilename()).toLowerCase(Locale.ENGLISH);
			try {
				fileUrl = fileRepository.storeByExt(fileMainUrl, ext, imgFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			fileUrl = request.getContextPath() + fileUrl;
		}
		return fileUrl;
	}

}
