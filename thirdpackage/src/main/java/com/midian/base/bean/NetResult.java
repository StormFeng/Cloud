package com.midian.base.bean;


import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.midian.base.app.AppException;

/**
 * 结果
 * 
 * @author XuYang
 * 
 */
public class NetResult extends NetBase {
	public String staus = "";
	public String message = "";
	public static Gson gson = new Gson();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isOK() {
		return "ok!".equals(staus);
	}

	public boolean noData() {
		return "no data".equals(message);
	}

	public static NetResult parse(String json) throws AppException {
		NetResult res = new NetResult();
		try {
			res = gson.fromJson(json, NetResult.class);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw AppException.json(e);
		}
		return res;
	}

    public String getStatus() {
        return staus;
    }

    public void setStatus(String staus) {
        this.staus = staus;
    }
}
