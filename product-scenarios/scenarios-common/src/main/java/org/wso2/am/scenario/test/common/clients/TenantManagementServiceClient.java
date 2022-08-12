package org.wso2.am.scenario.test.common.clients;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.axis2.AxisFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.integration.common.admin.client.utils.AuthenticateStubUtil;
import org.wso2.carbon.tenant.mgt.stub.TenantMgtAdminServiceExceptionException;
import org.wso2.carbon.tenant.mgt.stub.TenantMgtAdminServiceStub;
import org.wso2.carbon.tenant.mgt.stub.beans.xsd.TenantInfoBean;

public class TenantManagementServiceClient {
    private TenantMgtAdminServiceStub tenantMgtAdminServiceStub;
    private static final Log log = LogFactory.getLog(org.wso2.carbon.integration.common.admin.client.TenantManagementServiceClient.class);

    public TenantManagementServiceClient(String backEndURL, String sessionCookie) throws AxisFault {
        String serviceName = "TenantMgtAdminService";
        String endPoint = backEndURL + serviceName;
        this.tenantMgtAdminServiceStub = new TenantMgtAdminServiceStub(endPoint);
        AuthenticateStubUtil.authenticateStub(sessionCookie, this.tenantMgtAdminServiceStub);
        tenantMgtAdminServiceStub._getServiceClient().getOptions().setProperty(
                org.apache.axis2.transport.http.HTTPConstants.CHUNKED, Boolean.FALSE);
        tenantMgtAdminServiceStub._getServiceClient().getOptions().setTimeOutInMilliSeconds(20000);
    }

    public void addTenant(String domainName, String password, String firstName, String usagePlan) throws RemoteException, TenantMgtAdminServiceExceptionException {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        TenantInfoBean tenantInfoBean = new TenantInfoBean();
        tenantInfoBean.setActive(true);
        tenantInfoBean.setEmail(firstName + "@" + domainName);
        tenantInfoBean.setAdmin(firstName);
        tenantInfoBean.setAdminPassword(password);
        tenantInfoBean.setUsagePlan(usagePlan);
        tenantInfoBean.setLastname(firstName + "wso2automation");
        tenantInfoBean.setSuccessKey("true");
        tenantInfoBean.setCreatedDate(calendar);
        tenantInfoBean.setTenantDomain(domainName);
        tenantInfoBean.setFirstname(firstName);

        try {
            TenantInfoBean tenantInfoBeanGet = this.tenantMgtAdminServiceStub.getTenant(domainName);
            if (!tenantInfoBeanGet.getActive() && tenantInfoBeanGet.getTenantId() != 0) {
                this.tenantMgtAdminServiceStub.activateTenant(domainName);
                log.info("Tenant domain " + domainName + " Activated successfully");
            } else if (!tenantInfoBeanGet.getActive() && tenantInfoBeanGet.getTenantId() == 0) {
                this.tenantMgtAdminServiceStub.addTenant(tenantInfoBean);
                this.tenantMgtAdminServiceStub.activateTenant(domainName);
                log.info("Tenant domain " + domainName + " created and activated successfully");
            } else {
                log.info("Tenant domain " + domainName + " already registered");
            }

        } catch (RemoteException var10) {
            log.error("RemoteException thrown while adding user/tenants : ", var10);
            throw new RemoteException("RemoteException thrown while adding user/tenants : ", var10);
        }
    }

    public void addTenant(TenantInfoBean tenantInfoBean) throws RemoteException, TenantMgtAdminServiceExceptionException {
        this.tenantMgtAdminServiceStub.addTenant(tenantInfoBean);
    }

    public void activateTenant(String tenantDomain) throws RemoteException, TenantMgtAdminServiceExceptionException {
        this.tenantMgtAdminServiceStub.activateTenant(tenantDomain);
    }

    public void deleteTenant(String domainName) {
        try {
            this.tenantMgtAdminServiceStub.deactivateTenant(domainName);
        } catch (RemoteException var3) {
            log.error("Error while reach the tenant");
        } catch (TenantMgtAdminServiceExceptionException var4) {
            log.error("No such tenant found");
        }

    }

    public void deactivateTenant(String domainName) throws RemoteException, TenantMgtAdminServiceExceptionException {
        try {
            this.tenantMgtAdminServiceStub.deactivateTenant(domainName);
        } catch (RemoteException var3) {
            log.error("Error while reach the tenant");
            throw new RemoteException("RemoteException thrown while retrieving user/tenants : ", var3);
        } catch (TenantMgtAdminServiceExceptionException var4) {
            log.error("No such tenant found");
            throw new TenantMgtAdminServiceExceptionException("RemoteException thrown while deactivating tenant : ", var4);
        }
    }

    public TenantInfoBean getTenant(String tenantDomain) throws TenantMgtAdminServiceExceptionException, RemoteException {
        try {
            TenantInfoBean getTenantBean = this.tenantMgtAdminServiceStub.getTenant(tenantDomain);
            return getTenantBean;
        } catch (RemoteException var4) {
            log.error("RemoteException thrown while retrieving user/tenants : ", var4);
            throw new RemoteException("RemoteException thrown while retrieving user/tenants : ", var4);
        }
    }

    public void updateTenant(TenantInfoBean infoBean) throws TenantMgtAdminServiceExceptionException, RemoteException {
        try {
            this.tenantMgtAdminServiceStub.updateTenant(infoBean);
        } catch (RemoteException var3) {
            log.error("RemoteException thrown while retrieving user/tenants : ", var3);
            throw new RemoteException("RemoteException thrown while retrieving user/tenants : ", var3);
        }
    }
}

