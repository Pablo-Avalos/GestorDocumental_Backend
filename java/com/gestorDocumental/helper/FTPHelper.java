package com.gestorDocumental.helper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FTPHelper {

	@Value("${ftp.server}")
	private String server;
	@Value("${ftp.port}")
    private int port;
	@Value("${ftp.user}")
    private String user;
	@Value("${ftp.pass}")
    private String password;
	@Value("${ftp.upload.folder}")
	private String upload_folder;
	
    private FTPClient ftp;
 
    @PostConstruct
    public void open() throws IOException {
        ftp = new FTPClient();
 
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
 
        ftp.connect(server, port);
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }
 
        ftp.login("usrftp", "usrftp");
    }
 
    public void close() throws IOException {
        ftp.disconnect();
    }
    
    public Collection<String> listFiles(String path) throws IOException {
        FTPFile[] files = ftp.listFiles(path);
        return Arrays.stream(files)
          .map(FTPFile::getName)
          .collect(Collectors.toList());
    }
    
    public void downloadFile(String source, String destination) throws IOException {
        FileOutputStream out = new FileOutputStream(destination);
        ftp.retrieveFile(source, out);
    }
    
    public void putFileToPath(File file, String path) throws IOException {
        ftp.storeFile(path, new FileInputStream(file));
    }
    
    public void removeFileToPath(String path) throws IOException {
    	ftp.deleteFile(path);
    }
    
    public void putFileToFolder(File file, String fileName) throws IOException {
        ftp.storeFile("/documentos/" + fileName, new FileInputStream(file));
    }
    
    public void removeFileToFolder(String fileName) throws IOException {
    	ftp.deleteFile(upload_folder + fileName);
    }
	
    public Collection<String> listFilesToFolder() throws IOException {
        FTPFile[] files = ftp.listFiles(upload_folder);
        return Arrays.stream(files)
          .map(FTPFile::getName)
          .collect(Collectors.toList());
    }
    
}
