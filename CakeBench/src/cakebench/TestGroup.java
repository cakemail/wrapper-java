/*
 * TestGroup.java
 */
package cakebench;

import com.cakemail.api.*;

public class TestGroup extends TestBase {

  public void Run(String actionName, ParamDictionary params, ParamDictionary subParams) throws Exception {
    PrepareRun(actionName, params, subParams);

    switch (CakeUtils.Action.toAction(actionName)) {
      case Group_Create:
        res.put("id", CakeGroup.Create(pars));
        break;

      case Group_Delete:
        res.put("result", CakeGroup.Delete(pars));
        break;

      case Group_GetInfo:
        res = CakeGroup.GetInfo(pars);
        break;

      case Group_GetList:
        res = CakeGroup.GetList(pars);
        break;

      case Group_SetInfo:
        res.put("result", CakeGroup.SetInfo(pars));
        break;

      case Group_AddUser:
        res.put("result", CakeGroup.AddUser(pars));
        break;

      case Group_RemoveUser:
        res.put("result", CakeGroup.AddUser(pars));
        break;

      default:
        res.put("result", "Method not supported");
        break;
    }

    TraceList(res);

    Trace("OK");
  }
}
