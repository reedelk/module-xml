package de.codecentric.reedelk.xml.component;

import de.codecentric.reedelk.runtime.api.annotation.*;
import de.codecentric.reedelk.runtime.api.component.Implementor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

import java.util.Map;

@Shared
@Component(service = XPathConfiguration.class, scope = ServiceScope.PROTOTYPE)
public class XPathConfiguration implements Implementor {

    @Property("Prefix > Namespace mappings")
    @TabGroup("Prefix > Namespace mappings")
    @KeyName("Prefix Name")
    @ValueName("Namespace")
    @Example("soap > http://schemas.xmlsoap.org/soap/envelope/<br>" +
            "sec > http://schemas.xmlsoap.org/soap/security/2000-12")
    @Description("Prefixes and Namespaces mappings used in the XPath expression.")
    private Map<String,String> prefixNamespaceMap;

    public void setPrefixNamespaceMap(Map<String, String> prefixNamespaceMap) {
        this.prefixNamespaceMap = prefixNamespaceMap;
    }

    public Map<String, String> getPrefixNamespaceMap() {
        return prefixNamespaceMap;
    }
}
