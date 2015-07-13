/*
 * TestSuppressionList.java
 */
package cakebench;

import com.cakemail.api.*;

public class TestSuppressionList extends TestBase {

  public void Run(String actionName, ParamDictionary params, ParamDictionary subParams) throws Exception {
    PrepareRun(actionName, params, subParams);

    switch (CakeUtils.Action.toAction(actionName)) {
      case SuppressionList_ImportDomains:
        res = CakeSuppressionList.ImportDomains(pars);
        break;

      case SuppressionList_ImportEmails:
        res = CakeSuppressionList.ImportEmails(pars);
        break;

      case SuppressionList_ImportLocalparts:
        res = CakeSuppressionList.ImportLocalparts(pars);
        break;

      case SuppressionList_ExportDomains:
        res = CakeSuppressionList.ExportDomains(pars);
        break;

      case SuppressionList_ExportEmails:
        res = CakeSuppressionList.ExportEmails(pars);
        break;

      case SuppressionList_ExportLocalparts:
        res = CakeSuppressionList.ExportLocalparts(pars);
        break;

      case SuppressionList_DeleteDomains:
        res = CakeSuppressionList.DeleteDomains(pars);
        break;

      case SuppressionList_DeleteEmails:
        res = CakeSuppressionList.DeleteEmails(pars);
        break;

      case SuppressionList_DeleteLocalparts:
        res = CakeSuppressionList.DeleteLocalparts(pars);
        break;

      default:
        res.put("result", "Method not supported");
        break;
    }

    TraceList(res);

    Trace("OK");
  }
}
