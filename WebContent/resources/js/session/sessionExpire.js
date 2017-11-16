var idleSeconds = secondsBeforeExpire / 6;
attachEvent(window, 'load', function() {
	var idleTimer;
	function resetTimer() {
		clearTimeout(idleTimer);
		idleTimer = setTimeout(whenUserIdle, idleSeconds * 1000);
	}
	attachEvent(document.body, 'mousemove', resetTimer);
	attachEvent(document.body, 'keydown', resetTimer);
	attachEvent(document.body, 'click', resetTimer);

	resetTimer();
});

function whenUserIdle() {
	var minutesBeforeExpiration = idleSeconds / 60;
	alert("Session will expire after " + minutesBeforeExpiration + " minutes");
}

function attachEvent(obj, evt, fnc, useCapture) {
	if (obj.addEventListener) {
		obj.addEventListener(evt, fnc, !!useCapture);
		return true;
	} else if (obj.attachEvent) {
		return obj.attachEvent("on" + evt, fnc);
	}
}