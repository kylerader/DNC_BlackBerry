package com.thoughtworks.DNC;

import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;

public class PlaceCallScreen extends MainScreen{
	public PlaceCallScreen(Object supporterName, Object supporterPhone, Object supporterId, Object candidateName){
		LabelField title = new LabelField("Place Call", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
		setTitle(title);
		LabelField candidateTitle = new LabelField("for "+ candidateName, LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
		add(candidateTitle);
		LabelField supporterTitle = new LabelField(supporterName, LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
		add(supporterTitle);
		LabelField script = new LabelField("Hi, "+supporterName+". My name is __. I'm a volunteer with "+candidateName+". How are you? I'm calling to find out whether you'll be supporting him/her?", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
		add(script);
	}
	
}
