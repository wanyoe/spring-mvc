package com.glp.util;

import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
 
public class ElasticSearchClientFactoryBean implements FactoryBean<Client>,DisposableBean{

	List<TransportAddress> addresses;
	TransportClient client ;
	@Override
	public Client getObject() throws Exception {
		if(client != null ) return client;
		if(addresses != null && addresses.size() > 0){
			client = new TransportClient();
			for(TransportAddress addr : addresses){
				client.addTransportAddress(addr);
			}
			return client;
		}		
		return null;
	}

	@Override
	public Class<?> getObjectType() {
		return Client.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public void setAddresses(List<TransportAddress> addresses) {
		this.addresses = addresses;
	}
	
	public void close(){
		if(client != null){
			client.close();
		}
	}
	
	@Override
	public void destroy() throws Exception {
		close();
	}
}
