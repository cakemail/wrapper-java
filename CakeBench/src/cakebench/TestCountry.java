/*
 * TestCountry.java
 */
package cakebench;

import com.cakemail.api.*;

public class TestCountry extends TestBase {

  public void Run(String actionName, ParamDictionary params, ParamDictionary subParams) throws Exception {
    PrepareRun(actionName, params, subParams);

    switch (CakeUtils.Action.toAction(actionName)) {
      case Country_GetList:
        res = CakeCountry.GetList(pars);
        break;

      case Country_GetProvinces:
        res = CakeCountry.GetProvinces(pars);
        break;

      default:
        res.put("result", "Method not supported");
        break;
    }

    TraceList(res);

    Trace("OK");
  }
}
