window.onclick = function(event) {
	var temp = document.getElementsByClassName('modal');
	for (var i = 0; i < temp.length; i++) {
		if (event.target == temp[i]) {
			temp[i].style.display = "none";
		}
	}
}