package org.readbook.task;

import org.json.JSONObject;
import org.readbook.clz.MyApplication;
import org.readbook.entity.BaseRequest;
import org.readbook.entity.User;
import org.readbook.res.Constants;
import org.readbook.utils.LogUtil;

import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

/**
#获取用户信息 Setting/getUserInfo
###功能
-------
1. 获取用户基本信息，QQ账号，和支付宝信息
 */
public class GetUserInfoTask extends BaseTask {

	public GetUserInfoTask(BaseRequest request, Handler handler) {
		super(request, handler);
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			setRequestParams();
			String resultJson = httpHelper.httpPost(Constants.Host.getUserInfo, map);
			LogUtil.logD(LogUtil.TAG, "------GetUserInfoTask receiver-------" + resultJson);
			JSONObject dataObject = new JSONObject(resultJson);
			if (dataObject.getInt("status") == 0) {
			    JSONObject jsonObject = dataObject.getJSONObject("data");
			    Gson gson = new Gson();
			    User user = gson.fromJson(jsonObject.toString(), User.class);
			    // save common property
			    MyApplication.getInstance().setIsEmailBind(user.getIsEmailBind());
			    MyApplication.getInstance().setIsPhoneBind(user.getIsPhoneBind());
			    MyApplication.getInstance().setName(user.getName());
			    MyApplication.getInstance().setEmail(user.getEmail());
			    MyApplication.getInstance().setPhoneNumber(user.getPhone());
			    MyApplication.getInstance().setQQ(user.getQq());
			    if(handler != null){
			    	Message msg = handler.obtainMessage();
			    	msg.obj = user;
			    	handler.sendMessage(msg);	
			    }
			} else {
				if(handler != null){
					Message msg = handler.obtainMessage();
					msg.obj = dataObject.getString("info");
					msg.what = -1;
					handler.sendMessage(msg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(handler != null){
				Message msg = handler.obtainMessage();
				msg.obj = Constants.net_error;
				msg.what = -1;
				handler.sendMessage(msg);
			}
		}
		return null;
	}
}
