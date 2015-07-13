/*
 * TestList.java
 */
package cakebench;

import com.cakemail.api.*;

public class TestList extends TestBase {

  public void Run(String actionName, ParamDictionary params, ParamDictionary subParams) throws Exception {
    PrepareRun(actionName, params, subParams);

    switch (CakeUtils.Action.toAction(actionName)) {
      case List_Create:
        res.put("id", CakeList.Create(pars));
        break;

      case List_Delete:
        res.put("result", CakeList.Delete(pars));
        break;

      case List_GetList:
        res = CakeList.GetList(pars);
        break;

      case List_GetInfo:
        res = CakeList.GetInfo(pars);
        break;

      case List_SetInfo:
        res.put("result", CakeList.SetInfo(pars));
        break;

      case List_EditStructure:
        res.put("result", CakeList.EditStructure(pars));
        break;

      case List_GetFields:
        res = CakeList.GetFields(pars);
        break;

      case List_Import:
        pars.put("record", new CakeDictionary());
        ((CakeDictionary) pars.get("record")).put("0", pars.get("data"));
        pars.remove("data");
        res = CakeList.Import(pars);
        break;

      case List_Show:
        res = CakeList.Show(pars);
        break;

      case List_Search:
        res = CakeList.Search(pars);
        break;

      case List_SubscribeEmail:
        res.put("id", CakeList.SubscribeEmail(pars));
        break;

      case List_UnsubscribeEmail:
        res.put("result", CakeList.UnsubscribeEmail(pars));
        break;

      case List_DeleteEmail:
        res.put("result", CakeList.DeleteEmail(pars));
        break;

      case List_DeleteRecord:
        res.put("result", CakeList.DeleteRecord(pars));
        break;

      case List_GetRecord:
        res = CakeList.GetRecord(pars);
        break;

      case List_UpdateRecord:
        res.put("result", CakeList.UpdateRecord(pars));
        break;

      case List_AddTestEmail:
        res.put("result", CakeList.AddTestEmail(pars));
        break;

      case List_GetTestEmails:
        res = CakeList.GetTestEmails(pars);
        break;

      case List_DeleteTestEmail:
        res.put("result", CakeList.DeleteTestEmail(pars));
        break;

      case List_CreateSublist:
        res.put("sublist_id", CakeList.CreateSublist(pars));
        break;

      case List_DeleteSublist:
        res.put("result", CakeList.DeleteSublist(pars));
        break;

      case List_TestSublist:
        res = CakeList.TestSublist(pars);
        break;

      case List_GetSublists:
        res = CakeList.GetSublists(pars);
        break;

      case List_Upload:
        res = CakeList.Upload(pars);
        break;

      default:
        res.put("result", "Method not supported");
        break;
    }

    TraceList(res);

    Trace("OK");
  }
}
