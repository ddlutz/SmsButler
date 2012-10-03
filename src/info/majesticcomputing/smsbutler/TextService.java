package info.majesticcomputing.smsbutler;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

/**
 * 
 * @author ddlutz
 *	This is the service that calls the text to speech engine.
 */
public class TextService extends IntentService implements android.speech.tts.TextToSpeech.OnInitListener{

	public TextService() {
		super("textService");
	}

	
	/**
	 * When the service is started the TextToSpeech engine is run, speaking aloud the received message. 
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		TextToSpeech tts = new TextToSpeech(getApplicationContext(), this);
		Bundle bundle = intent.getExtras();
		String message = bundle.getString("message");
		String address = bundle.getString("address");
		tts.speak("New message from " + address + ". Message: " + message, TextToSpeech.QUEUE_ADD, null);
		tts.shutdown();
	}
	
	public void onInit(int arg){
		if(arg == 0){
			//successfull init
		}
		
	}

}
