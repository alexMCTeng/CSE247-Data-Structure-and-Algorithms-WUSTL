package dh.utils;

public class SendMessage {
	
	private final Agent sender, receiver;
	
	public SendMessage(Agent sender, Agent receiver) {
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public void send(String message) {
		char[] chars = message.toCharArray();
		for (char c : chars) {
			long senderPub   = sender.nextPubKey();
			long receiverPub = receiver.nextPubKey();
			Caesared cs = sender.encryptChar(receiverPub, c);
			char decrypted = receiver.decryptChar(cs, senderPub);
			System.out.println("Sent: " + cs + "\tReceived: " + decrypted);
		}
	}

}
