/*
 * TestMailing.java
 */
package cakebench;

import com.cakemail.api.*;

public class TestMailing extends TestBase {

  public void Run(String actionName, ParamDictionary params, ParamDictionary subParams) throws Exception {
    PrepareRun(actionName, params, subParams);

    switch (CakeUtils.Action.toAction(actionName)) {
      case Mailing_Create:
        res.put("id", CakeMailing.Create(pars));
        break;

      case Mailing_Delete:
        res.put("result", CakeMailing.Delete(pars));
        break;

      case Mailing_GetList:
        res = CakeMailing.GetList(pars);
        break;

      case Mailing_GetInfo:
        res = CakeMailing.GetInfo(pars);
        break;

      case Mailing_SetInfo:
        res.put("result", CakeMailing.SetInfo(pars));
        break;

      case Mailing_GetEmailMessage:
        res = CakeMailing.GetEmailMessage(pars);
        break;

      case Mailing_GetHtmlMessage:
        res.put("result", CakeMailing.GetHtmlMessage(pars));
        break;

      case Mailing_GetTextMessage:
        res.put("result", CakeMailing.GetTextMessage(pars));
        break;

      case Mailing_SendTestEmail:
        res.put("result", CakeMailing.SendTestEmail(pars));
        break;

      case Mailing_Schedule:
        res.put("result", CakeMailing.Schedule(pars));
        break;

      case Mailing_Unschedule:
        res.put("result", CakeMailing.Unschedule(pars));
        break;

      case Mailing_Suspend:
        res.put("result", CakeMailing.Suspend(pars));
        break;

      case Mailing_Resume:
        res.put("result", CakeMailing.Resume(pars));
        break;

      case Mailing_GetStats:
        res = CakeMailing.GetStats(pars);
        break;

      case Mailing_GetLog:
        res = CakeMailing.GetLog(pars);
        break;

      case Mailing_GetLinksLog:
        res = CakeMailing.GetLinksLog(pars);
        break;

      case Mailing_GetLinks:
        res = CakeMailing.GetLinks(pars);
        break;

      default:
        res.put("result", "Method not supported");
        break;
    }

    TraceList(res);

    Trace("OK");
  }
}
