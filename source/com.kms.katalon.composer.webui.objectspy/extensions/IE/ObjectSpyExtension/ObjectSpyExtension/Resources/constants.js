// SERVER CONSTANTS
var KATALON_SERVER_URL_PREFIX = "http://localhost:";
var KATALON_SERVER_URL_SUFFIX = "/"
var XHTTP_GET_METHOD = "GET";
var XHTTP_POST_METHOD = "POST";
var XHTTP_ACTION = "xhttp";
var POST_DOM_MAP_SUCCESS = "Load DOM map to Katalon successfully"

// STYLE CONSTANTS
var ELEMENT_HOVER_OUTLINE_STYLE = "2px solid #f00";

var INFO_DIV_DEFAULT_BACKGROUND_COLOR = "black";
var INFO_DIV_DEFAULT_FOREGROUND_COLOR = "white";
var INFO_DIV_DEFAULT_BORDER_COLOR = 'white';
var INFO_DIV_HOVER_BACKGROUND_COLOR = "blue";
var INFO_DIV_HOVER_FOREGROUND_COLOR = "yellow";
var INFO_DIV_HOVER_BORDER_COLOR = 'yellow';

// ANIMATION CONSTANTS
var ELEMENT_FLASHING_OUTLINE_STYLE = "2px solid #0F0"
var ELEMENT_FLASHING_OUTLINE_COLOR_1 = "#FFF";
var ELEMENT_FLASHING_OUTLINE_COLOR_2 = "#0F0";
var ELEMENT_FLASHING_OUTLINE_COLOR_3 = "#F00";

// INSTRUCTION DIV BASE64 IMAGE
var INSTRUCTION_IMAGE = '<img src="data:image/png;base64,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" />'
var INSTRUCTION_IMAGE_SIZE = 267