package com.market.fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import com.market.utils.FastDFSClient;

public class TestFastDFS {

	@Test
	public void uploadFile() throws Exception {
		// 1.向工程中添加jar包
		// 2.创建一个配置文件，配置tracker服务器地址
		// 3.加载配置文件
		ClientGlobal.init(
				"D:/03Temp/Develop/workspace03/market-manager-web/src/main/resources/resource/trackerserver.conf");
		// 4.创建一个TrackerClient对象
		TrackerClient trackerClient = new TrackerClient();
		// 5.使用TrackerClient对象获得TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		// 6.创建一个StorageServer的引用null就可以
		StorageServer storageServer = null;
		// 7.创建StorageClient对象，传入TrackerServer与StorageServer两个参数
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		// 8.使用StorageClient对象上传文件
		String[] strings = storageClient.upload_file("D:/03Temp/pictures/58ff1a8cNf67383b412.jpg", "jpg", null);
		for (String string : strings) {
			System.out.println(string);
		}
	}
	@Test
	public void testFastClients() throws Exception{
		FastDFSClient fastDFSClient = new FastDFSClient("D:/03Temp/Develop/workspace03/market-manager-web/src/main/resources/resource/trackerserver.conf");
		String uploadFile = fastDFSClient.uploadFile("D:/03Temp/pictures/58ff1c47Nf0435b7e12.jpg");
		System.out.println(uploadFile);
	}
}
