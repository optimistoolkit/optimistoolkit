/*
 * Copyright (c) 2013 National Technical University of Athens (NTUA)
 *	
 *   							MIT License
 *   
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


package eu.optimis.ac.smanalyzer.idInfo;

import org.apache.log4j.Logger;

public class ReadIdsFromSM {

	public String spId="";
	public String serviceId="";
	
        private String tagSM =  "ServiceManifest";
	private String tagVM = "VirtualMachineDescription";
	private String attributeSP = "serviceProviderId";
	private String attributeSI = "serviceId";
        
	public ReadIdsFromSM(String serviceManifest,Logger log,Boolean DisplayAllLogs)
	{
		spId = ServiceManifestXMLProcessor.getAttribute(serviceManifest, tagSM, attributeSP,log);
                if(DisplayAllLogs)
		log.info("SP ID : "+spId);
		
                serviceId = ServiceManifestXMLProcessor.getAttribute(serviceManifest, tagVM, attributeSI,log);
		if(DisplayAllLogs)
                log.info("service ID : "+serviceId);
		
	}//constructor
	
        public ReadIdsFromSM(String serviceManifest)
	{
		
		spId = ServiceManifestXMLProcessor.getAttribute(serviceManifest, tagSM, attributeSP);
		
		serviceId = ServiceManifestXMLProcessor.getAttribute(serviceManifest, tagVM, attributeSI);
		
	}//constructor
	
}//class