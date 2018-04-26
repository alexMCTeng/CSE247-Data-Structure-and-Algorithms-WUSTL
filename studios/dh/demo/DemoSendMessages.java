package dh.demo;

import dh.utils.Agent;
import dh.utils.DHFactory;
import dh.utils.SendMessage;

public class DemoSendMessages {
	
	public static void main(String[] args) {
		DHFactory dhf = new DHFactory(DHFactory.BASE, DHFactory.LARGEPRIME);
		Agent one = new Agent(dhf);
		Agent two = new Agent(dhf);
		SendMessage sm = new SendMessage(one, two);
		System.out.println("Sending all capital letters:");
		sm.send("ALL CAPS");
		System.out.println("\nSending the lower case alphabet:");
		sm.send("abcdefghijklmnopqrstuvwxyz");
	}

}
