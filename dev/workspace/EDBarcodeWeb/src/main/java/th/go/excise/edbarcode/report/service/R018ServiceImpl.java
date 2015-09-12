package th.go.excise.edbarcode.report.service;

import org.springframework.stereotype.Service;

import th.go.excise.edbarcode.report.domain.R018HDomain;

@Service("r018Service")
public class R018ServiceImpl implements R018Service {

	@Override
	public R018HDomain getMockReportData() {
	
		
		return Report18Test.getR018Test();
	}

}
