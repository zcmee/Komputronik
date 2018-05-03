$( document ).ready(function() {
    var timer;
    var wait = 20; // waiting time in minutes
    timer = setTimeout(logout, 60 *  1000 * wait);
    document.onclick=resetTimer;

    function resetTimer() {
        clearTimeout(timer);
        timer = setTimeout(logout, 60 * 1000 * wait);
    }

    function logout() {
        window.location.href='/login?session-expired';
    }
});