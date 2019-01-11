package com.company.project.msg;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.company.project.configurer.WebMvcConfigurer;
//import com.company.project.mapper.UserMapper;

@ServerEndpoint(value = "/ws/{id}")
@Component
public class MyWebSocket {

	private static Logger log = LoggerFactory.getLogger(WebMvcConfigurer.class);

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	public static int onlineCount = 0;
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

	// 保存所有连接上的session
	public static Map<String, MyWebSocket> sessionMap = new ConcurrentHashMap<String, MyWebSocket>();

	// 静态变量，启动时间
	public static long start = System.currentTimeMillis();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("id") String id) {
		this.session = session;
		webSocketSet.add(this); // 加入set中
		sessionMap.put(id, this);
		addOnlineCount(); // 在线数加1
		try {
			sendMessage("ok", session);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("有新连接[" + this.session.getId() + "]加入！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		String sessionID = this.session == null ? "" : this.session.getId();
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		log.info("有一连接[" + sessionID + "]关闭！当前在线人数为"+onlineCount);
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message
	 *            客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("来自客户端[" + session.getId() + "]的消息:" + message);

		// 群发消息
		for (MyWebSocket item : webSocketSet) {
			try {
				item.sendMessage(message, item.session);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error(error.getMessage());
	}

	public void sendMessage(String message, Session session) throws IOException {
		log.info("给客户端[" + session.getId() + "]发消息:" + message);
		session.getBasicRemote().sendText(message);
	}

	/**
	 * 群发自定义消息
	 */
	public static void sendInfo(String message) throws IOException {
		log.info(message);
		for (MyWebSocket item : webSocketSet) {
			try {
				item.sendMessage(message, item.session);
			} catch (IOException e) {
				continue;
			}
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		MyWebSocket.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		MyWebSocket.onlineCount--;
	}
}