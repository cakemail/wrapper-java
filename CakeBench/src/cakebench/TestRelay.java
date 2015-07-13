/*
 * TestRelay.java
 */
package cakebench;

import com.cakemail.api.*;

public class TestRelay extends TestBase {

  public void Run(String actionName, ParamDictionary params, ParamDictionary subParams) throws Exception {
    PrepareRun(actionName, params, subParams);

    switch (CakeUtils.Action.toAction(actionName)) {
      case Relay_Send:
        res.put("result", CakeRelay.Send(pars));
        break;

      case Relay_GetLogs:
        res = CakeRelay.GetLogs(pars);
        break;

      default:
        res.put("result", "Method not supported");
        break;
    }

    TraceList(res);

    Trace("OK");
  }
}
