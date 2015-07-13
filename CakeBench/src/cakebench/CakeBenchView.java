/*
 * CakeBenchView.java
 */
package cakebench;

import com.cakemail.api.*;

import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;

/**
 * The application's main frame.
 */
public class CakeBenchView extends FrameView {

  public CakeBenchView(SingleFrameApplication app) {
    super(app);

    initComponents();

    // status bar initialization - message timeout, idle icon and busy animation, etc
    ResourceMap resourceMap = getResourceMap();
    int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
    messageTimer = new Timer(messageTimeout, new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        statusMessageLabel.setText("");
      }
    });
    messageTimer.setRepeats(false);
    int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
    for (int i = 0; i < busyIcons.length; i++) {
      busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
    }
    busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
        statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
      }
    });
    idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
    statusAnimationLabel.setIcon(idleIcon);
    progressBar.setVisible(false);

    // connecting action tasks to status bar via TaskMonitor
    TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
    taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

      public void propertyChange(java.beans.PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if ("started".equals(propertyName)) {
          if (!busyIconTimer.isRunning()) {
            statusAnimationLabel.setIcon(busyIcons[0]);
            busyIconIndex = 0;
            busyIconTimer.start();
          }
          progressBar.setVisible(true);
          progressBar.setIndeterminate(true);
        } else if ("done".equals(propertyName)) {
          busyIconTimer.stop();
          statusAnimationLabel.setIcon(idleIcon);
          progressBar.setVisible(false);
          progressBar.setValue(0);
        } else if ("message".equals(propertyName)) {
          String text = (String) (evt.getNewValue());
          statusMessageLabel.setText((text == null) ? "" : text);
          messageTimer.restart();
        } else if ("progress".equals(propertyName)) {
          int value = (Integer) (evt.getNewValue());
          progressBar.setVisible(true);
          progressBar.setIndeterminate(false);
          progressBar.setValue(value);
        }
      }
    });

    Start();
  }

  private void Start() {
    try {
      CakeGlobals.LoadFromIniFile("CakeGlobals.ini");
    } catch (Exception ex) {
      Trace("EXCEPTION");
      Trace("Message=" + ex.getLocalizedMessage());
      Trace("StackTrace=" + Arrays.toString(ex.getStackTrace()).replaceAll(", ", "\n   "));
    }

    LoadActions();

    goButton.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent evt) {
        GoButtonClick(evt);
      }
    });
  }

  private void LoadActions() {
    actionsComboBox.removeAllItems();
    actionsComboBox.setModel(new DefaultComboBoxModel(CakeUtils.Action.values()));
    actionsComboBox.addItemListener(new ItemListener() {

      public void itemStateChanged(ItemEvent evt) {
        ActionChanged(evt);
      }
    });
  }

  private void ActionChanged(ItemEvent evt) {
    String actionName = evt.getItem().toString();

    ParamDictionary params = new ParamDictionary();
    ParamDictionary subParams = new ParamDictionary();

    switch (CakeUtils.Action.toAction(actionName)) {
      // Campaign
      case Campaign_Create:
        params.put("user_key", "");
        params.put("name", "");
        break;

      case Campaign_GetInfo:
      case Campaign_Delete:
        params.put("user_key", "");
        params.put("campaign_id", "");
        break;

      case Campaign_GetList:
        params.put("user_key", "");
        params.put("status", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        break;

      case Campaign_SetInfo:
        params.put("user_key", "");
        params.put("campaign_id", "");
        params.put("name", "");
        params.put("status", "");
        break;

      // Mailing
      case Mailing_Create:
        params.put("user_key", "");
        params.put("campaign_id", "");
        params.put("name", "");
        params.put("encoding", "");
        break;

      case Mailing_Delete:
      case Mailing_GetInfo:
      case Mailing_GetEmailMessage:
      case Mailing_GetHtmlMessage:
      case Mailing_GetTextMessage:
      case Mailing_Unschedule:
      case Mailing_Suspend:
      case Mailing_Resume:
        params.put("user_key", "");
        params.put("mailing_id", "");
        break;

      case Mailing_GetList:
        params.put("user_key", "");
        params.put("mailing_id", "");
        params.put("status", "");
        params.put("campaign_id", "");
        params.put("list_id", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        break;

      case Mailing_SetInfo:
        params.put("user_key", "");
        params.put("mailing_id", "");
        params.put("campaign_id", "");
        params.put("list_id", "");
        params.put("sublist_id", "");
        params.put("next_step", "");
        params.put("encoding", "");
        params.put("clickthru_html", "");
        params.put("clickthru_text", "");
        params.put("opening_stats", "");
        params.put("unsub_bottom_link", "");
        params.put("name", "");
        params.put("subject", "");
        params.put("sender_name", "");
        params.put("sender_email", "");
        params.put("html_message", "");
        params.put("text_message", "");
        break;

      case Mailing_SendTestEmail:
        params.put("user_key", "");
        params.put("mailing_id", "");
        params.put("test_email", "");
        params.put("test_type", "");
        break;

      case Mailing_Schedule:
        params.put("user_key", "");
        params.put("mailing_id", "");
        params.put("date", "");
        break;

      case Mailing_GetStats:
        params.put("user_key", "");
        params.put("mailing_id", "");
        params.put("information", "");
        break;

      case Mailing_GetLog:
        params.put("user_key", "");
        params.put("mailing_id", "");
        params.put("action", "");
        params.put("record_id", "");
        params.put("start_date", "");
        params.put("end_date", "");
        params.put("date", "");
        params.put("extra", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        params.put("uniques", "");
        break;

      case Mailing_GetLinksLog:
        params.put("user_key", "");
        params.put("mailing_id", "");
        params.put("start_date", "");
        params.put("end_date", "");
        break;

      case Mailing_GetLinks:
        params.put("user_key", "");
        params.put("mailing_id", "");
        params.put("status", "");
        break;

      // List
      case List_Create:
        params.put("user_key", "");
        params.put("name", "");
        params.put("sender_name", "");
        params.put("sender_email", "");
        subParams.put("first_name", "");
        subParams.put("last_name", "");
        break;

      case List_Delete:
      case List_GetInfo:
      case List_GetFields:
      case List_GetTestEmails:
      case List_GetSublists:
        params.put("user_key", "");
        params.put("list_id", "");
        break;

      case List_GetList:
        params.put("user_key", "");
        params.put("status", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        break;

      case List_SetInfo:
        params.put("user_key", "");
        params.put("list_id", "");
        params.put("sublist_id", "");
        params.put("name", "");
        params.put("query", "");
        params.put("language", "");
        params.put("status", "");
        params.put("policy", "");
        params.put("sender_name", "");
        params.put("sender_email", "");
        params.put("forward_page", "");
        params.put("goto_oi", "");
        params.put("goto_di", "");
        params.put("goto_oo", "");
        break;

      case List_EditStructure:
        params.put("user_key", "");
        params.put("list_id", "");
        params.put("action", "");
        params.put("field", "");
        params.put("type", "");
        break;

      case List_Import:
        params.put("user_key", "");
        params.put("list_id", "");
        subParams.put("email", "");
        subParams.put("first_name", "");
        subParams.put("last_name", "");
        break;

      case List_Show:
        params.put("user_key", "");
        params.put("list_id", "");
        params.put("status", "");
        params.put("bounce_type", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        break;

      case List_Search:
        params.put("user_key", "");
        params.put("list_id", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        subParams.put("email", "");
        subParams.put("first_name", "");
        break;

      case List_SubscribeEmail:
        params.put("user_key", "");
        params.put("list_id", "");
        params.put("email", "");
        params.put("no_triggers", "");
        subParams.put("first_name", "");
        subParams.put("last_name", "");
        break;

      case List_UnsubscribeEmail:
      case List_DeleteEmail:
      case List_AddTestEmail:
        params.put("user_key", "");
        params.put("list_id", "");
        params.put("email", "");
        break;

      case List_DeleteRecord:
      case List_GetRecord:
        params.put("user_key", "");
        params.put("list_id", "");
        params.put("record_id", "");
        break;

      case List_UpdateRecord:
        params.put("user_key", "");
        params.put("list_id", "");
        params.put("record_id", "");
        subParams.put("email", "");
        subParams.put("field1", "");
        break;

      case List_DeleteTestEmail:
        params.put("user_key", "");
        params.put("list_id", "");
        params.put("test_email_id", "");
        break;

      case List_CreateSublist:
        params.put("user_key", "");
        params.put("list_id", "");
        subParams.put("0_field", "");
        subParams.put("0_function", "");
        subParams.put("0_value", "");
        break;

      case List_DeleteSublist:
        params.put("user_key", "");
        params.put("sublist_id", "");
        break;

      case List_TestSublist:
        params.put("user_key", "");
        subParams.put("0_field", "");
        subParams.put("0_function", "");
        subParams.put("0_value", "");
        break;

      case List_Upload:
        params.put("user_key", "");
        break;

      // Client
      case Client_Create:
        params.put("user_key", "");
        params.put("company_name", "");
        params.put("admin_email", "");
        params.put("admin_first_name", "");
        params.put("admin_last_name", "");
        params.put("admin_password", "");
        params.put("admin_password_confirmation", "");
        params.put("contact_email", "");
        params.put("contact_first_name", "");
        params.put("contact_last_name", "");
        params.put("contact_password", "");
        params.put("contact_password_confirmation", "");
        params.put("manager_id", "");
        params.put("parent_id", "");
        params.put("currency", "");
        params.put("address1", "");
        params.put("address2", "");
        params.put("city", "");
        params.put("province_id", "");
        params.put("postal_code", "");
        params.put("country_id", "");
        params.put("website", "");
        params.put("phone", "");
        params.put("fax", "");
        params.put("contact_same_as_admin", "");
        params.put("admin_title", "");
        params.put("admin_office_phone", "");
        params.put("admin_mobile_phone", "");
        params.put("admin_language", "");
        params.put("admin_tiemzone_id", "");
        params.put("admin_language", "");
        params.put("contact_title", "");
        params.put("contact_language", "");
        params.put("contact_timezone_id", "");
        params.put("contact_office_phone", "");
        params.put("contact_mobile_phone", "");
        break;

      case Client_Activate:
        params.put("confirmation", "");
        break;

      case Client_AddCredits:
        params.put("user_key", "");
        params.put("client_id", "");
        params.put("credits", "");
        break;

      case Client_GetCreditBalance:
        params.put("user_key", "");
        params.put("client_id", "");
        break;

      case Client_GetCreditTransactions:
        params.put("user_key", "");
        params.put("client_id", "");
        params.put("start_date", "");
        params.put("end_date", "");
        params.put("count", "");
        break;
        
      case Client_GetList:
        params.put("user_key", "");
        params.put("status", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        break;

      case Client_GetInfo:
        params.put("user_key", "");
        params.put("client_id", "");
        break;

      case Client_ResetCredits:
        params.put("user_key", "");
        params.put("client_id", "");
        break;
        
      case Client_SetInfo:
        params.put("user_key", "");
        params.put("client_id", "");
        params.put("status", "");
        params.put("manager_id", "");
        params.put("parent_id", "");
        params.put("contact_id", "");
        params.put("company_name", "");
        params.put("address1", "");
        params.put("address2", "");
        params.put("city", "");
        params.put("province_id", "");
        params.put("postal_code", "");
        params.put("country_id", "");
        params.put("website", "");
        params.put("phone", "");
        params.put("fax", "");
        break;

      case Client_GetTimezones:
        break;

      case Client_Search:
        break;

      // User
      case User_CheckPermission:
        params.put("user_key", "");
        params.put("permission", "");
        break;
      
      case User_Create:
        params.put("user_key", "");
        params.put("email", "");
        params.put("password", "");
        params.put("password_confirmation", "");
        params.put("first_name", "");
        params.put("last_name", "");
        params.put("title", "");
        params.put("language", "");
        params.put("timezone_id", "");
        params.put("office_phone", "");
        params.put("mobile_phone", "");
        break;

      case User_GetList:
        params.put("user_key", "");
        params.put("status", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        break;

      case User_GetInfo:
        params.put("user_key", "");
        params.put("user_id", "");
        break;

      case User_SetInfo:
        params.put("user_key", "");
        params.put("user_id", "");
        params.put("email", "");
        params.put("password", "");
        params.put("password_confirmation", "");
        params.put("status", "");
        params.put("first_name", "");
        params.put("last_name", "");
        params.put("title", "");
        params.put("language", "");
        params.put("timezone_id", "");
        params.put("office_phone", "");
        params.put("mobile_phone", "");
        params.put("wysiwyg", "");
        break;

      case User_Login:
        params.put("email", "");
        params.put("password", "");
        break;

      case User_PasswordRecovery:
        params.put("email", "");
        params.put("subject", "");
        params.put("text", "");
        params.put("headers", "");
        params.put("encoding", "");
        params.put("html", "");
        break;

      // Group
      case Group_Create:
        params.put("user_key", "");
        params.put("name", "");
        params.put("client_id", "");
        break;

      case Group_Delete:
      case Group_GetInfo:
        params.put("user_key", "");
        params.put("group_id", "");
        params.put("client_id", "");
        break;

      case Group_GetList:
        params.put("user_key", "");
        params.put("client_id", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        break;

      case Group_SetInfo:
        params.put("user_key", "");
        params.put("group_id", "");
        params.put("client_id", "");
        params.put("name", "");
        subParams.put("CLASS_USER_CREATE", "");
        subParams.put("CLASS_USER_SET_INFO", "");
        break;

      case Group_AddUser:
      case Group_RemoveUser:
        params.put("user_key", "");
        params.put("group_id", "");
        params.put("user_id", "");
        params.put("client_id", "");
        break;

      // Template
      case Template_Create:
        params.put("user_key", "");
        params.put("name", "");
        params.put("type", "");
        params.put("message", "");
        break;

      case Template_Delete:
      case Template_GetInfo:
        params.put("user_key", "");
        params.put("template_id", "");
        break;

      case Template_GetList:
        params.put("user_key", "");
        params.put("type", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        break;

      case Template_SetInfo:
        params.put("user_key", "");
        params.put("template_id", "");
        params.put("message", "");
        params.put("name", "");
        params.put("type", "");
        break;

      // Trigger
      case Trigger_Create:
        params.put("user_key", "");
        params.put("name", "");
        params.put("list_id", "");
        params.put("encoding", "");
        params.put("description", "");
        break;

      case Trigger_GetList:
        params.put("user_key", "");
        params.put("status", "");
        params.put("action", "");
        params.put("list_id", "");
        params.put("parent_id", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        break;

      case Trigger_GetLog:
        params.put("user_key", "");
        break;

      case Trigger_GetLinks:
        params.put("user_key", "");
        break;

      case Trigger_GetLinksLog:
        params.put("user_key", "");
        break;

      case Trigger_GetInfo:
        params.put("user_key", "");
        params.put("trigger_id", "");
        break;

      case Trigger_SetInfo:
        params.put("user_key", "");
        params.put("trigger_id", "");
        params.put("status", "");
        params.put("encoding", "");
        params.put("action", "");
        params.put("name", "");
        params.put("description", "");
        params.put("delay", "");
        params.put("parent_id", "");
        params.put("send_to", "");
        params.put("subject", "");
        params.put("sender_name", "");
        params.put("sender_email", "");
        params.put("html_message", "");
        params.put("text_message", "");
        break;

      case Trigger_Unleash:
        params.put("user_key", "");
        params.put("trigger_id", "");
        params.put("record_id", "");
        break;
        
      // SuppressionList
      case SuppressionList_ImportDomains:
        params.put("user_key", "");
        params.put("domain", "");
        break;

      case SuppressionList_ImportEmails:
        params.put("user_key", "");
        params.put("email", "");
        break;

      case SuppressionList_ImportLocalparts:
        params.put("user_key", "");
        params.put("localpart", "");
        break;

      case SuppressionList_ExportDomains:
        params.put("user_key", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        params.put("like", "");
        break;

      case SuppressionList_ExportEmails:
        params.put("user_key", "");
        params.put("source_type", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        break;

      case SuppressionList_ExportLocalparts:
        params.put("user_key", "");
        params.put("limit", "");
        params.put("offset", "");
        params.put("count", "");
        params.put("like", "");
        break;

      case SuppressionList_DeleteDomains:
        params.put("user_key", "");
        params.put("domain", "");
        break;

      case SuppressionList_DeleteEmails:
        params.put("user_key", "");
        params.put("email", "");
        break;

      case SuppressionList_DeleteLocalparts:
        params.put("user_key", "");
        params.put("localpart", "");
        break;
        
      // Relay
      case Relay_Send:
        params.put("user_key", "");
        params.put("email", "");
        params.put("sender_name", "");
        params.put("sender_email", "");
        params.put("html_message", "");
        params.put("text_message", "");
        params.put("subject", "");
        params.put("encoding", "");
        params.put("track_opening", "");
        params.put("track_clicks_in_html", "");
        params.put("track_clicks_in_text", "");
        subParams.put("Tag", "");
        subParams.put("Ceva", "");
        break;

      case Relay_GetLogs:
        params.put("user_key", "");
        params.put("log_type", "");
        params.put("start_time", "");
        params.put("end_time", "");
        break;

      // Country
      case Country_GetList:
        break;

      case Country_GetProvinces:
        params.put("country_id", "");
        break;

      default:
        break;
    }

    paramsTextArea.setText(ParamUtils.ParamsToString(params));
    subParamsTextArea.setText(ParamUtils.ParamsToString(subParams));
  }

  private void GoButtonClick(ActionEvent evt) {
    String actionName = actionsComboBox.getSelectedItem().toString();

    ParamDictionary params = ParamUtils.StringToParams(paramsTextArea.getText());
    ParamDictionary subParams = ParamUtils.StringToParams(subParamsTextArea.getText());
    
    SettingsUtils.EMAIL = emailTextField.getText();
    SettingsUtils.PASSWORD = passwordTextField.getText();

    TestBase test;
    TraceClear();

    try {
      //Cursor.Current = Cursors.WaitCursor;

      switch (CakeUtils.Action.toAction(actionName)) {
        // Campaign
        case Campaign_Create:
        case Campaign_Delete:
        case Campaign_GetList:
        case Campaign_GetInfo:
        case Campaign_SetInfo:
          test = new TestCampaign();
          test.Run(actionName, params, subParams);
          Trace(test.TraceString);
          break;

        // Mailing
        case Mailing_Create:
        case Mailing_GetList:
        case Mailing_GetInfo:
        case Mailing_SetInfo:
        case Mailing_GetEmailMessage:
        case Mailing_GetHtmlMessage:
        case Mailing_GetTextMessage:
        case Mailing_SendTestEmail:
        case Mailing_Schedule:
        case Mailing_Unschedule:
        case Mailing_Suspend:
        case Mailing_Resume:
        case Mailing_GetStats:
        case Mailing_GetLog:
        case Mailing_GetLinks:
          test = new TestMailing();
          test.Run(actionName, params, subParams);
          Trace(test.TraceString);
          break;

        // List
        case List_Create:
        case List_Delete:
        case List_GetList:
        case List_GetInfo:
        case List_SetInfo:
        case List_EditStructure:
        case List_GetFields:
        case List_Import:
        case List_Show:
        case List_Search:
        case List_SubscribeEmail:
        case List_UnsubscribeEmail:
        case List_DeleteRecord:
        case List_GetRecord:
        case List_AddTestEmail:
        case List_GetTestEmails:
        case List_DeleteTestEmail:
        case List_CreateSublist:
        case List_DeleteSublist:
        case List_TestSublist:
        case List_GetSublists:
        case List_Upload:
          test = new TestList();
          test.Run(actionName, params, subParams);
          Trace(test.TraceString);
          break;

        // Client
        case Client_Create:
        case Client_Activate:
        case Client_GetList:
        case Client_GetInfo:
        case Client_SetInfo:
        case Client_GetTimezones:
        case Client_Search:
          test = new TestClient();
          test.Run(actionName, params, subParams);
          Trace(test.TraceString);
          break;

        // User
        case User_CheckPermission:
        case User_Create:
        case User_GetList:
        case User_GetInfo:
        case User_SetInfo:
        case User_Login:
        case User_PasswordRecovery:
          test = new TestUser();
          test.Run(actionName, params, subParams);
          Trace(test.TraceString);
          break;

        // Group
        case Group_Create:
        case Group_Delete:
        case Group_GetInfo:
        case Group_GetList:
        case Group_SetInfo:
        case Group_AddUser:
        case Group_RemoveUser:
          test = new TestGroup();
          test.Run(actionName, params, subParams);
          Trace(test.TraceString);
          break;

        // Template
        case Template_Create:
        case Template_Delete:
        case Template_GetList:
        case Template_GetInfo:
        case Template_SetInfo:
          test = new TestTemplate();
          test.Run(actionName, params, subParams);
          Trace(test.TraceString);
          break;

        // Trigger
        case Trigger_Create:
        case Trigger_GetList:
        case Trigger_GetLog:
        case Trigger_GetLinks:
        case Trigger_GetLinksLog:
        case Trigger_GetInfo:
        case Trigger_SetInfo:
          test = new TestTrigger();
          test.Run(actionName, params, subParams);
          Trace(test.TraceString);
          break;

        // SuppressionList
        case SuppressionList_ImportDomains:
        case SuppressionList_ImportEmails:
        case SuppressionList_ImportLocalparts:
        case SuppressionList_ExportDomains:
        case SuppressionList_ExportEmails:
        case SuppressionList_ExportLocalparts:
        case SuppressionList_DeleteDomains:
        case SuppressionList_DeleteEmails:
        case SuppressionList_DeleteLocalparts:
          test = new TestSuppressionList();
          test.Run(actionName, params, subParams);
          Trace(test.TraceString);
          break;
          
        // Relay
        case Relay_Send:
        case Relay_GetLogs:
          test = new TestRelay();
          test.Run(actionName, params, subParams);
          Trace(test.TraceString);
          break;

        // Country
        case Country_GetList:
        case Country_GetProvinces:
          test = new TestCountry();
          test.Run(actionName, params, subParams);
          Trace(test.TraceString);
          break;

        default:
          Trace("Action not implemented yet");
          break;
      }
    } catch (CakeException cakeEx) {
      Trace("CAKE EXCEPTION");
      Trace("Code=" + cakeEx.Code);
      Trace("Message=" + cakeEx.getMessage());
      Trace("StackTrace=" + Arrays.toString(cakeEx.getStackTrace()).replaceAll(", ", "\n   "));
    } catch (Exception ex) {
      Trace("EXCEPTION");
      Trace("Message=" + ex.getLocalizedMessage());
      Trace("StackTrace=" + Arrays.toString(ex.getStackTrace()).replaceAll(", ", "\n   "));
    } finally {
      //Cursor.Current = Cursors.Default;
    }
  }

  private void TraceClear() {
    resultTextArea.setText("");
  }

  private void Trace(String message) {
    resultTextArea.append(message + "\n");
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    mainPanel = new javax.swing.JPanel();
    actionsComboBox = new javax.swing.JComboBox();
    goButton = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    paramsTextArea = new javax.swing.JTextArea();
    jLabel1 = new javax.swing.JLabel();
    jScrollPane2 = new javax.swing.JScrollPane();
    subParamsTextArea = new javax.swing.JTextArea();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jScrollPane3 = new javax.swing.JScrollPane();
    resultTextArea = new javax.swing.JTextArea();
    emailTextField = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    passwordTextField = new javax.swing.JTextField();
    statusPanel = new javax.swing.JPanel();
    javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
    statusMessageLabel = new javax.swing.JLabel();
    statusAnimationLabel = new javax.swing.JLabel();
    progressBar = new javax.swing.JProgressBar();

    mainPanel.setMinimumSize(new java.awt.Dimension(1024, 625));
    mainPanel.setName("mainPanel"); // NOI18N
    mainPanel.setPreferredSize(new java.awt.Dimension(1024, 625));

    actionsComboBox.setName("actionsComboBox"); // NOI18N

    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(cakebench.CakeBenchApp.class).getContext().getResourceMap(CakeBenchView.class);
    goButton.setText(resourceMap.getString("goButton.text")); // NOI18N
    goButton.setName("goButton"); // NOI18N

    jScrollPane1.setName("jScrollPane1"); // NOI18N

    paramsTextArea.setColumns(20);
    paramsTextArea.setFont(resourceMap.getFont("paramsTextArea.font")); // NOI18N
    paramsTextArea.setRows(5);
    paramsTextArea.setName("paramsTextArea"); // NOI18N
    jScrollPane1.setViewportView(paramsTextArea);

    jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
    jLabel1.setName("jLabel1"); // NOI18N

    jScrollPane2.setName("jScrollPane2"); // NOI18N

    subParamsTextArea.setColumns(20);
    subParamsTextArea.setFont(resourceMap.getFont("subParamsTextArea.font")); // NOI18N
    subParamsTextArea.setRows(5);
    subParamsTextArea.setName("subParamsTextArea"); // NOI18N
    jScrollPane2.setViewportView(subParamsTextArea);

    jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
    jLabel2.setName("jLabel2"); // NOI18N

    jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
    jLabel3.setName("jLabel3"); // NOI18N

    jScrollPane3.setName("jScrollPane3"); // NOI18N

    resultTextArea.setColumns(20);
    resultTextArea.setFont(resourceMap.getFont("resultTextArea.font")); // NOI18N
    resultTextArea.setRows(5);
    resultTextArea.setName("resultTextArea"); // NOI18N
    jScrollPane3.setViewportView(resultTextArea);

    emailTextField.setText(resourceMap.getString("emailTextField.text")); // NOI18N
    emailTextField.setName("emailTextField"); // NOI18N

    jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
    jLabel4.setName("jLabel4"); // NOI18N

    jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
    jLabel5.setName("jLabel5"); // NOI18N

    passwordTextField.setText(resourceMap.getString("passwordTextField.text")); // NOI18N
    passwordTextField.setName("passwordTextField"); // NOI18N

    org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
    mainPanel.setLayout(mainPanelLayout);
    mainPanelLayout.setHorizontalGroup(
      mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, mainPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
          .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
          .add(org.jdesktop.layout.GroupLayout.LEADING, mainPanelLayout.createSequentialGroup()
            .add(actionsComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 339, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(goButton)
            .add(143, 143, 143)
            .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jLabel4)
              .add(emailTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 246, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jLabel5)
              .add(passwordTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)))
          .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel3)
          .add(org.jdesktop.layout.GroupLayout.LEADING, mainPanelLayout.createSequentialGroup()
            .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 527, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
              .add(jLabel1))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jLabel2)
              .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))))
        .addContainerGap())
    );
    mainPanelLayout.setVerticalGroup(
      mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(mainPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
            .add(actionsComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(goButton))
          .add(mainPanelLayout.createSequentialGroup()
            .add(jLabel4)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(emailTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(mainPanelLayout.createSequentialGroup()
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jLabel5)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(passwordTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
          .add(mainPanelLayout.createSequentialGroup()
            .add(jLabel2)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(mainPanelLayout.createSequentialGroup()
            .add(jLabel1)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jLabel3)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
        .addContainerGap())
    );

    statusPanel.setName("statusPanel"); // NOI18N

    statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

    statusMessageLabel.setName("statusMessageLabel"); // NOI18N

    statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

    progressBar.setName("progressBar"); // NOI18N

    org.jdesktop.layout.GroupLayout statusPanelLayout = new org.jdesktop.layout.GroupLayout(statusPanel);
    statusPanel.setLayout(statusPanelLayout);
    statusPanelLayout.setHorizontalGroup(
      statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1037, Short.MAX_VALUE)
      .add(statusPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(statusMessageLabel)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 913, Short.MAX_VALUE)
        .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(statusAnimationLabel)
        .addContainerGap())
    );
    statusPanelLayout.setVerticalGroup(
      statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(statusPanelLayout.createSequentialGroup()
        .add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .add(statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(statusMessageLabel)
          .add(statusAnimationLabel)
          .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .add(3, 3, 3))
    );

    setComponent(mainPanel);
    setStatusBar(statusPanel);
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox actionsComboBox;
  private javax.swing.JTextField emailTextField;
  private javax.swing.JButton goButton;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JPanel mainPanel;
  private javax.swing.JTextArea paramsTextArea;
  private javax.swing.JTextField passwordTextField;
  private javax.swing.JProgressBar progressBar;
  private javax.swing.JTextArea resultTextArea;
  private javax.swing.JLabel statusAnimationLabel;
  private javax.swing.JLabel statusMessageLabel;
  private javax.swing.JPanel statusPanel;
  private javax.swing.JTextArea subParamsTextArea;
  // End of variables declaration//GEN-END:variables
  private final Timer messageTimer;
  private final Timer busyIconTimer;
  private final Icon idleIcon;
  private final Icon[] busyIcons = new Icon[15];
  private int busyIconIndex = 0;
  private JDialog aboutBox;
}
