//롤링 배너
var popup_timer3 = null;
var popup_delay_time3 = 5000;
var popup_pause3 = false;
var popup_button_pause3 = false;
var select_popup_num3 = 1;
  
function popup_display3(popupIDX, move, strid) {
	if(!popupIDX)  {
  		popupIDX = select_popup_num3;
  		if(popup_pause3==true && !move) return;
  		if(popup_button_pause3==true && !move) return;
  		if(!move) move = 'next';
  		hide_popup3(strid, popupIDX);
  	}else{
  		hide_popup3(select_popup_num3);
  	}
  	if(move=='prev') {
  		if(popupIDX==1) {
  			popupIDX = popup_object_num3;
  		} else {
  			popupIDX--;
  		}
 	} else if(move=='next') {
  		if(popupIDX==popup_object_num3) {
  			popupIDX = 1;
  		} else {
  			popupIDX++;
  		}
  	} 
  	show_popup3(strid, popupIDX);
  	select_popup_num3 = popupIDX;
}
  
function hide_popup3(strid, num) {
  	document.getElementById(strid + num).style.display = 'none';
}
  
function show_popup3(strid, num) {
  	document.getElementById(strid + num).style.display = 'block';
}

function popup_scroll3(strid) {
  	popup_timer3 = setInterval("popup_display3('','','"+strid+"')",popup_delay_time3); 
}