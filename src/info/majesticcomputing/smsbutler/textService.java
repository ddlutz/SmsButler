package info.majesticcomputing.smsbutler;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

public class textService extends IntentService implements android.speech.tts.TextToSpeech.OnInitListener{

	public textService() {
		super("textService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		TextToSpeech tts = new TextToSpeech(getApplicationContext(), this);
		Bundle bundle = intent.getExtras();
		String message = bundle.getString("message");
		String address = bundle.getString("address");
		tts.speak("New message from " + address + ". Message: " + message, TextToSpeech.QUEUE_ADD, null);
		
		tts.shutdown();
	}
	
	public void onInit(int arg){
		// TODO Auto-generated method stub
		if(arg == 0){
			//successfull init
		}
		
	}

}
