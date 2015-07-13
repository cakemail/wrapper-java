/*
 * TestClient.java
 */
package cakebench;

import com.cakemail.api.*;

public class TestClient extends TestBase {

  public void Run(String actionName, ParamDictionary params, ParamDictionary subParams) throws Exception {
    PrepareRun(actionName, params, subParams);

    switch (CakeUtils.Action.toAction(actionName)) {
      case Client_Create:
        res = CakeClient.Create(pars);
        break;

      case Client_Activate:
        res = CakeClient.Activate(pars);
        break;

      case Client_AddCredits:
        res.put("result", CakeClient.AddCredits(pars));
        break;

      case Client_GetCreditBalance:
        res.put("balance", CakeClient.GetCreditBalance(pars));
        break;

      case Client_GetCreditTransactions:
        res = CakeClient.GetCreditTransactions(pars);
        break;
        
      case Client_GetList:
        res = CakeClient.GetList(pars);
        break;

      case Client_GetInfo:
        res = CakeClient.GetInfo(pars);
        break;

      case Client_ResetCredits:
        res.put("result", CakeClient.ResetCredits(pars));
        break;
        
      case Client_SetInfo:
        res.put("result", CakeClient.SetInfo(pars));
        break;

      case Client_GetTimezones:
        res = CakeClient.GetTimezones(pars);
        break;

      case Client_Search:
        res = CakeClient.Search(pars);
        break;

      default:
        res.put("result", "Method not supported");
        break;
    }

    TraceList(res);

    Trace("OK");
  }
}
