package com.thoughtworks.DNC;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.rim.device.api.collection.util.BigVector;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectListField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.xml.parsers.DocumentBuilder;
import net.rim.device.api.xml.parsers.DocumentBuilderFactory;

public class MakeCallsScreen extends MainScreen{
	String _node, _element;
	String _candidateId;
	String _candidateName;
	String name, id, phone, candidateId;
	BigVector _supporterNames;
	BigVector _supporterIds;
	BigVector _supporterPhones;
	public MakeCallsScreen(Object candidateId, Object candidateName){
		_candidateId = (String) candidateId;
		_candidateName= (String) candidateName;
		LabelField title = new LabelField("Make Calls", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
	    setTitle(title);
	    ObjectListField list = new ObjectListField(){
		protected boolean navigationClick(int status, int time)
    	{
    		UiApplication.getUiApplication().pushScreen(new PlaceCallScreen(_supporterNames.elementAt(this.getSelectedIndex()),_supporterPhones.elementAt(this.getSelectedIndex()),_supporterIds.elementAt(this.getSelectedIndex()),_candidateName));
    		return true;
    	}
    };
    _supporterNames = new BigVector();
    _supporterIds = new BigVector();
    _supporterPhones = new BigVector();
    Connection connection = new Connection();
    connection.run();
    String[] copiedItems = new String[_supporterNames.size()];
    _supporterNames.copyInto(0, _supporterNames.size(), copiedItems, 0);
    list.set(copiedItems);
    add(list);
}

private class Connection extends Thread{
	public Connection(){
		super();
	}
	public void run(){
		Document doc;
		StreamConnection conn;
		try{
			conn=(StreamConnection)Connector.open("http://10.101.100.247/phoneList.xml");
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			docBuilder.isValidating();
			doc = docBuilder.parse(conn.openInputStream());
			doc.getDocumentElement().normalize();
			NodeList list = doc.getElementsByTagName("*");
			for (int i=0;i<list.getLength();i++){
				Node value = list.item(i).getChildNodes().item(0);
 			    _node=list.item(i).getNodeName();
				_element=value.getNodeValue();
				if(list.item(i).getParentNode().getNodeName().compareTo("user")== 0 || list.item(i).getParentNode().getNodeName().compareTo("candidate")== 0){
					if(_node.compareTo("name")==0){
						name = _element;
					}
					else if(_node.compareTo("phone")==0){
						phone = _element;
					}
					else if(_node.compareTo("id")==0){
						id = _element;
					}
					else if(_node.compareTo("candidateId")==0){
						candidateId = _element;
					}
					else if(_node.compareTo("response")==0){
						if (_element.compareTo("NotYet")==0 && candidateId.compareTo(_candidateId)==0){
							_supporterNames.addElement(name);
							_supporterIds.addElement(id);
							_supporterPhones.addElement(phone);
						}
					}
				}
			}
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
}
}
