package ro.pub.cs.systems.eim.lab05.startedserviceactivity.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import ro.pub.cs.systems.eim.lab05.startedserviceactivity.general.Constants;

public class StartedServiceBroadcastReceiver extends BroadcastReceiver {

    private final TextView messageTextView;

    // TODO: exercise 9 - default constructor
    public StartedServiceBroadcastReceiver() {
        this.messageTextView = null;
    }

    public StartedServiceBroadcastReceiver(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: exercise 7 - get the action and the extra information from the intent
        // and set the text on the messageTextView

        String action = intent.getAction();
        if (action == null) {
            return;
        }

        String data = null;
        switch (action) {
            case Constants.ACTION_STRING:
                data = intent.getStringExtra(Constants.DATA);
                break;
            case Constants.ACTION_INTEGER:
                data = String.valueOf(intent.getIntExtra(Constants.DATA, -1));
                break;
            case Constants.ACTION_ARRAY_LIST:
                data = intent.getStringArrayListExtra(Constants.DATA).toString();
                break;
        }

        if (messageTextView != null) {
            messageTextView.setText(data);
        } else {
            Intent startedServiceActivityIntent = new Intent(context, StartedServiceActivity.class);
            startedServiceActivityIntent.putExtra(Constants.MESSAGE, data);
            startedServiceActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(startedServiceActivityIntent);
        }

        // TODO: exercise 9 - restart the activity through an intent
        // if the messageTextView is not available
    }
}
