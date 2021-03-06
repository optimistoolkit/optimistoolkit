
package es.arsys;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
//@WebServiceClient(name = "serviceManager", targetNamespace = "http://arsys.es/", wsdlLocation = "https://optimis-ws.servidoresdns.net:8443/OptimisWebService/serviceManager/serviceManager.asmx?wsdl")
@WebServiceClient(name = "serviceManager", targetNamespace = "http://arsys.es/", wsdlLocation = "https://optimis-ws.servidoresdns.net:8443/OptimisWebService/serviceManager/serviceManager.asmx?wsdl")
public class ServiceManager
    extends Service
{

    private final static URL SERVICEMANAGER_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(es.arsys.ServiceManager.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = es.arsys.ServiceManager.class.getResource(".");
            url = new URL(baseUrl, "https://optimis-ws.servidoresdns.net:8443/OptimisWebService/serviceManager/serviceManager.asmx?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'https://optimis-ws.servidoresdns.net:8443/OptimisWebService/serviceManager/serviceManager.asmx?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        SERVICEMANAGER_WSDL_LOCATION = url;
    }

    public ServiceManager(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServiceManager() {
        super(SERVICEMANAGER_WSDL_LOCATION, new QName("http://arsys.es/", "serviceManager"));
    }

    /**
     * 
     * @return
     *     returns ServiceManagerSoap
     */
    @WebEndpoint(name = "serviceManagerSoap")
    public ServiceManagerSoap getServiceManagerSoap() {
        return super.getPort(new QName("http://arsys.es/", "serviceManagerSoap"), ServiceManagerSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServiceManagerSoap
     */
    @WebEndpoint(name = "serviceManagerSoap")
    public ServiceManagerSoap getServiceManagerSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://arsys.es/", "serviceManagerSoap"), ServiceManagerSoap.class, features);
    }

}
