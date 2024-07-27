package lym.com.api.service.commons;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ResultAction<T extends Serializable> {

	private boolean result;
	private String message;
	private int codeInfo;
	private String codeResult;
	private T entity;
	private List<T> listEntity;

	public ResultAction() {
		// TODO Auto-generated constructor stub
	}

	public ResultAction(boolean result) {
		// TODO Auto-generated constructor stub
		this.result = result;
	}

	public ResultAction(boolean result, int codeInfo, String codeResult, String message) {
		// TODO Auto-generated constructor stub
		this.result = result;
		this.message = message;
		this.codeInfo=codeInfo;
		this.codeResult=codeResult;
		this.message=message;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(int codeInfo) {
		this.codeInfo = codeInfo;
	}

	public String getCodeResult() {
		return codeResult;
	}

	public void setCodeResult(String codeResult) {
		this.codeResult = codeResult;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
	
	public List<T> getListEntity() {
		return listEntity;
	}
	public void setListEntity(List<T> listEntity) {
		this.listEntity = listEntity;
	}
	
	

}
