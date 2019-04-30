package com.web.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import com.web.utill.FileUtils;

import ch.qos.logback.classic.Logger;

public class FileUtilsTest {

	private static final String uploadRootPath = "D:/JAVA/portfolio/Discord/src/main/webapp/resources/upload";
	
	private static Logger logger = (Logger)LoggerFactory.getLogger(FileUtilsTest.class);
	
	@Test
	@Ignore
	public void test() {
		assertTrue(existsDir(uploadRootPath));
		
		String path = FileUtils.getCurrentUploadPath(uploadRootPath);
		logger.debug("path={}", path);
		assertTrue(existsDir(path));
	}
	
	@Test
	@Ignore
	public void testThumbnail() throws IOException {
		String dirname = "D:/JAVA/portfolio/Discord/src/main/webapp/resources/upload/2019/03/10";
		String filename = "icon1.png";
		String thumbname = "s_" + filename;
		logger.info("dirname={}, filename={}", dirname, filename);
		
		File old = new File(dirname, thumbname);
		if (old.exists())
			old.delete();
		
		String thumbnailName = FileUtils.mamkeThumbnail(uploadRootPath, dirname, filename);
		logger.info("thumbnailName={}", thumbnailName);
		assertEquals(thumbnailName, "/2019/03/10/" + thumbname);
		
		assertTrue(existsDir(dirname + File.separator + thumbname));
	}
	@Ignore
	private boolean existsDir(String path) {
		return new File(path).exists();
	}
}
