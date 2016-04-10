package hokutosai.server.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hokutosai.server.data.repository.media.MediaRepository;
import hokutosai.server.error.BadRequestException;
import hokutosai.server.io.FileUtil;
import hokutosai.server.media.Photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@EnableAutoConfiguration
@RequestMapping("/media")
public class MediaApiController {

	@Value("${hokutosai.server.media.saveDirectory}")
	private String saveDirectory;

	@Value("${hokutosai.server.media.maxMediaSize}")
	private Long maxMediaSize;

	@Value("${hokutosai.server.media.minTotalResolution}")
	private Integer minTotalResolution;

	@Value("${hokutosai.server.media.extension}")
	private String[] mediaExtension;

	@Value("${hokutosai.server.media.url}")
	private String mediaUrl;

	@Autowired
	private MediaRepository mediaRepository;

	@RequestMapping(method = RequestMethod.POST)
	public List<hokutosai.server.data.entity.media.Media> postMedia(@RequestParam("media") List<MultipartFile> files) throws BadRequestException, IOException {
        if (files.isEmpty()) throw new BadRequestException("Medias are not exist.");

        // Media生成/リサイズ
        List<hokutosai.server.media.Media> medias = new ArrayList<hokutosai.server.media.Media>();
        for (MultipartFile media: files) {
        	if (media.isEmpty()) throw new BadRequestException("Uploading media is empty.");

        	String ext = this.getMediaExtension(media);
        	Photo photo = new Photo(media, ext);

        	Long fileSize = media.getSize();
        	if (fileSize > this.maxMediaSize) {
        		Photo resized = photo.resize((double)this.maxMediaSize / (double)fileSize);
        		if (resized.getTotalResolution() >= this.minTotalResolution) {
        			photo = resized;
        		}
        	}

        	medias.add(photo);
        }

        // 書き込み
        List<hokutosai.server.data.entity.media.Media> results = new ArrayList<hokutosai.server.data.entity.media.Media>();
        for (hokutosai.server.media.Media media: medias) {
        	results.add(this.writeMedia(media));
        }

        return results;
	}

	private String getMediaExtension(MultipartFile media) throws BadRequestException {
		String ext = FileUtil.getExtension(media.getOriginalFilename());

    	for (String valid: this.mediaExtension) {
    		if (valid.equalsIgnoreCase(ext)) return ext;
    	}

    	throw new BadRequestException("Invalid extension.");
	}

	private hokutosai.server.data.entity.media.Media writeMedia(hokutosai.server.media.Media media) throws IOException {
		String mediaType = media.getMediaType();

		// Reserve writing.
		hokutosai.server.data.entity.media.Media reservedMedia = this.mediaRepository.save(new hokutosai.server.data.entity.media.Media(mediaType));
		Integer mediaId = reservedMedia.getMediaId();
		String fileName = String.format("%s-%d.%s", mediaType, mediaId, media.getExtension());

		// Write
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(String.format("%s%s", this.saveDirectory, fileName)));
		media.write(out);

		// Regist url
		reservedMedia.setFileName(fileName);
		reservedMedia.setUrl(String.format("%s%s", this.mediaUrl, fileName));
		this.mediaRepository.registUrl(mediaId, reservedMedia.getUrl());

		return reservedMedia;
	}

}
