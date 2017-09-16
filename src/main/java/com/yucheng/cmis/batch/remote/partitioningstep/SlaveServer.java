package com.yucheng.cmis.batch.remote.partitioningstep;

import java.io.IOException;
import java.util.Scanner;


import fi.iki.elonen.NanoHTTPD;

public class SlaveServer {
	// partitionremote remote
	public static final String FILE_NAME = "remotepartitioning" + "/job-slave.xml";
	public static void main(String[] args) throws IOException {
		SlaveServer serverMain = new SlaveServer();
		// 启动服务
		serverMain.startServer();
		//
		while (true) {
			String consoleLine = serverMain.readFromConsole();
			if (consoleLine == null) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if ("exit".equalsIgnoreCase(consoleLine.trim())) {
				break;
			}
		}
		serverMain.stopServer();
	}
	protected void startServer() {
		try {
			BatchApp.getInstance().start(new String[]{FILE_NAME});
			BatchExecHTTPD httpdServer = new BatchExecHTTPD(8089);
			httpdServer.start(NanoHTTPD.SOCKET_READ_TIMEOUT, true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void stopServer() {
		BatchApp.getInstance().stop();
	}

	protected String readFromConsole() {
		try {
			Scanner scanner = new Scanner(System.in);
			return scanner.nextLine();
		} catch (Exception e) {
			// doNothing
		}
		return null;
	}
}