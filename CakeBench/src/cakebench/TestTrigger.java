/*
 * TestTrigger.java
 */
package cakebench;

import com.cakemail.api.*;

public class TestTrigger extends TestBase {

  public void Run(String actionName, ParamDictionary params, ParamDictionary subParams) throws Exception {
    PrepareRun(actionName, params, subParams);

    switch (CakeUtils.Action.toAction(actionName)) {
      case Trigger_Create:
        res.put("id", CakeTrigger.Create(pars));
        break;

      case Trigger_GetList:
        res = CakeTrigger.GetList(pars);
        break;

      case Trigger_GetLog:
        res = CakeTrigger.GetLog(pars);
        break;

      case Trigger_GetLinks:
        res = CakeTrigger.GetLinks(pars);
        break;

      case Trigger_GetLinksLog:
        res = CakeTrigger.GetLinksLog(pars);
        break;

      case Trigger_GetInfo:
        res = CakeTrigger.GetInfo(pars);
        break;

      case Trigger_SetInfo:
        res.put("result", CakeTrigger.SetInfo(pars));
        break;

      case Trigger_Unleash:
        res.put("result", CakeTrigger.Unleash(pars));
        break;
        
      default:
        res.put("result", "Method not supported");
        break;
    }

    TraceList(res);

    Trace("OK");
  }
}
