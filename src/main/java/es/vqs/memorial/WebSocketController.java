package es.vqs.memorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin("*")
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/send/message")
	public void onReceivedMessage(String message) {
		System.out.println(message);
		this.template.convertAndSend("/chat", message);
	}
}
