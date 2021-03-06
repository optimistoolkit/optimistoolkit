/* $Id: DeleteVmClient.java 1037 2011-05-11 14:56:43Z rkuebert $ */

/*
 Copyright (c) 2011 University of Stuttgart
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
 derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package eu.optimis.service_manager.client;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Client for deletion of a VM from a service in the
 * ServiceManager.
 * <p/>
 * The URI needs to include the VM id as a bug in Jersey
 * does not allow to parameterize a DELETE call.
 * 
 * @author roland
 *
 */
public class DeleteVmClient {

private WebResource serviceManager;
	
	/**
	 * Creates a new client for VM deletion.
	 * <p/>
	 * The <code>uri</code> needs to be the full URI including the VM id,
	 * for example
	 * <code>http://localhost:8080/ServiceManager/services/14711/vms/135</code>.
	 * 
	 * @param uri the URI of the VM to delete
	 */
	public DeleteVmClient(String uri) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		serviceManager = client.resource(getBaseURI(uri));
	}
	
	/**
	 * Delete the VM specified for this client.
	 */
	public void deleteVm() {
		serviceManager.delete();
	}
	
	private URI getBaseURI(String uri) {
		return UriBuilder.fromUri(uri).build();
	}
	
	public static void main(String[] args) {
		DeleteVmClient client = new DeleteVmClient("http://localhost:8080/ServiceManager/services/14711/vms/135");
		client.deleteVm();
	}
	
}
