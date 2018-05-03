function sendFormByAjax(idForm, isAttachment, fun) {
    var contentForm;
    var form = $('#' + idForm);
    if(isAttachment) contentForm = new FormData(($('#'+idForm))[0]);
    else contentForm = $('#'+idForm).serialize();
    var $button = $(this);
    if($button.data('requestRunning')) return;
    $button.data('requestRunning', true);

    $.ajax({
        type: form.attr('method'),
        url: form.attr('action'),
        cache: false,
        encoding: "UTF-8",
        data: contentForm,
        contentType: isAttachment ? false : 'application/x-www-form-urlencoded; charset=UTF-8',
        processData: false,
        success: function (datax) {
            fun(datax);
        },
        error: function(xhr) {
            var $element = $("#error-message");
            if ($element.length && xhr.status < 500) {
                $element.show();
                $element.empty().append('<strong>Błąd!</strong><BR/> ' + xhr.responseText).addClass("alert alert-danger");
            }

        },
        complete : function() {
            $button.data('requestRunning', false);
        },
        statusCode: {
            500: function() {
                $("#error-message").empty()
                                   .append('Funkcjonalość chwilowo niedostępna. Proszę spróbować później.');
            }
        }
    });
}