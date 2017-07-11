package com.kms.katalon.constants;

public class EventConstants {
    // Property's name of event that is broadcast by IEventBroker
    public static final String EVENT_DATA_PROPERTY_NAME = "org.eclipse.e4.data";

    // Workspace events
    public static final String WORKSPACE_CREATED = "WORKSPACE/CREATED";

    // Project Events
    public static final String PROJECT_ALL = "PROJECT/*";

    public static final String PROJECT_CREATED = "PROJECT/CREATED";

    public static final String PROJECT_OPEN = "PROJECT/OPEN";

    public static final String PROJECT_OPENED = "PROJECT/OPENED";

    public static final String PROJECT_CLOSE = "PROJECT/CLOSE";

    public static final String PROJECT_CLOSED = "PROJECT/CLOSED";

    public static final String PROJECT_UPDATED = "PROJECT/UPDATED";

    public static final String PROJECT_SAVE_SESSION = "PROJECT/SAVE_SESSION";

    public static final String PROJECT_SETTINGS = "PROJECT/SETTINGS";

    public static final String PROJECT_INDEX = "PROJECT/INDEX";

    // Eclipse Events
    public static final String ECLIPSE_EDITOR_CLOSED = "EDITORS_CLOSED";

    public static final String ECLIPSE_EDITOR_SAVED = "EDITORS_SAVED";

    // Explorer Events
    public static final String EXPLORER_RELOAD_DATA = "EXPLORER/RELOAD_DATA";

    public static final String EXPLORER_RELOAD_INPUT = "EXPLORER/RELOAD_INPUT";

    public static final String EXPLORER_SET_SELECTED_ITEM = "EXPLORER/SET_SELECTED_ITEM";

    public static final String EXPLORER_SET_SELECTED_ITEMS = "EXPLORER/SET_SELECTED_ITEMS";

    public static final String EXPLORER_SHOW_ITEM = "EXPLORER/SHOW_ITEM";

    public static final String EXPLORER_REFRESH = "EXPLORER/REFRESH";

    public static final String EXPLORER_REFRESH_TREE_ENTITY = "EXPLORER/REFRESH_TREE_ENTITY";

    public static final String EXPLORER_EXPAND_TREE_ENTITY = "EXPLORER/EXPAND_TREE_ENTITY";

    public static final String EXPLORER_OPEN_SELECTED_ITEM = "EXPLORER/OPEN_SELECTED_ITEM";

    public static final String EXPLORER_DELETE_SELECTED_ITEM = "EXPLORER/DELETE_SELECTED_ITEM";

    public static final String EXPLORER_DELETED_SELECTED_ITEM = "EXPLORER/DELETED_SELECTED_ITEM";

    public static final String EXPLORER_RESET_USER_RESPONSE_FOR_DELETION = "EXPLORER/RESET_USER_RESPONSE_FOR_DELETION";

    public static final String EXPLORER_RENAME_SELECTED_ITEM = "EXPLORER/RENAME_SELECTED_ITEM";

    public static final String EXPLORER_RENAMED_SELECTED_ITEM = "EXPLORER/RENAMED_SELECTED_ITEM";

    public static final String EXPLORER_PASTE_SELECTED_ITEM = "EXPLORER/PASTE_SELECTED_ITEM";

    public static final String EXPLORER_COPY_PASTED_SELECTED_ITEM = "EXPLORER/COPY_PASTED_SELECTED_ITEM";

    public static final String EXPLORER_CUT_PASTED_SELECTED_ITEM = "EXPLORER/CUT_PASTED_SELECTED_ITEM";

    public static final String EXPLORER_FILTER_ITEM = "EXPLORER/FILTER_ITEM";

    public static final String EXPLORER_HIGHLIGH_FILTER_ITEM = "EXPLORER/HIGHLIGH_FILTER_ITEM";

    public static final String EXPLORER_REFRESH_SELECTED_ITEM = "EXPLORER/REFRESH_SELECTED_ITEM";

    public static final String EXPLORER_REFRESH_ALL_ITEMS = "EXPLORER/REFRESH_ALL_ITEMS";

    public static final String EXPLORER_COLLAPSE_ALL_ITEMS = "EXPLORER/COLLAPSE_ALL_ITEMS";

    public static final String EXPLORER_LINK_ITEM_WITH_SELECTED_PART = "EXPLORER/LINK_ITEM_WITH_SELECTED_PART";

    public static final String EXPLORER_DELETE_TEST_CASE_FOLDER = "EXPLORER/DELETE_TEST_CASE_FOLDER";

    // Folder Events
    public static final String FOLDER_REFRESH_CHILDREN = "EXPLORER/REFRESH_CHILDREN";

    // TestCase Events
    public static final String TESTCASE_ALL = "TESTCASE/*";

    public static final String TESTCASE_NEW = "TESTCASE/NEW";

    public static final String TESTCASE_OPEN = "TESTCASE/OPEN";

    public static final String TESTCASE_SAVE = "TESTCASE/SAVE";

    public static final String TESTCASE_UPDATED = "TESTCASE/UPDATED";

    public static final String TESTCASE_SAVE_SCRIPT = "TESTCASE/SAVE_SCRIPT";

    public static final String TESTCASE_REFRESH_EDITOR = "TESTCASE/UPDATE_SCRIPT";

    public static final String TESTCASE_SETTINGS_FAILURE_HANDLING_UPDATED = "TESTCASE/FAILURE_HANDLING_UPDATED";

    public static final String TESTCASE_ADD_STEP = "TESTCASE/ADD_STEP";

    public static final String TESTCASE_ADD_STEP_CALL_TESTCASE = "TESTCASE/ADD_STEP/CALL_TESTCASE";

    public static final String TESTCASE_RECENT_KEYWORD_ADDED = "TESTCASE/RECENT_KEYWORD_ADDED";

    // TestRun Events
    public static final String TEST_SUITE_COLLECTION_NEW = "TEST_SUITE_COLLECTION/NEW";

    public static final String TEST_SUITE_COLLECTION_OPEN = "TEST_SUITE_COLLECTION/OPEN";

    public static final String TEST_SUITE_COLLECTION_UPDATED = "TEST_SUITE_COLLECTION/UPDATED";

    // TestSuite Events
    public static final String TEST_SUITE = "TESTSUITE/*";

    public static final String TEST_SUITE_NEW = "TESTSUITE/NEW";

    public static final String TEST_SUITE_OPEN = "TESTSUITE/OPEN";

    public static final String TEST_SUITE_SAVE = "TESTSUITE/SAVE";

    public static final String TEST_SUITE_UPDATED = "TESTSUITE/UPDATED";

    // TestData Events
    public static final String TEST_DATA_NEW = "TESTDATA/NEW";

    public static final String TEST_DATA_OPEN = "TESTDATA/OPEN";

    public static final String TEST_DATA_UPDATED = "TESTDATA/UPDATE";

    // TestObject Events
    public static final String TEST_OBJECT_NEW = "TESTOBJECT/NEW";

    public static final String TEST_OBJECT_OPEN = "TESTOBJECT/OPEN";

    public static final String TEST_OBJECT_SAVE = "TESTOBJECT/SAVE";

    public static final String TEST_OBJECT_UPDATED = "TESTOBJECT/UPDATED";

    public static final String WEBSERVICE_REQUEST_OBJECT_NEW = "WSOBJECT/NEW";

    public static final String WEBSERVICE_REQUEST_OBJECT_OPEN = "WSOBJECT/OPEN";

    // Report Event
    public static final String REPORT_OPEN = "REPORT/OPEN";

    public static final String REPORT_UPDATED = "REPORT/UPDATED";

    public static final String REPORT_DELETED = "REPORT/DELETED";

    public static final String REPORT_RENAMED = "REPORT/RENAMED";

    public static final String REPORT_COLLECTION_RENAMED = "REPORT/COLLECTION/RENAMED";

    // Keyword Events
    public static final String GROOVY_REFRESH_PROJECT = "GROOVY/REFRESH_PROJECT";

    public static final String KEYWORD_NEW = "KEYWORD/NEW";

    public static final String PACKAGE_NEW = "PACKAGE/NEW";

    // Import Events
    public static final String IMPORT_DUPLICATE_ENTITY_RESULT = "IMPORT/DUPLICATE_ENTITY_RESULT";

    public static final String EXECUTION_LOGGING_EVENT = "EXECUTION/LOGGING_EVENT";

    // Console Log Events
    public static final String CONSOLE_LOG_OPEN = "CONSOLE_LOG/OPEN";

    public static final String CONSOLE_LOG_RESET = "CONSOLE_LOG/RESET";

    public static final String CONSOLE_LOG_REFRESH = "CONSOLE_LOG/REFRESH";

    public static final String CONSOLE_LOG_UPDATE_PROGRESS_BAR = "CONSOLE_LOG/UPDATE_PROGRESS_BAR";

    public static final String CONSOLE_LOG_CHANGE_VIEW_TYPE = "CONSOLE_LOG/CHANGE_VIEW_TYPE";

    public static final String CONSOLE_LOG_WORD_WRAP = "CONSOLE_LOG/WORD_WRAP";

    // Job Events
    public static final String JOB_REFRESH = "JOB/REFRESH";

    public static final String JOB_UPDATE_PROGRESS = "JOB/UPDATE_PROGRESS";

    public static final String JOB_COMPLETED = "JOB/COMPLETED";

    // Object Spy Events
    public static final String OBJECT_SPY_RESET_SELECTED_TARGET = "OBJECT_SPY/RESET_SELECTED_TARGET";

    public static final String OBJECT_SPY_REFRESH_SELECTED_TARGET = "OBJECT_SPY/REFRESH_SELECTED_TARGET";

    public static final String OBJECT_SPY_TEST_OBJECT_ADDED = "OBJECT_SPY/TEST_OBJECT_ADDED";

    public static final String OBJECT_SPY_CLOSE_MOBILE_APP = "OBJECT_SPY/CLOSE_MOBILE_APP";

    public static final String OBJECT_SPY_MOBILE_HIGHLIGHT = "OBJECT_SPY/MOBILE_HIGHLIGHT";

    public static final String OBJECT_SPY_MOBILE_SCREEN_CAPTURE = "OBJECT_SPY/MOBILE_SCREEN_CAPTURE";

    public static final String OBJECT_SPY_HTML_ELEMENT_CAPTURED = "OBJECT_SPY/HTML_ELEMENT_CAPTURED";

    public static final String OBJECT_SPY_HTML_DOM_CAPTURED = "OBJECT_SPY/HTML_DOM_CAPTURED";

    public static final String OBJECT_SPY_WEB = "OBJECT_SPY/WEB";

    public static final String OBJECT_SPY_MOBILE = "OBJECT_SPY/MOBILE";

    // Recorder Events
    public static final String RECORDER_HTML_ACTION_CAPTURED = "RECORDER/HTML_ACTION_CAPTURED";

    // Global Variable Events
    public static final String GLOBAL_VARIABLE_REFRESH = "GLOBAL_VARIABLE/REFRESH";

    public static final String GLOBAL_VARIABLE_SHOW_REFERENCES = "GLOBAL_VARIABLE/SHOW_REFERENCES";

    // Checkpoint Events
    public static final String CHECKPOINT_NEW = "CHECKPOINT/NEW";

    public static final String CHECKPOINT_OPEN = "CHECKPOINT/OPEN";

    public static final String CHECKPOINT_UPDATED = "CHECKPOINT/UPDATED";

    // Properties Events
    public static final String PROPERTIES_ENTITY = "PROPERTIES/ENTITY";

    public static final String PROPERTIES_ENTITY_UPDATED = "PROPERTIES/ENTITY_UPDATED";

    // Execution Events
    public static final String EXECUTE_FROM_TEST_STEP = "EXECUTE/FROM_TEST_STEP";

    public static final String KATALON_HELP = "KATALON/HELP";

    public static final String KATALON_QUICK_GUIDE = "KATALON/QUICK_GUIDE";

    public static final String KATALON_PREFERENCES = "KATALON/PREFERENCES";

    public static final String KATALON_LOAD_COMMANDS = "KATALON/LOAD_COMMANDS";

    public static final String KATALON_OPEN_URL = "KATALON/OPEN_URL";

    public static final String KATALON_UPDATE_HELP_CONTENTS = "KATALON/UPDATE_HELP_CONTENTS";

    public static final String KATALON_RECORD = "KATALON/RECORD";

    public static final String KATALON_GENERATE_COMMAND = "KATALON/GENERATE_COMMAND";

}
