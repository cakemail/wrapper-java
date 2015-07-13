/*
 * TestBase.java
 */
package cakebench;

import com.cakemail.api.*;

public class TestBase {
  protected String actName;
  protected CakeDictionary pars;
  protected CakeDictionary res;
  protected CakeDictionary subPars;
  public String TraceString = "";

  public void TraceList(CakeDictionary dictionary)
  {
    Trace("[" + actName + "]");
    Trace("");
    Trace(CakeUtils.TraceDictionary(dictionary));
  }

  public void Trace(String s)
  {
    TraceString += s + "\n";
  }

  public void Trace_Clear()
  {
    TraceString = "";
  }

  protected void PrepareRun(String actionName, ParamDictionary params, ParamDictionary subParams) throws Exception
  {
    Trace_Clear();

    actName = actionName;

    res = new CakeDictionary();

    pars = ParamUtils.ParamsToCakeDictionary(params, true, new String[] {"user_key"});
    if (pars == null) pars = new CakeDictionary();

    if (pars.containsKey("user_key") && (pars.get("user_key") == null || pars.get("user_key").toString().equals(""))) {
      pars.put("user_key", CakeUtils.GetUserKey(SettingsUtils.EMAIL, SettingsUtils.PASSWORD));
    }

    subPars = ParamUtils.ParamsToCakeDictionary(subParams, true);
    if (subPars != null) pars.put("data", subPars);
  }

  public void Run(String actionName, ParamDictionary parameters, ParamDictionary subParameters) throws Exception
  {
  }
}
