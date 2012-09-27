package info.majesticcomputing.smsbutler;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends Activity {

	CheckBox cbSpeak;
	CheckBox cbReply;
	EditText message;
	
	public static final String SHOULD_SPEAK = "shouldSpeak";
	public static final String SHOULD_REPLY = "shouldReply";
	public static final String MESSAGE = "message";
	
	SharedPreferences prefs;
	Editor prefsEdit;
	boolean shouldSpeak;
	boolean shouldReply;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("sms",MODE_PRIVATE);     
        
        shouldSpeak = prefs.getBoolean(SHOULD_SPEAK, false);
        shouldReply = prefs.getBoolean(SHOULD_REPLY, false);
        
        initalizeLayout();
 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        
      
        return true;
    }
    
    private void initalizeLayout(){
    	cbSpeak = (CheckBox)findViewById(R.id.checkBoxSpeak);
    	cbReply = (CheckBox)findViewById(R.id.checkBoxReply);
    	message = (EditText)findViewById(R.id.editText1);
    	
    	cbSpeak.setChecked(shouldSpeak);
    	cbReply.setChecked(shouldReply);
    	
    	cbSpeak.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				
					prefsEdit = prefs.edit();
					prefsEdit.putBoolean(SHOULD_SPEAK, isChecked);
					prefsEdit.apply();}
			
    	});
    	
    	cbReply.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				
					prefsEdit = prefs.edit();
					prefsEdit.putBoolean(SHOULD_REPLY, isChecked);
					prefsEdit.apply();}
			
    	});
    }
    
    @Override
    public void onDestroy(){
    super.onDestroy();
    Log.i("smsbutler","is being destroyed");
    }
    
    @Override
    public void onPause(){
    super.onPause();
    String msg = message.getText().toString();
    prefs.edit().putString(MESSAGE, msg).apply();
    finish();
    }
}
