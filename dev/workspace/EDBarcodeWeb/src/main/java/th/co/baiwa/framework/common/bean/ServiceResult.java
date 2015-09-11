package th.co.baiwa.framework.common.bean;

public class ServiceResult<T extends Object> {
	
	private T result;
	private boolean success;
	
	
	public ServiceResult() {
		super();
		this.success = true;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}