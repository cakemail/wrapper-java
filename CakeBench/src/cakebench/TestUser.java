/*
 * TestUser.java
 */
package cakebench;

import com.cakemail.api.*;

public class TestUser extends TestBase {

  public void Run(String actionName, ParamDictionary params, ParamDictionary subParams) throws Exception {
    PrepareRun(actionName, params, subParams);

    switch (CakeUtils.Action.toAction(actionName)) {
      case User_CheckPermission:
        res.put("result", CakeUser.CheckPermission(pars));
        break;
      
      case User_Create:
        res = CakeUser.Create(pars);
        break;

      case User_GetList:
        res = CakeUser.GetList(pars);
        break;

      case User_GetInfo:
        res = CakeUser.GetInfo(pars);
        break;

      case User_SetInfo:
        res.put("result", CakeUser.SetInfo(pars));
        break;

      case User_Login:
        res = CakeUser.Login(pars);
        break;

      case User_PasswordRecovery:
        res.put("result", CakeUser.PasswordRecovery(pars));
        break;

      default:
        res.put("result", "Method not supported");
        break;
    }

    TraceList(res);

    Trace("OK");
  }
}
