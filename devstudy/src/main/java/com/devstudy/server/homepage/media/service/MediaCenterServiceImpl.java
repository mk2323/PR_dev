package com.zoomansa.homepage.media.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zoomansa.config.file.FileUploadProperties;
import com.zoomansa.homepage.media.dao.MediaCenterDao;
import com.zoomansa.homepage.media.model.Media;

@Service
public class MediaCenterServiceImpl implements MediaCenterService {
	
	private static final Logger log = LoggerFactory.getLogger(MediaCenterServiceImpl.class);
	
	private final Path fileLocation;
	private final int IMG_WIDTH;
	private final int IMG_HEIGHT; 
	private final String address;

	@Autowired
	MediaCenterDao mediaCenterDao;

	@Autowired
    public MediaCenterServiceImpl(FileUploadProperties prop) {
        this.fileLocation = Paths.get(prop.getUploadDir())
                .toAbsolutePath().normalize();
        
        this.IMG_WIDTH = prop.getResizeWidth();
        this.IMG_HEIGHT = prop.getResizeHeight();
        this.address = prop.getAddress();
        
        try {
            Files.createDirectories(this.fileLocation);
        }catch(Exception e) {
            throw new RuntimeException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
        }
    }

	
	@Override
	public Media getMedia(int mediaUID) {
		return mediaCenterDao.getMedia(mediaUID);
	}

	@Override
	public List<Media> getMediaList() {
		return mediaCenterDao.getMediaList();
	}

	@Override
	public Page<Media> getMediaListByPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		return mediaCenterDao.getMediaListByPage();
	}

	@Override
	public List<Media> getUsedMediaList() {
		return mediaCenterDao.getUsedMediaList();
	}

	
	@Override
	@Transactional
	public int insertMedia(Media media, MultipartFile file) {
		
		if(!file.isEmpty()) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			
			Date date = new Date();
			String today = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
			String realFileName = "file_" + today + "." + file.getContentType().split("/")[1];
			
			media.setImgRealName(realFileName);
			media.setImgURL(address +"uploads/" + realFileName);
			media.setImgName(fileName);
			
			imageUpload(file);
		}else {
			media.setImgURL("/images/logo.png");
		}

		return mediaCenterDao.insertMedia(media);
	}
	

	@Override
	@Transactional
	public int updateMedia(Media media, MultipartFile file) {
		
		Media resultMedia = mediaCenterDao.getMedia(media.getMediaUID());
		System.out.println(resultMedia);
		
		resultMedia.setMediaTitle(media.getMediaTitle());
		resultMedia.setContents(media.getContents());
		resultMedia.setMediaLink(media.getMediaLink());
		resultMedia.setUseState(media.getUseState());
		
		if(!file.isEmpty()) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Date date = new Date();
			String today = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
			String realFileName = "file_" + today + "." + file.getContentType().split("/")[1];
			String prevRealFileName = resultMedia.getImgRealName();
			
			resultMedia.setImgRealName(realFileName);
			resultMedia.setImgURL(address +"uploads/" + realFileName);
			resultMedia.setImgName(fileName);
			
			imageUpload(file);
			
			try {
				File prevFile = new File("/uploads/resize/"+prevRealFileName);
				File prevFile2 = new File("/uploads/"+prevRealFileName);
				
				if(prevFile.exists()) {
					prevFile.delete();
				}
				if(prevFile2.exists()) {
					prevFile2.delete();
				}
			} catch (Exception exception) {
				log.error("upload file delete error.", exception);
			}
		}
		
		return mediaCenterDao.updateMedia(resultMedia);
	}
		
	@Override
	public int deleteMedia(int mediaUID) {
		return mediaCenterDao.deleteMedia(mediaUID);
	}

	@Override
	public int updateMediaState(Media media) {
		return mediaCenterDao.updateMediaState(media);
	}

	
	@Transactional
	private File imageUpload(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		String today = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String realFileName = "file_" + today + "." + file.getContentType().split("/")[1];

		Path targetLocation = this.fileLocation.resolve(realFileName);

		try {
			
	        if(!file.getContentType().split("/")[0].equals("image"))
				throw new FileUploadException("이미지 파일이 아닙니다." + fileName);
	        
	        //파일 등록
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			
			// image 크기 변경
			Image src = ImageIO.read(targetLocation.toFile());
			BufferedImage originalImage = ImageIO.read(targetLocation.toFile());
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

			BufferedImage resizeImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
			resizeImage.getGraphics()
						.drawImage(src.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH), 0, 0, null);

			Files.createDirectories(Paths.get(targetLocation.getParent()+"/resize"));
			
		    ImageIO.write(resizeImage, file.getContentType().split("/")[1], new File(targetLocation.getParent()+ "/resize/" + targetLocation.getFileName() ) );

		}catch(Exception e) {
			throw new RuntimeException("["+fileName+"] 파일 업로드에 실패하였습니다. 다시 시도하십시오.",e);
		}
		
		return new File(targetLocation.getParent()+ "/resize/" + targetLocation.getFileName() );
	}
	
}
