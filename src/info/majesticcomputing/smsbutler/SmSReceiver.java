package info.majesticcomputing.smsbutler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.*;
import android.util.Log;
import android.widget.Toast;



public class SmSReceiver extends BroadcastReceiver {

	SharedPreferences prefs;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
			
			prefs = context.getSharedPreferences("sms", android.content.Context.MODE_PRIVATE);
			Log.i("SmSReceiver", "Received " + intent);
			Bundle data = intent.getExtras();
			String message = "";
		
			String address = "";
			if(data != null)
			{
				Object[] smsExtra = (Object[]) data.get("pdus");
				
				for(int i = 0; i < smsExtra.length; i++)
				{
					SmsMessage sms = SmsMessage.createFromPdu((byte[])smsExtra[i]);
					message = sms.getMessageBody().toString();
					address = sms.getOriginatingAddress();
				}
				
				if(prefs.contains(MainActivity.SHOULD_SPEAK) && prefs.getBoolean(MainActivity.SHOULD_SPEAK, false) == true){
					
				if(address != null && !address.equals("null")){
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
				
				context.startService(new android.content.Intent(context, textService.class).putExtra("address", address).putExtra("message", message));
					}
				}
				
				if(prefs.contains(MainActivity.SHOULD_REPLY) && prefs.getBoolean(MainActivity.SHOULD_REPLY, false) == true)
				{
					//Send sms reply here
					SmsManager sms = SmsManager.getDefault();
					sms.sendTextMessage(address, null, "testing", null, null);
				}
			}
			
					
	}




}
