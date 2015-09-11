package th.go.excise.edbarcode.report.service;

import java.util.List;

import th.co.baiwa.framework.common.bean.ServiceResult;
import th.go.excise.edbarcode.report.domain.ExRptDomain;

public interface ExRptService {
	
	public ServiceResult<List<ExRptDomain>> getMockReportData();
	
}
