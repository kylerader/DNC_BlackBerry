package com.thoughtworks.DNC;

import net.rim.device.api.ui.*;

public class DNCApp extends UiApplication{
	public static void main(String[] args){
		DNCApp application = new DNCApp();
		UiApplication.getUiApplication().pushScreen(new HomeScreen());
		application.enterEventDispatcher();
	}
}
