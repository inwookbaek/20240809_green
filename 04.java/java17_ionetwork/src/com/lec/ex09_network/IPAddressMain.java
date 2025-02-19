package com.lec.ex09_network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPAddressMain {

	public static void main(String[] args) throws Exception {
		
		InetAddress ip1 = InetAddress.getLocalHost();
		System.out.println("Local IP Address = " + ip1);
		System.out.println("Local IP Address = " + ip1.getHostAddress());
		System.out.println("Local Host Name = " + ip1.getHostName());
		System.out.println();
		
		InetAddress[] ips = InetAddress.getAllByName("www.naver.com");
		for(InetAddress ip:ips) {
			System.out.println("네이버 IP = " + ip.getHostAddress());
		}
		System.out.println();
		
		ips = InetAddress.getAllByName("www.google.com");
		for(InetAddress ip:ips) {
			System.out.println("Google IP = " + ip.getHostAddress());
		}
		System.out.println();
		
	}

}
