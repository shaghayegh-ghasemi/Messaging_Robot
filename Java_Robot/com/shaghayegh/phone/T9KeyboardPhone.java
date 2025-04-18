package com.shaghayegh.phone;

import java.util.HashMap;
import java.util.Map;

public class T9KeyboardPhone implements HasSendButton, HasKeys{
    private final Map<String, String> t9keypad = new HashMap<>();
    private String lastMessage;
    private final MessageSender sender = new MessageSender();

    public T9KeyboardPhone(){
        t9keypad.put("2", "abc");
        t9keypad.put("3", "def");
        t9keypad.put("4", "ghi");
        t9keypad.put("5", "jkl");
        t9keypad.put("6", "mno");
        t9keypad.put("7", "pqrs");
        t9keypad.put("8", "tuv");
        t9keypad.put("9", "wxyz");
        t9keypad.put("0", " "); // space

        lastMessage = "";

    }

    @Override
    public void clickSend() {
        if (!lastMessage.isEmpty() && lastMessage != null) {
            sender.sendMessage(lastMessage);
        } else {
            System.out.println("No message to sent! ");
        }
    }

    public void type(String message) {
        for(Character c: message.toLowerCase().toCharArray()){
            boolean found = false; // to prevent null or unknown chars
            for(Map.Entry<String, String> entry : t9keypad.entrySet()){
                String button = entry.getKey();
                String letters = entry.getValue();

                int clickCount = letters.indexOf(c);
                if(clickCount != -1){
                    for (int i = 0; i <= clickCount; i++) clickKey(button);
                    found = true;
                    break;
                }
            }
            if(!found){
                System.out.println("Unknown character: " + c);
            }
        }

        lastMessage = message.toLowerCase().replaceAll("[^a-z ]", "");

    }
}
