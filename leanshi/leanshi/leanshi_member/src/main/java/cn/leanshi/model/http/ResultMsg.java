package cn.leanshi.model.http;

import lombok.Data;

/**
 * @author :ldq
 * @date:2018/8/15
 * @description:leanshi_member cn.leanshi.model.http
 */
@Data
public class ResultMsg<T> {

	private boolean code;
	private String msg;
	private T data;

	public boolean isCode() {
		return code;
	}

	public void setCode(boolean code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResultMsg() {
	}

	private ResultMsg(boolean code, String msg) {
		this.msg = msg;
		this.code = code;
	}
	public static ResultMsg newInstance(boolean code,String msg){
		return new ResultMsg(code,msg);
	}
}
