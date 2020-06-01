package plugin;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PluginInterface {
    public String auth(JSONObject authInfo, HttpServletRequest request, HttpServletResponse response);
}
