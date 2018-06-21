function footer() {
    var docHeight = $(window).height();
    var footerHeight = $('footer').height();
    var footerTop = $('footer').position().top + footerHeight;
    if (footerTop < docHeight) { $('footer').css('margin-top', (docHeight - footerTop - 80) + 'px');}
    $('[data-toggle="tooltip"]').tooltip();
}

function addPositiveMessage(message) {
   addMessage(message, "success")
}

function addNegativeMessage(message) {
   addMessage(message, "danger")
}

function addMessage(message, type) {
    var $tmp =  $("#show-message");
    $tmp.addClass("alert");
    $tmp.removeClass("alert-success").removeClass("alert-danger");
    if(type === "success") {
        $tmp.addClass("alert-success");
    } else if(type === "danger") {
        $tmp.addClass("alert-danger");
    }
    $tmp.html(message);
}

function cleanMessage() {
    $("#show-message").removeClass(" alert-success")
                      .removeClass("alert-danger")
                      .html("");
}

toastr.options = {
  "closeButton": true,
  "debug": false,
  "newestOnTop": false,
  "progressBar": true,
  "positionClass": "toast-top-full-width",
  "preventDuplicates": false,
  "onclick": null,
  "showDuration": "300",
  "hideDuration": "1000",
  "timeOut": "5000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}

Handlebars.registerHelper('select', function( value, options ){
    var $el = $('<select />').html( options.fn(this) );
    $el.find('[value="' + value + '"]').attr({'selected':'selected'});
    return $el.html();
});

Handlebars.registerHelper('dateFormatter', function(date){
    if(date !== null) {return moment(date).format("YYYY-MM-DD"); }
    return '';
});