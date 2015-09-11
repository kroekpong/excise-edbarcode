package th.go.excise.edbarcode.report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.co.baiwa.framework.common.bean.ServiceResult;
import th.go.excise.edbarcode.report.domain.ExRptDomain;

@Service("exRptService")
public class ExRptServiceImpl implements ExRptService {

	@Override
	public ServiceResult<List<ExRptDomain>> getMockReportData() {
		
		List<ExRptDomain> domainList = new ArrayList<ExRptDomain>();
		
		ExRptDomain domain = null;
		for (int i = 0; i < 10; i++) {
			domain = new ExRptDomain();
			if (i <= 4) {
				domain.setLicenseType("โรงใหญ่");
			} else {
				domain.setLicenseType("โรงเล็ก");
			}
			domain.setCompanyName("Company " + (i + 1));
			domain.setFactoryName("Factory " + (i + 1));
			domainList.add(domain);
		}
		
		ServiceResult<List<ExRptDomain>> serviceResult = new ServiceResult<List<ExRptDomain>>();
		serviceResult.setResult(domainList);
		
		return serviceResult;
	}

}
