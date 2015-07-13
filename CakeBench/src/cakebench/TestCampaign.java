/*
 * TestCampaign.java
 */
package cakebench;

import com.cakemail.api.*;

public class TestCampaign extends TestBase {

  public void Run(String actionName, ParamDictionary params, ParamDictionary subParams) throws Exception {
    PrepareRun(actionName, params, subParams);

    switch (CakeUtils.Action.toAction(actionName)) {
      case Campaign_Create:
        res.put("id", CakeCampaign.Create(pars));
        break;

      case Campaign_Delete:
        res.put("result", CakeCampaign.Delete(pars));
        break;

      case Campaign_GetList:
        res = CakeCampaign.GetList(pars);
        break;

      case Campaign_GetInfo:
        res = CakeCampaign.GetInfo(pars);
        break;

      case Campaign_SetInfo:
        res.put("result", CakeCampaign.SetInfo(pars));
        break;


      default:
        res.put("result", "Method not supported");
        break;
    }

    TraceList(res);

    Trace("OK");
  }
}
