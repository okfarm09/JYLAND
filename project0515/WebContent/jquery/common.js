function g_ValidOnlyEngNum(val) {
	if(val.length == 0){
		return true;
	}
	
	re = /[^A-Za-z0-9]/;

    return  re.test(val);
}

function g_IsValidUserId(str) {
	res1 = (/[a-z]/i).test(str);
	res2 = (/^[0-9a-z]*$/i).test(str);

	return (res1 && res2);
}

function g_IsPhone(val) {
	if(val.length == 0){
		return true;
	}
	
	re = /^\d{3,4}-\d{3,4}-\d{4}$/;

    return  re.test(val);
}

function g_IsEmail(val) {
	if(val.length == 0){
		return true;
	}
	
	re = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    return  re.test(val);
}

function g_IsEmailHost(val) {
	if(val.length == 0){
		return true;
	}
	
	re = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    return  re.test(val);
}

function g_IsNumber(val) {
	if(val.length == 0){
		return true;
	}
	
	re = /[^0-9]/gi;

    return  re.test(val);
}

function g_popUpCalendar() {

    var s_pos = "dialogWidth:250px; dialogHeight:270px; status:no; help:no; scroll:no;";
    var s_url = "/ext_yongin/common/pop_calendar.jsp";
    var ls_result = window.showModalDialog(s_url, window , s_pos) ;

    if (ls_result == "" || ls_result == null) ls_result = "";

    return ls_result;
}

function g_popUpMobileCalendar() {

    var s_pos = "width=250, height=250, resizable=no, scrollbars=no, toolbars=no, status=no, menu=no";
    var s_url = "/ext_yongin/common/pop_calendar_car.jsp";
    var ls_result = window.open(s_url, "win_zip" , s_pos) ;   
}


function g_popUpCalendar2(weekday) {

    var s_pos = "dialogWidth:250px; dialogHeight:270px; status:no; help:no; scroll:no;";
    var s_url = "/common/pop_calendar2.jsp?weekday=" + weekday;
    var ls_result = window.showModalDialog(s_url, window , s_pos) ;

    if (ls_result == "" || ls_result == null) ls_result = "";

    return ls_result;
}

function g_popConfirmPwdPop(params)
{	
    var s_pos = "dialogWidth:328px; dialogHeight:138px;status:no;help:no;scroll:no";
    var s_url = "../common/g_popConfirmPwdPop.do";
    var ls_result = window.showModalDialog(s_url, params , s_pos) ;
    if (ls_result == "" || ls_result == null) ls_result = "";
    return ls_result;
}

function g_popZip() {
    var s_url = "../common/pop_zip.do";
	var s_pos = "width=420, height=280,resizable=yes,scrollbars=yes,toolbars=no,status=yes,menu=no";
    var ls_result = window.open(s_url, "win_zip" , s_pos) ;
}

function g_mobile_popZip() {

    var s_url = "/ext_nyj/common/pop_mobile_zip.jsp";
	var s_pos = "width=420, height=280,resizable=yes,scrollbars=yes,toolbars=no,status=yes,menu=no";
    var ls_result = window.open(s_url, "win_zip" , s_pos) ;
}

function g_validate(FormName) {
	var result = true;
	
	$("#" + FormName + " input[data-validate='true'], textarea[data-validate='true'], select[data-validate='true']").each(function() {
		if($(this).val() == "") {
			alert($(this).attr("title") + "해주십시요.");
			$(this).focus();
			
			result = false;
			return false;
		}			
	});
	
	return result;
}
/*
function g_validate(FormName, lang) {
	var result = true;
	
	$("#" + FormName + " input[data-validate='true'], textarea[data-validate='true']").each(function() {
		if($(this).val() == "") {
			
			if (lang == "ko") alert($(this).attr("title") + "해주십시요.");
			if (lang == "en") alert("The " + $(this).attr("title") + " field is required.");
			
			$(this).focus();
			
			result = false;
			return false;
		}			
	});
	
	return result;
}
*/