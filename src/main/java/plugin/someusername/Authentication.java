package plugin.someusername;

import com.alibaba.fastjson.JSONObject;
import com.para4digm.yumcdpl.util.BusDict;
import com.para4digm.yumcdpl.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import plugin.PluginInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class Authentication implements PluginInterface {

    @Override
    public String auth(JSONObject authInfo, HttpServletRequest request, HttpServletResponse response) {
        /*
        for example
        String userName = (String)authInfo.get("username");
        return userName;
         */
        String userName = (String) authInfo.get(BusDict.UserName);
        return userName;
//        return "4pdadmin";
    }

}
