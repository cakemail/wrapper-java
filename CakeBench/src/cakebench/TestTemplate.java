/*
 * TestTemplate.java
 */
package cakebench;

import com.cakemail.api.*;

public class TestTemplate extends TestBase {

  public void Run(String actionName, ParamDictionary params, ParamDictionary subParams) throws Exception {
    PrepareRun(actionName, params, subParams);

    switch (CakeUtils.Action.toAction(actionName)) {
      case Template_Create:
        res.put("id", CakeTemplate.Create(pars));
        break;

      case Template_Delete:
        res.put("result", CakeTemplate.Delete(pars));
        break;

      case Template_GetList:
        res = CakeTemplate.GetList(pars);
        break;

      case Template_GetInfo:
        res = CakeTemplate.GetInfo(pars);
        break;

      case Template_SetInfo:
        res.put("result", CakeTemplate.SetInfo(pars));
        break;

      default:
        res.put("result", "Method not supported");
        break;
    }

    TraceList(res);

    Trace("OK");
  }
}
