package com.thoughtworks.DNC;

import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

import net.rim.device.api.collection.List;
import net.rim.device.api.collection.util.BigVector;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectListField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.xml.parsers.DocumentBuilder;
import net.rim.device.api.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class FindCandidateScreen extends MainScreen{
	String _node, _element;
	BigVector _candidateNames;
	BigVector _candidateIds;
	public FindCandidateScreen(){
		LabelField title = new LabelField("Find a Candidate", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
	    setTitle(title);
	    ObjectListField list = new ObjectListField(){
	    	protected boolean navigationClick(int status, int time)
	    	{
	    		UiApplication.getUiApplication().pushScreen(new MakeCallsScreen(_candidateIds.elementAt(this.getSelectedIndex()),_candidateNames.elementAt(this.getSelectedIndex())));
	    		return true;
	    	}
	    };
	    _candidateNames = new BigVector();
	    _candidateIds = new BigVector();
	    Connection connection = new Connection();
	    connection.run();
	    String[] copiedItems = new String[_candidateNames.size()];
	    _candidateNames.copyInto(0, _candidateNames.size(), copiedItems, 0);
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
				conn=(StreamConnection)Connector.open("http://10.101.100.247/candidates.xml");
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
					if(list.item(i).getParentNode().getNodeName().compareTo("user")== 0){
						if(_node.compareTo("name")==0){
							_candidateNames.addElement(_element);
						}
						else if(_node.compareTo("id")==0){
							_candidateIds.addElement(_element);
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
