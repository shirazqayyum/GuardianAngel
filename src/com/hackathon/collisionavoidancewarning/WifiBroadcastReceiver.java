package com.hackathon.collisionavoidancewarning;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.util.Log;

public class WifiBroadcastReceiver extends BroadcastReceiver {

	public WifiBroadcastReceiver(WifiP2pManager manager, Channel channel,
            MainActivity activity) {
        super();
        this.mManager = manager;
        this.mChannel = channel;
        this.mActivity = activity;
    }

	 @Override
	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
	            // Determine if Wifi P2P mode is enabled or not, alert
	            // the Activity.
	            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
	            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
	                mActivity.wifiEnabled = true;
	            } else {
	                mActivity.wifiEnabled = false;
	            }
	        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

	        	 // request available peers from the wifi p2p manager. This is an
	            // asynchronous call and the calling activity is notified with a
	            // callback on PeerListListener.onPeersAvailable()
	            if (mManager != null) {
	                mManager.requestPeers(mChannel, mActivity);
	                Log.d(TAG, "peers requested");
	            }

	        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

	            // Connection state changed!  We should probably do something about
	            // that.

	        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
//	            DeviceListFragment fragment = (DeviceListFragment) activity.getFragmentManager()
//	                    .findFragmentById(R.id.frag_list);
//	            fragment.updateThisDevice((WifiP2pDevice) intent.getParcelableExtra(
//	                    WifiP2pManager.EXTRA_WIFI_P2P_DEVICE));

	        }
	    }
	 
	 
	 /* instance variables */
	 	private WifiP2pManager mManager;
	    private Channel mChannel;
	    private MainActivity mActivity;
	    private String TAG = "Broadcast";
}
