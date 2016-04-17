package hokutosai.server.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hokutosai.server.config.MediaConfiguration;
import hokutosai.server.data.repository.media.MediaRepository;
import hokutosai.server.error.BadRequestException;
import hokutosai.server.error.InternalServerErrorException;
import hokutosai.server.io.FileUtil;
import hokutosai.server.media.Photo;
import hokutosai.server.util.RandomToken;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private MediaConfiguration mediaConfigure;

	@Autowired
	private MediaRepository mediaRepository;

	private RandomToken randToken = new RandomToken();
	private static final int ISSUE_MEDIA_ID_LENGTH = 20;
	private static final int ISSUE_MEDIA_ID_MAX_RETRY_COUNT = 100;

	@RequestMapping(method = RequestMethod.POST)
	public List<hokutosai.server.data.entity.media.Media> postMedia(@RequestParam("media") List<MultipartFile> files) throws BadRequestException, IOException, InternalServerErrorException {
        if (files.isEmpty()) throw new BadRequestException("Medias are not exist.");

        // Media生成/リサイズ
        List<hokutosai.server.media.Media> medias = new ArrayList<hokutosai.server.media.Media>();
        for (MultipartFile media: files) {
        	if (media.isEmpty()) throw new BadRequestException("Uploading media is empty.");

        	String ext = this.getMediaExtension(media);
        	Photo photo = new Photo(media, ext);

        	Long mediaSize = media.getSize();
        	Long maxMediaSize = this.mediaConfigure.getMaxMediaSize();
        	if (mediaSize > maxMediaSize) {
        		Photo resized = photo.resize((double)maxMediaSize / (double)mediaSize);
        		if (resized.getTotalResolution() >= this.mediaConfigure.getMinTotalResolution()) {
        			photo = resized;
        		}
        	}

        	medias.add(photo);
        }

        // ID発行
        String rootId = this.issueMediaRootId();

        // 書き込み
        List<hokutosai.server.data.entity.media.Media> results = new ArrayList<hokutosai.server.data.entity.media.Media>();
        int mediaCount = medias.size();
        for (int i = 0; i < mediaCount; ++i) {
        	results.add(this.writeMedia(rootId, i, medias.get(i)));
        }

        return results;
	}

	private String getMediaExtension(MultipartFile media) throws BadRequestException {
		String ext = FileUtil.getExtension(media.getOriginalFilename());

    	for (String valid: this.mediaConfigure.getMediaExtension()) {
    		if (valid.equalsIgnoreCase(ext)) return ext;
    	}

    	throw new BadRequestException("Invalid extension.");
	}

	private String issueMediaRootId() throws InternalServerErrorException {
		int count = 1;

		String id = this.randToken.generate(ISSUE_MEDIA_ID_LENGTH);
		while (this.mediaRepository.exists(id)) {
			if (++count > ISSUE_MEDIA_ID_MAX_RETRY_COUNT) throw new InternalServerErrorException("Sorry, account failed to create.");
			id = this.randToken.generate(ISSUE_MEDIA_ID_LENGTH);
		}

		return id;
	}

	private hokutosai.server.data.entity.media.Media writeMedia(String rootId, int sequence, hokutosai.server.media.Media media) throws IOException {
		String mediaId = String.format("%s%s", rootId, sequence == 0 ? "" : String.format("+%d", sequence));
		String fileName = String.format("%s.%s", mediaId, media.getExtension());
		String url = String.format("%s%s", this.mediaConfigure.getMediaUrl(), fileName);
		String mediaType = media.getMediaType();

		hokutosai.server.data.entity.media.Media mediaEntity = new hokutosai.server.data.entity.media.Media(mediaId, sequence, url, fileName, mediaType);
		hokutosai.server.data.entity.media.Media result = this.mediaRepository.save(mediaEntity);

		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(String.format("%s%s", this.mediaConfigure.getSaveDirectory(), fileName)));
		media.write(out);

		return result;
	}

}
