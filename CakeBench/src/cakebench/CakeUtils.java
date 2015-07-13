/*
 * CakeUtils.java
 */
package cakebench;

import com.cakemail.api.*;
import java.util.Enumeration;

public class CakeUtils {

  public enum Action {

    _Campaign_,
    Campaign_Create,
    Campaign_Delete,
    Campaign_GetList,
    Campaign_GetInfo,
    Campaign_SetInfo,
    _Mailing_,
    Mailing_Create,
    Mailing_Delete,
    Mailing_GetList,
    Mailing_GetInfo,
    Mailing_SetInfo,
    Mailing_GetEmailMessage,
    Mailing_GetHtmlMessage,
    Mailing_GetTextMessage,
    Mailing_SendTestEmail,
    Mailing_Schedule,
    Mailing_Unschedule,
    Mailing_Suspend,
    Mailing_Resume,
    Mailing_GetStats,
    Mailing_GetLog,
    Mailing_GetLinksLog,
    Mailing_GetLinks,
    _List_,
    List_Create,
    List_Delete,
    List_GetList,
    List_GetInfo,
    List_SetInfo,
    List_EditStructure,
    List_GetFields,
    List_Import,
    List_Show,
    List_Search,
    List_SubscribeEmail,
    List_UnsubscribeEmail,
    List_DeleteEmail,
    List_DeleteRecord,
    List_GetRecord,
    List_UpdateRecord,
    List_AddTestEmail,
    List_GetTestEmails,
    List_DeleteTestEmail,
    List_CreateSublist,
    List_DeleteSublist,
    List_TestSublist,
    List_GetSublists,
    List_Upload,
    _Client_,
    Client_Create,
    Client_Activate,
    Client_AddCredits,
    Client_GetCreditBalance,
    Client_GetCreditTransactions,    
    Client_GetList,
    Client_GetInfo,
    Client_ResetCredits,    
    Client_SetInfo,
    Client_GetTimezones,
    Client_Search,
    _User_,
    User_CheckPermission,
    User_Create,
    User_GetList,
    User_GetInfo,
    User_SetInfo,
    User_Login,
    User_PasswordRecovery,
    _Group_,
    Group_Create,
    Group_Delete,
    Group_GetInfo,
    Group_GetList,
    Group_SetInfo,
    Group_AddUser,
    Group_RemoveUser,
    _Template_,
    Template_Create,
    Template_Delete,
    Template_GetList,
    Template_GetInfo,
    Template_SetInfo,
    _Trigger_,
    Trigger_Create,
    Trigger_GetList,
    Trigger_GetLog,
    Trigger_GetLinks,
    Trigger_GetLinksLog,
    Trigger_GetInfo,
    Trigger_SetInfo,
    Trigger_Unleash,
    _SuppressionList_,
    SuppressionList_ImportDomains,
    SuppressionList_ImportEmails,
    SuppressionList_ImportLocalparts,
    SuppressionList_ExportDomains,
    SuppressionList_ExportEmails,
    SuppressionList_ExportLocalparts,
    SuppressionList_DeleteDomains,
    SuppressionList_DeleteEmails,
    SuppressionList_DeleteLocalparts,
    _Relay_,
    Relay_Send,
    Relay_GetLogs,
    _Country_,
    Country_GetList,
    Country_GetProvinces;

    public static Action toAction(String str) {
      return valueOf(str);
    }
  }

  public static String GetUserKey(String email, String password) throws Exception {
    CakeDictionary pars = new CakeDictionary();

    pars.put("email", email);
    pars.put("password", password);
    CakeDictionary userInfo = CakeUser.Login(pars);

    return (userInfo.get("user_key").toString());
  }

  public static String TraceDictionary(CakeDictionary dictionary) {
    StringBuilder sb = new StringBuilder();

    Enumeration<String> keys = dictionary.keys();
    while (keys.hasMoreElements()) {
      String key = keys.nextElement();
      Object value = dictionary.get(key);
      if (value instanceof CakeDictionary) {
        sb.append(TraceDictionary((CakeDictionary) value));
      } else {
        sb.append(key + "=" + value);
      }
      sb.append("\n");
    }

    return (sb.toString());
  }
}
