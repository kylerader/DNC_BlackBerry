package com.thoughtworks.DNC;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;

public class HomeScreen extends MainScreen{
	public HomeScreen(){
        LabelField title = new LabelField("Home", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
        setTitle(title);
        ButtonField findCandidateButton = new ButtonField("Find a Candidate",ButtonField.FIELD_HCENTER|ButtonField.FIELD_VCENTER|ButtonField.VCENTER|ButtonField.HCENTER){
            public boolean trackwheelClick(int status,int time){
            	UiApplication.getUiApplication().pushScreen(new FindCandidateScreen());
        		//Dialog.alert("Find a Candidate Clicked");
                return true;
            }
        };
        add(findCandidateButton);
        ButtonField findIssueButton = new ButtonField("Find an Issue",ButtonField.FIELD_HCENTER|ButtonField.FIELD_VCENTER|ButtonField.VCENTER|ButtonField.HCENTER){
            public boolean trackwheelClick(int status,int time){
                Dialog.alert("Find an Issue Clicked");
                return true;
            }
        };
        add(findIssueButton);
        ButtonField makeCallsButton = new ButtonField("Make Calls",ButtonField.FIELD_HCENTER|ButtonField.FIELD_VCENTER|ButtonField.VCENTER|ButtonField.HCENTER){
            public boolean trackwheelClick(int status,int time){
                Dialog.alert("Make Calls Clicked");
                return true;
            }
        };
        add(makeCallsButton);
        ButtonField knockDoorsButton = new ButtonField("Knock Doors",ButtonField.FIELD_HCENTER|ButtonField.FIELD_VCENTER|ButtonField.VCENTER|ButtonField.HCENTER){
            public boolean trackwheelClick(int status,int time){
                Dialog.alert("Knock Doors Clicked");
                return true;
            }
        };
        add(knockDoorsButton);
       
    }
}
