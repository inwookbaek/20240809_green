package com.lec.ex06_lifo_fifo;

import java.util.LinkedList;
import java.util.Queue;

public class QueueMain {

	public static void main(String[] args) {
		
		Queue<Message> messages = new LinkedList<>();
		
		messages.offer(new Message("Send Twitter", "홍길동"));
		messages.offer(new Message("Send Mail", "홍길순"));
		messages.offer(new Message("Send FaceBook", "홍길자"));
		messages.offer(new Message("Send KaakoTalk", "홍길녀"));
		messages.offer(new Message("Send Line", "홍길성"));
		messages.offer(new Message("Send Mail", "홍길상"));
		System.out.println("총 메시지 갯수 = " + messages.size());
		System.out.println();
		
		while(!messages.isEmpty()) {
			Message message = messages.poll();
			System.out.println(message.to + "에게 보내는 메시지 입니다! " + message.msg);
		}
		System.out.println("총 메시지 갯수 = " + messages.size());
		System.out.println();
		
		messages.offer(new Message("새로운 메시지 Twitter", "홍길동"));
		messages.offer(new Message("새로운 메시지 Mail", "홍길순"));
		messages.offer(new Message("새로운 메시지 FaceBook", "홍길자"));
		messages.offer(new Message("Send Twitter", "홍길동"));
		messages.offer(new Message("Send Mail", "홍길순"));
		messages.offer(new Message("Send FaceBook", "홍길자"));
		System.out.println("총 메시지 갯수 = " + messages.size());
		
		while(!messages.isEmpty()) {
			Message message = messages.poll();
			switch(message.msg) {
			case "Send Twitter":
				System.out.println(message.to + "에게 트위터로 문자를 보냈습니다!");
				break;
			case "Send Mail":
				System.out.println(message.to + "에게 메일을 보냈습니다!");
				break;
			case "Send FaceBook":
				System.out.println(message.to + "에게 페이스북으로 문자를 보냈습니다!");
			}
		}	
	}

}

class Message {
	public String msg;
	public String to;
	
	public Message(String msg, String to) {
		super();
		this.msg = msg;
		this.to = to;
	}	
}